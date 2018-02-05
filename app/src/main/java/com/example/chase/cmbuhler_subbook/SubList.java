/*
 * SubList
 *
 * February 4, 2018
 *
 * Copyright © Chase Buhler, CMPUT301, University of Alberta - All rights reserved.
 * You may use, distribute, or modify this code under the terms and conditions of the
 * Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in this project. Otherwise please contact cmbuhler@ualberta.ca
 */

package com.example.chase.cmbuhler_subbook;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * SubList is a class that has an underlying ArrayList of Subscription objects
 * and various methods to operate on that list. This ArrayList is the data
 * source for the ListView in the Main Activity.
 *
 * SubList also manages the interaction with the data file for persistent
 * storage
 *
 * @author Chase Buhler
 * @see ArrayList
 * @see Subscription
 */
public class SubList {

    private final static String FILENAME = "subdata.sav";
    private static SubList mySubList = new SubList();;

    private ArrayList<Subscription> subList;
    private Context context;

    /**
     * Default constructor. Constructs a SubList
     */
    private SubList(){
    }

    /**
     * Initialize this sublist.
     * @param context MainActivity context
     */
    public void init(Context context){
        this.context = context;
        loadFromFile();
    }

    /**
     * Get the instance of this sublist
     * @return this sublist instance.
     */
    public static SubList getInstance(){
        return mySubList;
    }

    /**
     * Return the underlying ArrayList
     * @return the ArrayList full of Subscription objects
     */
    public ArrayList<Subscription> getSubList(){
        return subList;
    }

    /**
     * Add a new Subscription object, with a null comment, to the list and
     * save it to the file.
     * @param name Name of the new subscription.
     * @param year Year of the new subscription's start date.
     * @param month Month of the new subscription's start date.
     * @param day Day of the new subscription's start date.
     * @param charge Monthly charge of the new subscription.
     */
    public void add(String name, int year, int month, int day, float charge){
        add(name, year, month, day, charge, null);
    }

    /**
     * Add a new Subscription object to the list and
     * save it to the file.
     * @param name Name of the new subscription.
     * @param year Year of the new subscription's start date.
     * @param month Month of the new subscription's start date.
     * @param day Day of the new subscription's start date.
     * @param charge Monthly charge of the new subscription.
     * @param comment A comment about the new subscription.
     */
    public void add(String name, int year, int month, int day, float charge, String comment){
        Subscription sub = new Subscription(name, year, month, day, charge, comment);
        subList.add(sub);
        updateFile();
    }

    /**
     * Remove a subscription from the list
     * @param position position in the list of the subscription that is to be
     *                 removed.
     */
    public void remove(int position){
        subList.remove(position);
        updateFile();
    }

    /**
     * Get a subscription from the list
     * @param position position in the list of the desired subscription
     * @return the Subscription object at position
     */
    public Subscription get(int position){
        return subList.get(position);
    }

    /**
     * Get the total monthly charge of all the subscriptions in the list
     * @return total monthly charge of all the subscriptions in the list
     */
    public float getTotalCharge(){

        float charge = 0;
        for(Subscription sub: subList){
            charge += sub.getCharge();
        }
        return charge;
    }

    /**
     * Update the data file
     */
    public void updateFile(){
        try {

            FileOutputStream fos = context.openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(subList, out);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Load data into the list from the file.
     */
    public void loadFromFile(){
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // taken https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018 - 01 - 24

            Type listType = new TypeToken<ArrayList<Subscription>>() {
            }.getType();

            subList = gson.fromJson(in, listType);
        } catch (FileNotFoundException e){
            subList = new ArrayList<Subscription>();
        }
    }
}

package com.example.chase.cmbuhler_subbook;

import java.util.ArrayList;

/**
 * Created by Chase on 2018-01-17.
 */

public class SubList {
    private static SubList mySubList = new SubList();
    private float totalCharge;
    private ArrayList<Subscription> subList;

    private SubList(){
        subList = loadSubList();
    }

    public static SubList getInstance(){
        return mySubList;
    }

    private ArrayList<Subscription> loadSubList(){
        //Load the subscription list from the file
        //if it is not loaded
        return new ArrayList<Subscription>();
    }

    public ArrayList<Subscription> getSubList(){
        return subList;
    }

    public void add(String name, int year, int month, int day, float charge){
        add(name, year, month, day, charge, null);
        //Save to file
    }

    public float getTotalCharge(){
        return totalCharge;
    }

    public void add(String name, int year, int month, int day, float charge, String comment){
        totalCharge = totalCharge + charge;
        Subscription sub = new Subscription(name, year, month, day, charge, comment);
        subList.add(sub);
    }

    public void remove(int position){
        totalCharge = totalCharge - subList.get(position).getCharge();
        subList.remove(position);
        //Remove from file
    }

    public Subscription get(int position){
        return subList.get(position);
    }



}

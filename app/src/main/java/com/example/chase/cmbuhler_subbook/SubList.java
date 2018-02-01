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
 * Created by Chase on 2018-01-17.
 */

public class SubList {
    private final static String FILENAME = "subdata.sav";
    private static SubList mySubList = new SubList();
    private ArrayList<Subscription> subList;
    private Context context;

    private SubList(){
    }

    public void init(Context context){
        this.context = context;
        loadFromFile();
    }

    public static SubList getInstance(){
        return mySubList;
    }

    public ArrayList<Subscription> getSubList(){
        return subList;
    }

    public void add(String name, int year, int month, int day, float charge){
        add(name, year, month, day, charge, null);
        //Save to file
    }

    public float getTotalCharge(){

        float charge = 0;
        for(Subscription sub: subList){
            charge += sub.getCharge();
        }
        return charge;
    }

    public void add(String name, int year, int month, int day, float charge, String comment){
        Subscription sub = new Subscription(name, year, month, day, charge, comment);
        subList.add(sub);
        updateFile();
    }

    private void updateFile(){
        try {

            FileOutputStream fos = context.openFileOutput(FILENAME,

                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));



            Gson gson = new Gson();



            gson.toJson(subList, out);



            out.flush();

        } catch (FileNotFoundException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void remove(int position){
        subList.remove(position);
        updateFile();
        //Remove from file
    }

    public Subscription get(int position){
        return subList.get(position);
    }

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

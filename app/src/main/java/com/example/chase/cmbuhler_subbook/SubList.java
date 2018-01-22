package com.example.chase.cmbuhler_subbook;

import java.util.ArrayList;

/**
 * Created by Chase on 2018-01-17.
 */

public class SubList {
    private static SubList mySubList = new SubList();
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

    public void add(Subscription sub){
        subList.add(sub);
        //Save to file
    }

    public void remove(Subscription sub){
        subList.remove(sub);
        //Remove from file
    }



}

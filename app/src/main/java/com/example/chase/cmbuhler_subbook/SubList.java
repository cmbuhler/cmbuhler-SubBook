package com.example.chase.cmbuhler_subbook;

import java.util.ArrayList;

/**
 * Created by Chase on 2018-01-17.
 */

public class SubList {
    private ArrayList<Subscription> subList;

    public SubList(){
        subList = loadSubList();
    }

    private ArrayList<Subscription> loadSubList(){
        //Load the subscription list from the file
        //if it is not loaded
        return new ArrayList<Subscription>();
    }
}

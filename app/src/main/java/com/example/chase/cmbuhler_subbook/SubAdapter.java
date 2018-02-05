/*
 * SubAdapter
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Represents the adapter used to give the ListView data about each subscription
 * References:
 *      https://www.raywenderlich.com/124438/android-listview-tutorial
 *          Retrieved: 2018-01-28
 *
 * @author Chase Buhler
 * @see android.widget.Adapter
 * @see Subscription
 */

public class SubAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Subscription> data;

    /**
     * Constructs a new Adapter
     * @param context MainActivity context
     * @param data Subscription list to be used as the data
     */
    public SubAdapter(Context context, ArrayList<Subscription> data){
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    /**
     * Get the count of the list
     * @return count of the list
     */
    @Override
    public int getCount() {
        return data.size();
    }

    /**
     * get an item from the list
     * @param position position in the list
     * @return the Object at that position in the list
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    /**
     * Return the id of the item. The ID for this simple case is just the
     * position in the array.
     * @param position position of the item whose ID we are looking for
     * @return the id of the item at position (Which is just position itself)
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Contructs the view that will be used to display the details of a
     * subscription in the ListView.
     * @param position position on the array of the subscription whose details
     *                 are required.
     * @param view
     * @param parent Parent ViewGroup
     * @return
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View subView = inflater.inflate(R.layout.list_sub, parent, false);

        // Get the textView for the name
        TextView nameText = (TextView) subView.findViewById(R.id.list_sub_name);

        // Get the textView for the date
        TextView dateText = (TextView) subView.findViewById(R.id.list_sub_date);

        // Get the textCiew for the charge
        TextView chargeText = (TextView) subView.findViewById(R.id.list_sub_charge);

        // Populate the layout with the data from the current sub
        Subscription sub = (Subscription) getItem(position);
        nameText.setText(sub.getName());
        dateText.setText("" + Subscription.getMonth(sub.getMonth())
         + " " + sub.getDay() + ", " + sub.getYear());
        chargeText.setText(String.format("$%.2f", sub.getCharge()));


        return subView;
    }
}

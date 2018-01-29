package com.example.chase.cmbuhler_subbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chase on 2018-01-24.
 *
 * References:
 *      https://www.raywenderlich.com/124438/android-listview-tutorial
 *          Retrieved: 2018-01-28
 */

public class SubAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Subscription> data;

    public SubAdapter(Context context, ArrayList<Subscription> data){
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

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
        chargeText.setText("" + sub.getCharge());


        return subView;
    }
}

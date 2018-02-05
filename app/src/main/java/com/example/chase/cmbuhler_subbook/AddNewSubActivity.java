/*
 * AddNewSubActivity
 *
 * February 4, 2018
 *
 * Copyright © Chase Buhler, CMPUT301, University of Alberta - All rights reserved.
 * You may use, distribute, or modify this code under the terms and conditions of the
 * Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in this project. Otherwise please contact cmbuhler@ualberta.ca
 */

package com.example.chase.cmbuhler_subbook;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;


import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;

/**
 * Represents the activity the user sees when adding a new subscription
 *
 * @author Chase Buhler
 */
public class AddNewSubActivity extends AppCompatActivity {

    static final String STATE_YEAR = "subYear";
    static final String STATE_MONTH = "subMonth";
    static final String STATE_DAY = "subDay";
    private Calendar calendar;
    private TextView date;
    private Button addButton;
    private int month;
    private int day;

    /**
     * Creates the AddNewSubActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sub);
        addButton = findViewById(R.id.button);
        date = (TextView) findViewById(R.id.sub_date);
        calendar = Calendar.getInstance();


        if(savedInstanceState == null){
            //Activity is being created for the first time

            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            //Activity is being restarted

            year = savedInstanceState.getInt(STATE_YEAR);
            month = savedInstanceState.getInt(STATE_MONTH);
            day = savedInstanceState.getInt(STATE_DAY);
        }

        showDate(year, month, day);
        addButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                addNewSub(view);
            }
        });

    }

    /**
     * Save some values from the edit texts in this activity during restarts
     * @param savedInstanceState the Bundle that is going to save the instance state.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        //Save current date for persistence between activity restarts
        savedInstanceState.putInt(STATE_YEAR, year);
        savedInstanceState.putInt(STATE_MONTH, month);
        savedInstanceState.putInt(STATE_DAY, day);

        //Call the super class state to still save other state info
        super.onSaveInstanceState(savedInstanceState);
    }

    /*
     * DatePicker example:
     * Retrieved 2018-01-22
     * https://www.tutorialspoint.com/android/android_datepicker_control.htm
     */

    /**
     * Sets the date
     * @param view view
     */
    public void setDate(View view){
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT).show();
    }

    /**d
     * Create the dialog that displays the DatePicker
     * @param id id
     * @return datePicker Dialog
     */
    @Override
    protected Dialog onCreateDialog(int id){
        if(id == 999){
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    showDate(i, i1, i2);
                }
            };

    /**
     * Shows the date in the TextView
     */
    private void showDate(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
        date.setText(new StringBuilder().append(Subscription.getMonth(month)).append(" " + day +
                ", " + year));
    }

    /**
     * Called when the add button is pressed.
     * Checks to make sure the entered values are
     * appropriate and then adds a new subscription
     * object to the SubList and finishes the Activity.
     * If some of the entered values are bad, the user
     * is notified and asked to re enter the bad values.
     * @param view Button clicked
     */
    public void addNewSub(View view){
        String name = "";
        float charge = 0.0f;
        boolean isGoodInput = true;

        EditText subName = (EditText) findViewById(R.id.text_subName);
        EditText subCharge = (EditText) findViewById(R.id.text_subCharge);
        EditText subComment = (EditText) findViewById(R.id.text_subComment);

        if(subName.getText().toString().isEmpty()){
            //Name field was left empty and therefore we can not continue;
            subName.setError("Name can not be empty");
            isGoodInput = false;
        } else {
            //Name field has value and will be sent to the new subscription.
            name = subName.getText().toString();
        }

        if(subCharge.getText().toString().isEmpty()){
            //Charge field is empty we have an error
            subCharge.setError(("Charge can not be empty"));
            isGoodInput = false;
        } else {
            charge = Float.parseFloat(subCharge.getText().toString());
        }

        if(!isGoodInput){
            return;
        }

        if(subComment.getText().toString().isEmpty()) {
            SubList.getInstance().add(
                    name, year, month, day, charge
            );
        } else {
            SubList.getInstance().add(
                    name, year, month, day, charge,
                    subComment.getText().toString()
            );
        }
        finish();
    }
}
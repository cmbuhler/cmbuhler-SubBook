/*
 * EditSubActivity
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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

/**
 * Represents the Activity used to edit a subscription
 *
 * @author Chase Buhler
 */
public class EditSubActivity extends AppCompatActivity {

    private Subscription newSub;

    private int position;
    private Subscription sub;
    private TextView textView;
    private EditText subName, subCharge, subComment;
    private Button addButton;
    private Calendar calendar;

    private TextView date;

    /**
     * Creates the EditSubActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sub);

        date = findViewById(R.id.sub_date);
        addButton = findViewById(R.id.button);
        subName = findViewById(R.id.text_subName);
        subCharge = findViewById(R.id.text_subCharge);
        subComment = findViewById(R.id.text_subComment);

        calendar = Calendar.getInstance();

        position = getIntent().getExtras().getInt("pos");

        sub = SubList.getInstance().get(position);
        newSub = sub.clone();

        subName.setText(sub.getName());
        subComment.setText(sub.getComment());
        subCharge.setText("" + sub.getCharge());

        textView = findViewById(R.id.textView);
        textView.setText(R.string.edit_sub);

        showDate();

        addButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                String name = "";
                float charge = 0.0f;
                boolean isGoodInput = true;

                if(subName.getText().toString().isEmpty()){
                    /* Name field was left empty and we can not continue */
                    subName.setError("Name can not be empty");
                    isGoodInput = false;
                } else {
                    /* Name field has value */
                    name = subName.getText().toString();
                }

                if(subCharge.getText().toString().isEmpty()){
                    /* Charge field is empty, we have an error */
                    subCharge.setError(("Charge can not be empty"));
                    isGoodInput = false;
                } else {
                    /* Charge is not empty */
                    charge = Float.parseFloat(subCharge.getText().toString());
                }

                /* See if we should add the edits or show user an error */
                if(!isGoodInput){
                    return;
                }

                /*
                 * If the edits are good capture them and set the old sub to
                 * The new sub
                 */
                newSub.setCharge(charge);
                newSub.setName(name);

                sub.setDay(newSub.getDay());
                sub.setMonth(newSub.getMonth());
                sub.setYear(newSub.getYear());
                sub.setCharge(newSub.getCharge());
                sub.setName(newSub.getName());
                sub.setComment(subComment.getText().toString());

                SubList.getInstance().updateFile();

                finish();
            }
        });
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
        Toast.makeText(getApplicationContext(), "Pick a Date", Toast.LENGTH_SHORT).show();
    }

    /**
     * Create the dialog that displays the DatePicker
     * @param id id
     * @return datePicker Dialog
     */
    @Override
    protected Dialog onCreateDialog(int id){
        if(id == 999){
            return new DatePickerDialog(this,
                    myDateListener, newSub.getYear(), newSub.getMonth(), newSub.getDay());
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener myDateListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    newSub.setYear(i);
                    newSub.setMonth(i1);
                    newSub.setDay(i2);
                    showDate();
                }
            };

    /**
     * Shows the date in the TextView
     */
    private void showDate(){
        date.setText(new StringBuilder().append(Subscription.getMonth(newSub.getMonth()))
                .append(" " + newSub.getDay() + ", " + newSub.getYear()));
    }
}

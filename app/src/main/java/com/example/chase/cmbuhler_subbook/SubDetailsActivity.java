/*
 * SubDetailsActivity
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
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Represents the activity used to view details about a Subscription
 *
 * @author Chase Buhler
 */
public class SubDetailsActivity extends AppCompatActivity {

    private Subscription sub;
    private int position;
    private TextView nameView;
    private TextView dateView;
    private TextView chargeView;
    private TextView commentView;

    /**
     * Create the details activity
     * @param savedInstanceState only used by super
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_details);

        position = this.getIntent().getExtras().getInt("sub_pos");
        sub = SubList.getInstance().get(position);

        nameView = findViewById(R.id.subNameTextView);
        dateView = findViewById(R.id.subDateValue);
        chargeView = findViewById(R.id.subChargeValue);
        commentView = findViewById(R.id.subCommentValue);

        Button deleteButton = findViewById(R.id.buttonDelete);
        Button editButton = findViewById(R.id.buttonEdit);

        final Context context = this;
        deleteButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View deleteButton){

                /*
                Taken https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android
                Retrieved 2018-01-28
                 */
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure you want to delete this subscription?\nThis " +
                        "is permanent and there is no going back.");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SubList.getInstance().remove(position);
                                finish();
                            }
                        });
                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        editButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View button){
                Intent intent = new Intent(context, EditSubActivity.class);

                intent.putExtra("pos", position);

                startActivity(intent);
            }
        });
    }

    /**
     * On the start of the activity we gather the required details to display
     */
    @Override
    public void onStart(){
        super.onStart();

        nameView.setText(sub.getName());

        String date = "" + Subscription.getMonth(sub.getMonth()) + " "
                + sub.getDay() + ", " + sub.getYear();
        dateView.setText(date);

        commentView.setText(sub.getComment());

        String chargeWithSign = "$"+sub.getCharge();
        chargeView.setText(chargeWithSign);
    }
}

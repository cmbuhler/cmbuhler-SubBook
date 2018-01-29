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

public class SubDetailsActivity extends AppCompatActivity {

    private String name;
    private String comment;
    private int year, month, day;
    private float charge;
    private int position;

    private TextView nameView;
    private TextView dateView;
    private TextView chargeView;
    private TextView commentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_details);


        name = this.getIntent().getExtras().getString("sub_name");
        comment = this.getIntent().getExtras().getString("sub_comment");
        year = this.getIntent().getExtras().getInt("sub_year");
        month = this.getIntent().getExtras().getInt("sub_month");
        day = this.getIntent().getExtras().getInt("sub_day");
        charge = this.getIntent().getExtras().getFloat("sub_charge");
        position = this.getIntent().getExtras().getInt("sub_pos");

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

    @Override
    public void onStart(){
        super.onStart();
        nameView.setText(name);
        String date = "" + Subscription.getMonth(month) + " " + day +
                ", " + year;
        dateView.setText(date);
        commentView.setText(comment);
        String chargewithsign = "$"+charge;
        chargeView.setText(chargewithsign);


    }
}

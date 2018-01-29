package com.example.chase.cmbuhler_subbook;

/*
 * Created by Chase Buhler
 *
 * Additional examples and pieces of code from
 * tutorialspoint.com:
 *
 * DatePicker example:
 * Retrieved 2018-01-22
 * https://www.tutorialspoint.com/android/android_datepicker_control.htm
 *
 */


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;

import android.view.View;
import android.widget.DatePicker;


import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;


public class AddNewSubActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView date;
    private int year, month, day;
    static final String STATE_YEAR = "subYear";
    static final String STATE_MONTH = "subMonth";
    static final String STATE_DAY = "subDay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sub);

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


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        //Save current date for persistence between activity restarts
        savedInstanceState.putInt(STATE_YEAR, year);
        savedInstanceState.putInt(STATE_MONTH, month);
        savedInstanceState.putInt(STATE_DAY, day);

        //Call the super class state to still save other state info
        super.onSaveInstanceState(savedInstanceState);
    }

    public void setDate(View view){
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT).show();
    }
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
     * Called when the add button is pressed.
     * Checks to make sure the entered values are
     * appropriate and then adds a new subscription
     * object to the SubList and finishes the Activity.
     * If some of the entered values are bad, the user
     * is notified and asked to re enter the bad values.
     * @param view view Clicked
     */
    public void addNewSub(View view){
        String name = "";
        String comment = "";
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_sub_menu, menu);
        return true;
    }

    private void showDate(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
        date.setText(new StringBuilder().append(Subscription.getMonth(month)).append(" " + day +
                ", " + year));
    }




}

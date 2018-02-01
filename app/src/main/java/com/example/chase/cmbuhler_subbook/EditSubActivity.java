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
 * Created by Chase on 2018-01-29.
 */

public class EditSubActivity extends AppCompatActivity {

    private Subscription oldSub;

    private int position;
    private Subscription sub;
    private TextView textView;
    private EditText subName, subCharge, subComment;
    private Button addButton;
    private Calendar calendar;

    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sub);
        date = findViewById(R.id.sub_date);
        addButton = findViewById(R.id.button);
        calendar = Calendar.getInstance();
        subName = findViewById(R.id.text_subName);
        subCharge = findViewById(R.id.text_subCharge);
        subComment = findViewById(R.id.text_subComment);

        //TODO Add add button listener

        position = getIntent().getExtras().getInt("pos");

        sub = SubList.getInstance().get(position);
        oldSub = sub.clone();

        subName.setText(sub.getName());
        subComment.setText(sub.getComment());
        subCharge.setText("" + sub.getCharge());

        textView = findViewById(R.id.textView);
        textView.setText(R.string.edit_sub);

        showDate();
    }

    /*
     * DatePicker example:
     * Retrieved 2018-01-22
     * https://www.tutorialspoint.com/android/android_datepicker_control.htm
     */
    public void setDate(View view){
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id == 999){
            return new DatePickerDialog(this,
                    myDateListener, sub.getYear(), sub.getMonth(), sub.getDay());
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    sub.setYear(i);
                    sub.setMonth(i1);
                    sub.setDay(i2);
                    showDate();
                }
            };

    private void showDate(){
        date.setText(new StringBuilder().append(Subscription.getMonth(sub.getMonth()))
                .append(" " + sub.getDay() + ", " + sub.getYear()));
    }

    @Override
    public void onDestroy(){
        super.onDestroy();


    }
}

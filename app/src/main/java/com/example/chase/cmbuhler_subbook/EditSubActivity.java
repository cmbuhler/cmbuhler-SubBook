package com.example.chase.cmbuhler_subbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Chase on 2018-01-29.
 */

public class EditSubActivity extends AppCompatActivity {

    private int position;
    private Subscription sub;
    private TextView textView;

    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sub);
        date = findViewById(R.id.sub_date);

        position = getIntent().getExtras().getInt("pos");

        sub = SubList.getInstance().get(position);

        textView = findViewById(R.id.textView);
        textView.setText(R.string.edit_sub);
    }

    private void showDate(){
        date.setText(new StringBuilder().append(Subscription.getMonth(sub.getMonth()))
                .append(" " + sub.getDay() + ", " + sub.getYear()));
    }
}

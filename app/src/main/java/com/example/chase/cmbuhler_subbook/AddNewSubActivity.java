package com.example.chase.cmbuhler_subbook;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import java.lang.reflect.Field;

public class AddNewSubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sub);

        NumberPicker yearPicker = findViewById(R.id.year_num_picker);

        yearPicker.setMaxValue(2020);
        yearPicker.setValue(2018);
        yearPicker.setMinValue(1970);
        yearPicker.setBackgroundColor(0xff000000);
        yearPicker.setWrapSelectorWheel(false);
        setNumberPickerTextColor(yearPicker, 0xffffffff);
        yearPicker.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_sub_menu, menu);
        return true;
    }

    public void onYearClick(View view){
        NumberPicker yearPicker = findViewById(R.id.year_num_picker);
        yearPicker.setVisibility(View.VISIBLE);
    }
    public void onYearPickerClick(View view){
        NumberPicker yearPicker = findViewById(R.id.year_num_picker);
        TextView year = findViewById(R.id.date_year_textView);


        year.setText("" + yearPicker.getValue());
        year.setTextColor(0xff000000);
        yearPicker.setVisibility(View.GONE);
    }


    /**
     * Change the text color of the number picker view.
     *
     * Provided by stack overflow user Simon: https://stackoverflow.com/users/1383790/simon
     * At: https://stackoverflow.com/questions/22962075/change-the-text-color-of-numberpicker
     * Retrieved 2018-01-17
     * @param numberPicker
     * @param color
     * @return
     */
    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color)
    {
        final int count = numberPicker.getChildCount();
        for(int i = 0; i < count; i++){
            View child = numberPicker.getChildAt(i);
            if(child instanceof EditText){
                try{
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint)selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText)child).setTextColor(color);
                    numberPicker.invalidate();
                    return true;
                }
                catch(NoSuchFieldException e){
                    Log.w("setNumPickerTextColor", e);
                }
                catch(IllegalAccessException e){
                    Log.w("setNumPickerTextColor", e);
                }
                catch(IllegalArgumentException e){
                    Log.w("setNumPickerTextColor", e);
                }
            }
        }
        return false;
    }

}

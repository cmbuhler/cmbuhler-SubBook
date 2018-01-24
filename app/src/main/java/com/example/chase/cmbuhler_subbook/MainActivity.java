package com.example.chase.cmbuhler_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView subList;
    private TextView totalChargeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalChargeValue = findViewById(R.id.totalChargeValue);
        subList = findViewById(R.id.listView);
    }

    @Override
    protected void onStart(){
        super.onStart();

        updateList();
    }

    private void updateList(){

        totalChargeValue.setText("$" + SubList.getInstance().getTotalCharge());
        SubAdapter adapter = new SubAdapter(this, SubList.getInstance().getSubList());
        subList.setAdapter(adapter);
    }

    /**
     * Inflate the main menu
     * @param menu menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Start the activity associated with a particular menu press.
     * @param item Menu item selected.
     * @return true on success, else false;
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            // Add new sub menu button is pressed.
            case R.id.add_new_sub:
                startNewSubActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startNewSubActivity(){
        Intent intent = new Intent(this, AddNewSubActivity.class);
        startActivity(intent);
    }
}

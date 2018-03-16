package com.example.myapplication2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String PREFERENCES_NAME = "myPreferences";
    private static final String PREFERENCES_TEXT_FIELD = "textFieldMass";
    private static final String PREFERENCES_TEXT_FIELD_TWO="textFieldHeight";
    private SharedPreferences preferences;
    EditText et;
    EditText et2;
    TextView tv;
    Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = getSharedPreferences(PREFERENCES_NAME, MainActivity.MODE_PRIVATE);
        et = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        tv = (TextView) findViewById(R.id.units);
        sw = (Switch) findViewById(R.id.switch1);
        tv.setText("Kg/M");
        restoreData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void calculateBMI(View view){
        BMI ob;
        int mass = 0;
        double height = 0;
        double bmi = 0;
        String result;
        String color;

        if(!et.getText().toString().equals(""))
            mass = Integer.parseInt(et.getText().toString());
        if(!et2.getText().toString().equals(""))
            height =  Double.parseDouble(et2.getText().toString());

        if(sw.isChecked())
            ob = new BMIForFtIb(mass, height);
        else
            ob = new BMIForKgM(mass, height);

        try{
            ob.areDataCorrect();
            bmi = ob.calculateBMI();
            result = getString(R.string.BMI_string) + String.format("%.2f", bmi);
        }
        catch(IllegalArgumentException e){
            result = getString(R.string.wrong_parameters);
        }

        color = setColor(bmi);

        Intent intent = new Intent(getBaseContext(), ShowBMI.class);
        intent.putExtra("stringToShow",result);
        intent.putExtra("color",color);
        startActivity(intent);
    }

    public String setColor(double bmi){
        String color = "";

        if(bmi>30)
            color = "#FF0000";
        else if(bmi>25 || bmi<18.5)
            color = "#FFD700";
        else
            color = "#32CD32";

        return color;
    }

    public void changeUnit(View view) {
        et.setText(R.string.null_string);
        et2.setText(R.string.null_string);

        if(sw.isChecked())
            tv.setText(R.string.stupid_units);
        else
            tv.setText(R.string.normal_units);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this, InfoAboutAuthor.class);
                startActivity(intent);
                return true;

            case R.id.saveButton:
                saveData();
                showToast(getString(R.string.data_saved));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void saveData() {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        String editTextMass = et.getText().toString();
        String editTextHeight = et2.getText().toString();
        preferencesEditor.putString(PREFERENCES_TEXT_FIELD, editTextMass);
        preferencesEditor.putString(PREFERENCES_TEXT_FIELD_TWO,editTextHeight);
        preferencesEditor.commit();
    }

    private void restoreData() {
        String massFromPreferences = preferences.getString(PREFERENCES_TEXT_FIELD, "");
        String heightFromPreferences = preferences.getString(PREFERENCES_TEXT_FIELD_TWO, "");
        et.setText(massFromPreferences);
        et2.setText(heightFromPreferences);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
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
    private static final String PREFERENCES_TEXT_FIELD_TWO = "textFieldHeight";
    public static final String KEY_FOR_RESULT = "stringToShow";
    public static final String KEY_FOR_COLOR = "color";
    private SharedPreferences preferences;
    EditText etMass;
    EditText etHeight;
    TextView tvUnits;
    Switch swUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = getSharedPreferences(PREFERENCES_NAME, MainActivity.MODE_PRIVATE);
        initViews();
        restoreData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void initViews(){
        etMass = (EditText) findViewById(R.id.editTextMass);
        etHeight = (EditText) findViewById(R.id.editTextHeight);
        tvUnits = (TextView) findViewById(R.id.units);
        swUnits = (Switch) findViewById(R.id.switchUnits);
        tvUnits.setText(R.string.normal_units);
    }

    public void calculateBMI(View view){
        BMI ob;
        double mass = 0;
        double height = 0;
        double bmi = 0;
        String result, color;

        if(!etMass.getText().toString().equals(""))
            mass = Double.parseDouble(etMass.getText().toString());
        if(!etHeight.getText().toString().equals(""))
            height = Double.parseDouble(etHeight.getText().toString());

        if(swUnits.isChecked())
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

        color = ob.setColor(bmi);

        Intent intent = new Intent(getBaseContext(), ShowBMI.class);
        intent.putExtra(KEY_FOR_RESULT, result);
        intent.putExtra(KEY_FOR_COLOR, color);
        startActivity(intent);
    }

    public void changeUnit(View view) {
        etMass.setText(R.string.null_string);
        etHeight.setText(R.string.null_string);

        if(swUnits.isChecked())
            tvUnits.setText(R.string.stupid_units);
        else
            tvUnits.setText(R.string.normal_units);
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
        String editTextMass = etMass.getText().toString();
        String editTextHeight = etHeight.getText().toString();
        preferencesEditor.putString(PREFERENCES_TEXT_FIELD, editTextMass);
        preferencesEditor.putString(PREFERENCES_TEXT_FIELD_TWO, editTextHeight);
        preferencesEditor.commit();
    }

    private void restoreData() {
        String massFromPreferences = preferences.getString(PREFERENCES_TEXT_FIELD, "");
        String heightFromPreferences = preferences.getString(PREFERENCES_TEXT_FIELD_TWO, "");
        etMass.setText(massFromPreferences);
        etHeight.setText(heightFromPreferences);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
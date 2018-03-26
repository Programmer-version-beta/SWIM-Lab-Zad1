package com.example.myapplication2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class ShowBMI extends AppCompatActivity {

    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bmi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        et = (EditText) findViewById(R.id.showBMIValue);
    }

    @Override
    protected void onStart(){
        super.onStart();
        getDelegate().onStart();

        String toShow = getIntent().getStringExtra("stringToShow");
        String color = getIntent().getStringExtra("color");
        et.setText(toShow);
        et.setTextColor(Color.parseColor(color));
    }

}

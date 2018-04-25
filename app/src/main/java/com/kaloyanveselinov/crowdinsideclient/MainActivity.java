package com.kaloyanveselinov.crowdinsideclient;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean isLogging = sharedPreferences.getBoolean(getString(R.string.log_key), false);



        FloatingActionButton fab = findViewById(R.id.fab);
        informLogging(isLogging, fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Boolean isLogging = sharedPreferences.getBoolean(getString(R.string.log_key), false);
                editor.putBoolean("log", !isLogging);
                editor.apply();
                informLogging(!isLogging, view);
            }
        });

//        setupSharedPreferences();
    }

//    private void setupSharedPreferences() {
//        mLogger.setLogWifi(sharedPreferences.getBoolean(getString(R.string.log_wifi), false));
//        mLogger.setLogAccelerometer(sharedPreferences.getBoolean(getString(R.string.log_acc), false));
//        mLogger.setLogMagnetometer(sharedPreferences.getBoolean(getString(R.string.log_magn), false));
//        mLogger.setLogGyroscope(sharedPreferences.getBoolean(getString(R.string.log_gyro), false));
//        mLogger.setLogGPS(sharedPreferences.getBoolean(getString(R.string.log_gps), false));
//    }

    private void informLogging(Boolean isLogging, View view){
        if (isLogging)
            Snackbar.make(view, R.string.logging, Snackbar.LENGTH_LONG).show();
        else
            Snackbar.make(view, R.string.no_logging, Snackbar.LENGTH_LONG).show();
    }
}

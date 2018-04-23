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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO add sharedPreferences inversion
                if(sharedPreferences.getBoolean(getString(R.string.log_key), false))
                    Snackbar.make(view, R.string.logging, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                else
                    Snackbar.make(view, R.string.no_logging, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
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
}

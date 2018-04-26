package com.kaloyanveselinov.crowdinsideclient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final Context context = this;

        Boolean isLogging = sharedPreferences.getBoolean(getString(R.string.log_key), false);

        FloatingActionButton fab = findViewById(R.id.fab);
        informLogging(isLogging, fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Boolean isLogging = sharedPreferences.getBoolean(getString(R.string.log_key), false);

                // TODO launch dataCollecionService in a new thread
                Intent intent = new Intent(context, DataCollectionService.class);
                if (!isLogging) startService(intent);
                else stopService(intent);
                editor.putBoolean("log", !isLogging);
                editor.apply();
                informLogging(!isLogging, view);
            }
        });

    }

    private void informLogging(Boolean isLogging, View view){
        if (isLogging)
            Snackbar.make(view, R.string.logging, Snackbar.LENGTH_LONG).show();
        else
            Snackbar.make(view, R.string.no_logging, Snackbar.LENGTH_LONG).show();
    }
}

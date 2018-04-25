package com.kaloyanveselinov.crowdinsideclient;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.PreferenceFragmentCompat;

public class MainActivityFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_crowdinside);
        initPreferencesForAvailableSensors();
    }

    /**
     * This method disables the preferences for sensors that are not available on the device
     */
    private void initPreferencesForAvailableSensors() {
        Activity activity = getActivity();
        if (activity != null) {
            SensorManager sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
            if (sensorManager != null) {
                if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null)
                    disableSensor(R.string.log_wifi);
                if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) == null)
                    disableSensor(R.string.log_magn);
                if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) == null)
                    disableSensor(R.string.log_gyro);
            }
        }
    }

    private void disableSensor(int log_id) {
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference(getString(log_id));
        if (checkBoxPreference != null) checkBoxPreference.setEnabled(false);
    }

}

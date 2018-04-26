package com.kaloyanveselinov.crowdinsideclient.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class SensorListener implements SensorEventListener {
    private OnSensorDataCallback onSensorDataCallback;

    SensorListener(OnSensorDataCallback onSensorDataCallback) {
        this.onSensorDataCallback = onSensorDataCallback;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        SensorSample sensorSample = new SensorSample(event);
        onSensorDataCallback.onSensorSample(sensorSample);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}

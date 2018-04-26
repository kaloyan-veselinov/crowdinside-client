package com.kaloyanveselinov.crowdinsideclient.sensors;

import android.hardware.SensorEvent;

import org.json.JSONException;
import org.json.JSONObject;

public class SensorSample {
    private float xSensorValue, ySensorValue, zSensorValue;
    private long timestamp;
    private int sensorType;

    SensorSample(SensorEvent sensorEvent) {
        xSensorValue = sensorEvent.values[0];
        ySensorValue = sensorEvent.values[1];
        zSensorValue = sensorEvent.values[2];
        timestamp = System.currentTimeMillis();
        sensorType = sensorEvent.sensor.getType();
    }

    JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("timestamp", timestamp);
            jsonObject.put("sensorType", sensorType);
            jsonObject.put("xSensorValue", xSensorValue);
            jsonObject.put("ySensorValue", ySensorValue);
            jsonObject.put("zSensorValue", zSensorValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public int getSensorType() {
        return sensorType;
    }
}

package com.kaloyanveselinov.crowdinsideclient.sensors;

import android.net.wifi.ScanResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WifiSample {
    private List<ScanResult> scanResults;
    private long timestamp;

    WifiSample(List<ScanResult> scanResults) {
        this.scanResults = scanResults;
        this.timestamp = System.currentTimeMillis();
    }

    JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonObject.put("timestamp", timestamp);
            for (ScanResult scanResult : scanResults) {
                WifiAPReading wifiAPData = new WifiAPReading(scanResult.BSSID, scanResult.level);
                jsonArray.put(wifiAPData);
            }
            jsonObject.put("wifiAPData", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    class WifiAPReading {
        private String bssid;
        private int level;

        WifiAPReading(String bssid, int level) {
            this.bssid = bssid;
            this.level = level;
        }

        JSONObject toJSON() {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("bssid", bssid);
                jsonObject.put("level", level);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }
    }
}

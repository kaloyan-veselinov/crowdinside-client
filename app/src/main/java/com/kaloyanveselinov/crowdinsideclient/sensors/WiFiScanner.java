package com.kaloyanveselinov.crowdinsideclient.sensors;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.List;

public class WiFiScanner extends BroadcastReceiver {

    private WifiManager wifiManager;
    private Context context;
    private OnWifiDataCallback onWifiDataCallback;

    public WiFiScanner(@NonNull Context context, OnWifiDataCallback onWifiDataCallback) {
        super();
        this.context = context;
        this.onWifiDataCallback = onWifiDataCallback;
        this.wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (wifiManager != null) {
            if (!wifiManager.isWifiEnabled()) {
                Toast.makeText(context, "WiFi not enabled", Toast.LENGTH_SHORT).show();
            }
            context.registerReceiver(this, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
            wifiManager.startScan();
        } else throw new IllegalArgumentException();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        List<ScanResult> scanResultList = wifiManager.getScanResults();
        WifiSample wifiSample = new WifiSample(scanResultList);
        onWifiDataCallback.onWifiSample(wifiSample);
        wifiManager.startScan();
    }

    public void endHandler() {
        context.unregisterReceiver(this);
    }
}

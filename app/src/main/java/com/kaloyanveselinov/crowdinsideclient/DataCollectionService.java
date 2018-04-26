package com.kaloyanveselinov.crowdinsideclient;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class DataCollectionService extends Service {

    private LoggerHandler mLoggerHandler;

    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_FOREGROUND);
        thread.start();
        Looper mLoggerLooper = thread.getLooper();
        mLoggerHandler = new LoggerHandler(mLoggerLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Logging service starting", Toast.LENGTH_SHORT).show();
        this.updatePreferences();

        Message msg = mLoggerHandler.obtainMessage();
        msg.arg1 = startId;
        mLoggerHandler.sendMessage(msg);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mLoggerHandler.endLogging();
        Toast.makeText(this, "Logging service done", Toast.LENGTH_SHORT).show();
    }

    public void updatePreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mLoggerHandler.setLogWifi(sharedPreferences.getBoolean(getString(R.string.log_wifi), false));
        mLoggerHandler.setLogAccelerometer(sharedPreferences.getBoolean(getString(R.string.log_acc), false));
        mLoggerHandler.setLogMagnetometer(sharedPreferences.getBoolean(getString(R.string.log_magn), false));
        mLoggerHandler.setLogGyroscope(sharedPreferences.getBoolean(getString(R.string.log_gyro), false));
        mLoggerHandler.setLogGPS(sharedPreferences.getBoolean(getString(R.string.log_gps), false));
    }

    private final class LoggerHandler extends Handler {
        private boolean logWifi;
        private boolean logAccelerometer;
        private boolean logMagnetometer;
        private boolean logGyroscope;
        private boolean logGPS;
        private boolean end = false;

        LoggerHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            while (!end) {
                // TODO add logging
                Log.i("workerThread", "Meow");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            stopSelf(msg.arg1);
        }

        void setLogWifi(boolean logWifi) {
            this.logWifi = logWifi;
        }

        void setLogAccelerometer(boolean logAccelerometer) {
            this.logAccelerometer = logAccelerometer;
        }

        void setLogMagnetometer(boolean logMagnetometer) {
            this.logMagnetometer = logMagnetometer;
        }

        void setLogGyroscope(boolean logGyroscope) {
            this.logGyroscope = logGyroscope;
        }

        void setLogGPS(boolean logGPS) {
            this.logGPS = logGPS;
        }

        void endLogging() {
            this.end = true;
        }
    }

}

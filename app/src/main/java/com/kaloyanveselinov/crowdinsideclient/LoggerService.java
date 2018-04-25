package com.kaloyanveselinov.crowdinsideclient;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class LoggerService extends IntentService {

    private static final String LOG_WIFI = "intent_log_wifi";
    private static final String LOG_ACCELEROMETER = "intent_log_acc";
    private static final String LOG_MAGNETOMETER = "intent_log_magn";
    private static final String LOG_GYROSCOPE = "intent_log_gyro";
    private static final String LOG_GPS = "intent_log_gps";

    public LoggerService() {
        super("LoggerService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startLogging(Context context) {
        Intent intent = new Intent(context, LoggerService.class);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Boolean log_wifi = sharedPreferences.getBoolean(context.getString(R.string.log_wifi), false);
        Boolean log_acc = sharedPreferences.getBoolean(context.getString(R.string.log_acc), false);
        Boolean log_magn = sharedPreferences.getBoolean(context.getString(R.string.log_magn), false);
        Boolean log_gyro = sharedPreferences.getBoolean(context.getString(R.string.log_gyro), false);
        Boolean log_gps = sharedPreferences.getBoolean(context.getString(R.string.log_gps), false);

        intent.putExtra(LOG_WIFI, log_wifi);
        intent.putExtra(LOG_ACCELEROMETER, log_acc);
        intent.putExtra(LOG_MAGNETOMETER, log_magn);
        intent.putExtra(LOG_GYROSCOPE, log_gyro);
        intent.putExtra(LOG_GPS, log_gps);

        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final Boolean log_wifi = intent.getBooleanExtra(LOG_WIFI, false);
            final Boolean log_acc = intent.getBooleanExtra(LOG_ACCELEROMETER, false);
            final Boolean log_magn = intent.getBooleanExtra(LOG_MAGNETOMETER, false);
            final Boolean log_gyro = intent.getBooleanExtra(LOG_GYROSCOPE, false);
            final Boolean log_gps = intent.getBooleanExtra(LOG_GPS, false);
            handleAction(log_wifi, log_acc, log_magn, log_gyro, log_gps);
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleAction(Boolean log_wifi, Boolean log_acc, Boolean log_magn, Boolean log_gyro, Boolean log_gps) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

}

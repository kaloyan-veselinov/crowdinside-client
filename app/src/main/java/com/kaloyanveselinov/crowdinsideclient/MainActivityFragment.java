package com.kaloyanveselinov.crowdinsideclient;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_crowdinside);
    }
}

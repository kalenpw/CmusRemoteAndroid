package com.kalenpw.cmusremote;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by kalenpw on 3/16/17.
 */

public class PrefFragment extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    SharedPreferences.OnSharedPreferenceChangeListener _Listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        AppSettings.updateAppSettings();
        NotificationUtils.handleDisplayNotifications();
    }
}

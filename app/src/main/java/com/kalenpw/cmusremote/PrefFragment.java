package com.kalenpw.cmusremote;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by kalenpw on 3/16/17.
 */

public class PrefFragment extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}

package com.kalenpw.cmusremote;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by kalenpw on 3/17/17.
 */

public class AppSettings {
    public static boolean NOTIFICATION_TURNED_ON;

    private static Context _Context;

    public static void setContext(Context value){
        _Context = value;
    }
    /**
     * Updates the app settings from those entered in preferences menu
     * @param context
     */
    public static void updateAppSettings(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        NOTIFICATION_TURNED_ON = preferences.getBoolean("pref_notification", false);
    }

    /**
     * Updates the app settings based off those entered in preferences menu
     */
    public static void updateAppSettings(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(_Context);
        NOTIFICATION_TURNED_ON = preferences.getBoolean("pref_notification", false);
    }
}

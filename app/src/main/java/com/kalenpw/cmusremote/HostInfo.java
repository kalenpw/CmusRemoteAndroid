package com.kalenpw.cmusremote;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by kalenpw on 3/16/17.
 */

public class HostInfo {
    public static String USER_NAME = "";
    public static String PASSWORD = "";
    public static int PORT = -1;
    public static String HOST = "";

    /**
     * Updates the user information to match what is entered in preferences menu
     * @param Context context - context of app
     */
    public static void updateUserInfo(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        USER_NAME = preferences.getString("pref_username", "USER");
        PASSWORD = preferences.getString("pref_password", "hunter2");
        PORT = Integer.parseInt(preferences.getString("pref_port", "22"));
        HOST = preferences.getString("pref_host", "0.0.0.0");
    }
}

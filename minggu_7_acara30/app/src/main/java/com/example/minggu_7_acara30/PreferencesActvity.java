package com.example.minggu_7_acara30;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.minggu_7_acara30.LoginActivity;
import android.preference.PreferenceManager;

public class PreferencesActvity {
    static final String KEY_USER_REGISTER ="user", KEY_PASS_REGISTER ="pass";
    static final String KEY_USERNAME_LOGIN = "Username_logged_in";
    static final String KEY_STATUS_LOGIN = "Status_logged_in";
    Context context;

    PreferencesActvity(Context context) {
        this.context = context;
    }

    public static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    public static void setRegisteredUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_REGISTER, username);
        editor.apply();
    }

    public static String getRegisteredUser(Context context){
        return getSharedPreference(context).getString(KEY_USER_REGISTER,"");
    }


    public static void setRegisteredPass(Context context, String password){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_PASS_REGISTER, password);
        editor.apply();
    }

    public static String getRegisteredPass(Context context){
        return getSharedPreference(context).getString(KEY_PASS_REGISTER,"");
    }


    public static void setLoggedInUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME_LOGIN, username);
        editor.apply();
    }

    public static String getLoggedInUser(Context context){
        return getSharedPreference(context).getString(KEY_USERNAME_LOGIN,"");
    }


    public static void setLoggedInStatus(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_STATUS_LOGIN,status);
        editor.apply();
    }

    public static boolean getLoggedInStatus(Context context){
        return getSharedPreference(context).getBoolean(KEY_STATUS_LOGIN,false);
    }


    public static void clearLoggedInUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_USERNAME_LOGIN);
        editor.remove(KEY_STATUS_LOGIN);
        editor.apply();
    }
}

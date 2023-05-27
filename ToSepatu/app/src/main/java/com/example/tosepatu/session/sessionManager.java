package com.example.tosepatu.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.tosepatu.model.login.LoginData;

import java.util.HashMap;

public class sessionManager {

    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String IS_LOGGED_IN = "isLoggedIn";
    private static final String ID = "id";
    private static final String ID_USERS = "id_users";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String NO_TELP = "no_telp";
    private static final String FOTO = "foto";
    private static final String WILAYAH_ID = "wilayah_id";

    public sessionManager(Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(LoginData user){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(ID, user.getId());
        editor.putString(ID_USERS, user.getIdUsers());
        editor.putString(EMAIL, user.getEmail());
        editor.putString(USERNAME, user.getUsername());
        editor.putString(NO_TELP, user.getNoTelp());
        editor.putString(FOTO, user.getFoto());
        editor.putString(WILAYAH_ID, user.getWilayahId().toString());
        editor.commit();
    }
    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(ID, sharedPreferences.getString(ID,null));
        user.put(ID_USERS, sharedPreferences.getString(ID_USERS,null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL,null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME,null));
        user.put(NO_TELP, sharedPreferences.getString(NO_TELP,null));
        user.put(FOTO, sharedPreferences.getString(FOTO,null));
        user.put(WILAYAH_ID, sharedPreferences.getString(WILAYAH_ID,null));
        return user;
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isloggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

}

package com.aasshh.user.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionHandler {
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "APP_DROID_AASSHH";
    private static final String IS_LOGGED_IN = "logged_in_status";
    private static final String LOGGED_IN_MOBILE = "logged_in_mobile";
    private static final String LOGGED_IN_USER = "logged_in_user"; //it will store User id
    private static final String DEVICE_TOKEN = "device_Token"; //it will store uid
    private static final String LOGGED_TOKEN = "login_Token"; //it will store uid
    private static final String LOGGED_USER_NAME = "login_Token"; //it will store uid
    String TAG = SessionHandler.class.getSimpleName();

    @SuppressLint("CommitPrefEdits")
    public SessionHandler(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void setIsLoggedIn(boolean status) {

        editor.putBoolean(IS_LOGGED_IN, status);
        editor.commit();
        Log.d(TAG, "setIsLoggedIn: Login status modified");
    }

    public void setLoggedInMobile(String mobile) {
        editor.putString(LOGGED_IN_MOBILE, mobile);
        editor.commit();
        Log.d(TAG, "setLoggedInMobile: Mobile number changed");
    }

    public void setLoggedUserName(String name) {
        editor.putString(LOGGED_USER_NAME, name);
        editor.commit();
        Log.d(TAG, "setLoggedUserName: Modified " + name);
    }

    public String getLoggedUserName() {
        return preferences.getString(LOGGED_USER_NAME, null);
    }

    public void setLoggedToken(String token) {
        editor.putString(LOGGED_TOKEN, token);
        editor.commit();
        Log.d(TAG, "setLoggedToken: Session access token modified");
    }

    public void setLoggedInUser(String id) {
        editor.putString(LOGGED_IN_USER, id);
        editor.commit();
        Log.d(TAG, "setLoggedInUser: Logged in device user changed");
    }

    public void setDeviceToken(String token) {
        editor.putString(DEVICE_TOKEN, token);
        editor.commit();
        Log.d(TAG, "setDeviceToken: Device token modified");
    }

    public String getDeviceToken() {
        return this.preferences.getString(DEVICE_TOKEN, null);
    }

    public boolean getIsLoggedIn() {
        return this.preferences.getBoolean(IS_LOGGED_IN, false);
    }

    public String getLoggedToken() {
        return this.preferences.getString(LOGGED_TOKEN, null);
    }

    public String getLoggedInMobile() {
        return this.preferences.getString(LOGGED_IN_MOBILE, null);
    }

    public String getLoggedInUser() {
        return this.preferences.getString(LOGGED_IN_USER, null);
    }
}

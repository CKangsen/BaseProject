package com.base.project;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by cks on 2017/7/19.
 */

public class ApplicationPrefsManager {

    public static final String SHARED_PREFERENCES_NAME = "name";

    private Context mContext;
    private static ApplicationPrefsManager instance;

    ApplicationPrefsManager(Context context){
        mContext = context;
    }

    public static ApplicationPrefsManager getInstance(Context context){
        if (instance == null){
            instance = new ApplicationPrefsManager(context);
        }
        return instance;
    }


    public int getInt(final String key, final int defaultValue) {
        final SharedPreferences prefs = mContext.getSharedPreferences(
                SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(key, defaultValue);
    }

    public long getLong(final String key, final long defaultValue) {
        final SharedPreferences prefs = mContext.getSharedPreferences(
                SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return prefs.getLong(key, defaultValue);
    }

    public boolean getBoolean(final String key, final boolean defaultValue) {
        final SharedPreferences prefs = mContext.getSharedPreferences(
                SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(key, defaultValue);
    }

    public String getString(final String key, final String defaultValue) {
        final SharedPreferences prefs = mContext.getSharedPreferences(
                SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, defaultValue);
    }

    public void putInt(final String key, final int value) {
        final SharedPreferences prefs = mContext.getSharedPreferences(
                SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void putLong(final String key, final long value) {
        final SharedPreferences prefs = mContext.getSharedPreferences(
                SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void putBoolean(final String key, final boolean value) {
        final SharedPreferences prefs = mContext.getSharedPreferences(
                SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void putString(final String key, final String value) {
        final SharedPreferences prefs = mContext.getSharedPreferences(
                SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void remove(final String key) {
        final SharedPreferences prefs = mContext.getSharedPreferences(
                SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
    }




}

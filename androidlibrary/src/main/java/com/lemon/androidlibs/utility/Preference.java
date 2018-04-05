package com.lemon.androidlibs.utility;

import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by lemon on 3/16/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class Preference {
    private static SharedPreferences preferences;

    public static void initPref(SharedPreferences sharedPreferences) {
        preferences=sharedPreferences;
    }

    public static String getString(String key) {
        return preferences.getString(key,null);
    }
    public static boolean getBoolean(String key) {
        return preferences.getBoolean(key,false);
    }
    public static int getInt(String key) {
        return preferences.getInt(key,0);
    }
    public static long getLong(String key) {
        return preferences.getLong(key,0);
    }
    public static float getFloat(String key) {
        return preferences.getFloat(key,0);
    }
    public static Set<String> getStringSet(String key) {
        return preferences.getStringSet(key,null);
    }

    public static void setString(String key,String value) {
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static void setBoolean(String key,boolean value) {
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    public static void setInt(String key,int value) {
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt(key,value);
        editor.apply();
    }
    public static void setLong(String key,Long value) {
        SharedPreferences.Editor editor=preferences.edit();
        editor.putLong(key,value);
        editor.apply();
    }
    public static void setFloat(String key,float value) {
        SharedPreferences.Editor editor=preferences.edit();
        editor.putFloat(key,value);
        editor.apply();
    }
    public static void setStringSet(String key,Set<String > value) {
        SharedPreferences.Editor editor=preferences.edit();
        editor.putStringSet(key,value);
        editor.apply();
    }

}

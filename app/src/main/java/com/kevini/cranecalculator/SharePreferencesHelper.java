package com.kevini.cranecalculator;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kevini on 2019/1/31.
 */

public class SharePreferencesHelper {
    public static boolean saveA(String a, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("a", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("a", a);
        editor.commit();
        return true;
    }


    public static String getA(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("a", Context.MODE_PRIVATE);
        String a = preferences.getString("a","-1");
        return a;
    }
    public static boolean saveD(String d, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("d", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("d", d);
        editor.commit();
        return true;
    }


    public static String getD(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("d", Context.MODE_PRIVATE);
        String d = preferences.getString("d","-1");
        return d;
    }
}

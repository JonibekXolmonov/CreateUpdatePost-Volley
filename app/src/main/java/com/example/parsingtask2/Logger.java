package com.example.parsingtask2;

import android.util.Log;

import com.example.parsingtask2.networking.VolleyHttp;


public class Logger {
    public static void d(String tag, String msg) {
        if (VolleyHttp.IS_TESTER) Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (VolleyHttp.IS_TESTER) Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (VolleyHttp.IS_TESTER) Log.v(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (VolleyHttp.IS_TESTER) Log.e(tag, msg);
    }
}

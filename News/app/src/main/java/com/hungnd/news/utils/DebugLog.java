package com.hungnd.news.utils;


import android.support.v4.BuildConfig;

public class DebugLog {
    public static String TAG = BuildConfig.APPLICATION_ID;

    // upload to store need set mDEBUG = false
    private static boolean mDEBUG = true;//BuildConfig.DEBUG;//false;//

    public static void I(String tag, String msg) {
        if (mDEBUG)
            android.util.Log.i(tag, msg != null ? msg : "");
    }

    public static void i(String tag, String msg) {
        I(tag, msg);
    }

    public static void E(String tag, String msg) {
        if (mDEBUG)
            android.util.Log.e(tag, msg != null ? msg : "");
    }

    public static void e(String tag, String msg) {
        E(tag, msg);
    }

    public static void D(String tag, String msg) {
        if (mDEBUG)
            android.util.Log.d(tag, msg != null ? msg : "");
    }
    public static void D(String tag, String msg, Exception e) {
        try {
            String msgShow;
            if(e != null){
                msgShow = msg + ": " + e.getMessage();
            } else {
                msgShow = msg + " null";
            }
            if (mDEBUG)
                android.util.Log.d(tag, msgShow );
        }catch (Exception ex){}
    }

    public static void d(String tag, String msg) {
        D(tag, msg);
    }

    public static void W(String tag, String msg) {
        if (mDEBUG)
            android.util.Log.w(tag, msg != null ? msg : "");
    }

    public static void w(String tag, String msg) {
        W(tag, msg);
    }

    public static void logD(Object... msg) {
        if(msg == null || msg.length == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Object s : msg) {
            if (s != null) {
                sb.append(s.toString());
                sb.append(" | ");
            }
        }
        d(TAG, sb.toString());
    }
    public static void logD(String msg, Exception ex) {
        if(ex != null){
            d(TAG, msg + ": " + ex.getMessage());
        } else {
            d(TAG, msg + " null");
        }
    }
    public static void logV(Object... msg) {
        StringBuilder sb = new StringBuilder();
        for (Object s : msg) {
            if (s != null) {
                sb.append(s.toString());
                sb.append(" | ");
            }
        }
        if (mDEBUG)
            android.util.Log.v(TAG, sb.toString());
    }

}

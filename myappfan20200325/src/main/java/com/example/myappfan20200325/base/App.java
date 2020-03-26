package com.example.myappfan20200325.base;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getmContext() {
        return mContext;
    }
}

package com.ring.mycollections;

import android.app.Application;

import com.ring.llog.LLog;

/**
 * Created by ring
 * on 15/12/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LLog.init(true);
    }
}

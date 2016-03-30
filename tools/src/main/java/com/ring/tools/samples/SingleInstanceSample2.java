package com.ring.tools.samples;

import android.content.Context;

/**
 * Created by ring
 * on 15/12/15.
 */
public class SingleInstanceSample2 {
    private static volatile SingleInstanceSample2 singleton;

    private SingleInstanceSample2(Context context) {

    }

    public static SingleInstanceSample2 getInstance(Context context) {
        if (singleton == null) {
            synchronized (SingleInstanceSample2.class) {
                singleton = new SingleInstanceSample2(context);
            }
        }
        return singleton;
    }
}

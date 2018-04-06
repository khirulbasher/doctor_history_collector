package com.lemon.doctorpointcollector;

import android.app.Application;
import android.support.multidex.MultiDex;

/**
 * Created by lemon on 4/6/2018.
 */

@SuppressWarnings("DefaultFileTemplate")
public class DexApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}

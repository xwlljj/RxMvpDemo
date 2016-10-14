package com.xwlljj.tim.demo;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by XieWei on 16/9/5.
 */
public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

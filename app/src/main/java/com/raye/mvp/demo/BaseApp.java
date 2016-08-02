package com.raye.mvp.demo;

import android.support.multidex.MultiDexApplication;

/**
 * Created by XieWei on 16/8/1.
 */
public class BaseApp extends MultiDexApplication {
    private static BaseApp app;

    public static BaseApp getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}

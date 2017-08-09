package com.base.project;

import android.app.Application;

/**
 * Created by pradmin on 2017/7/10.
 */

public class WakaIcloudApplication extends Application {
    public static final String TAG = WakaIcloudApplication.class.getSimpleName();

    private  ApplicationPrefsManager mApplicationPrefsManager;
    @Override
    public void onCreate() {
        super.onCreate();

        FactoryImpl.register(this.getApplicationContext());


    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


}

package com.base.project;

import android.content.Context;


/**
 * Created by pradmin on 2017/7/17.
 */

public abstract class Factory {

    private static volatile Factory sInstance;

    public static Factory get() {
        return sInstance;
    }

    protected static void setInstance(final Factory factory) {
        sInstance = factory;
    }

    public abstract Context getApplicationContext();
//    public abstract DBManager getDBManager();
//    public abstract CacheStatisticsManager getCacheStatisticsManager();
//    public abstract ApplicationPrefsManager getApplicationPrefsManager();
}

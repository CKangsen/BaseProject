package com.base.project;

import android.content.Context;

import com.afmobi.wakacloud.db.DBManager;

/**
 * Created by pradmin on 2017/7/17.
 */

public class FactoryImpl extends Factory {

    private Context mApplicationContext;
    private DBManager mDBManager;
    private CacheStatisticsManager mCacheStatisticsManager;
    private ApplicationPrefsManager mApplicationPrefsManager;

    private FactoryImpl() {
    }

    public static Factory register(final Context applicationContext) {

        final FactoryImpl factory = new FactoryImpl();
        Factory.setInstance(factory);
        factory.mApplicationContext = applicationContext;
        factory.mDBManager = DBManager.getInstance(applicationContext);
        factory.mCacheStatisticsManager = CacheStatisticsManager.getInstance();
        factory.mApplicationPrefsManager = ApplicationPrefsManager.getInstance(applicationContext);

        return factory;
    }

    @Override
    public Context getApplicationContext() {
        return mApplicationContext;
    }

    @Override
    public DBManager getDBManager() {
        return mDBManager;
    }

    @Override
    public CacheStatisticsManager getCacheStatisticsManager() {
        return mCacheStatisticsManager;
    }

    @Override
    public ApplicationPrefsManager getApplicationPrefsManager() {
        return mApplicationPrefsManager;
    }
}

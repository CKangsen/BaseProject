package com.base.project.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by pradmin on 2017/7/12.
 */

public class DBHelper extends SQLiteOpenHelper {


    private Context mApplicationContext;
    private final Object mDatabaseWrapperLock = new Object();
    private DBWrapper mDBWrapper;

    /**
     *
     */
    private static final String DATABASE_NAME = "base.db";
    /**
     *
     */
    private static final int DATABASE_VERSION = 1;




    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        mApplicationContext= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS ");
        onCreate(db);

    }


    public DBWrapper getDatabase() {

        synchronized (mDatabaseWrapperLock) {
            if (mDBWrapper == null) {
                mDBWrapper = new DBWrapper(mApplicationContext, getWritableDatabase());
            }
            return mDBWrapper;
        }
    }
}

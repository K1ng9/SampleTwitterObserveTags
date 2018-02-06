package com.example.vladkliuchak.sampleapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vladkliuchak.sampleapp.data.data_tables.TweetDBTable;
import com.example.vladkliuchak.sampleapp.data.data_tables.UserDBTable;
import com.soft.unikey.vkluchak.testtwitterapp.injection.ApplicationContext;

import javax.inject.Inject;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "test_tweet_android.db";
    public static final int DATABASE_VERSION = 1;

    @Inject
    public DataBaseOpenHelper(@ApplicationContext Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDBTable.SQL_CREATE_ENTRIES);
        db.execSQL(TweetDBTable.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
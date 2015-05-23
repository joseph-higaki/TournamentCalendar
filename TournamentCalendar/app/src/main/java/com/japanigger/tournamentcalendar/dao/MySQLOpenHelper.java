package com.japanigger.tournamentcalendar.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Student on 02/08/2014.
 */
public class MySQLOpenHelper extends SQLiteOpenHelper {
    //private const String databaseName = "myDatabase.db";

    private String sqlCreateUserTable = "create table match (id integer primary key autoincrement, " +
                                        "name text not null) ";
    private String sqlDropUserTable = " drop table if exists user ";
    public MySQLOpenHelper(Context context) {
        super(context, "myDatabaseNew.db", null, 1);
        Log.d(this.getClass().toString(), "Constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(this.getClass().toString(), "before onCreate");
        db.execSQL(sqlCreateUserTable);
        Log.d(this.getClass().toString(), "after onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlDropUserTable);
        onCreate(db);
        Log.d(this.getClass().toString(), "onUpgrade");
    }
}

package com.japanigger.tournamentcalendar.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Student on 02/08/2014.
 */
public class MySQLOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tournamentDB.db";
    private static final String TABLE_CITY = "city";

    private String sqlCreateCityTable =
            "CREATE TABLE city (id integer primary key autoincrement, name VARCHAR(60) not null)";

    private String sqlInsertCityTable = "INSERT INTO city (name) values ('Santiago'), ('Lima')";

    private String sqlDropCityTable = " drop table if exists city ";

    public MySQLOpenHelper(Context context, String name,
                           SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        Log.d(this.getClass().toString(), "Constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(this.getClass().toString(), "before onCreate");
        db.execSQL(sqlCreateCityTable);
        Log.d(this.getClass().toString(), "after onCreate");
        db.execSQL(sqlInsertCityTable);
        //Log.d(this.getClass().toString(), "after Insert");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlDropCityTable);
        onCreate(db);
        Log.d(this.getClass().toString(), "onUpgrade");
    }
}

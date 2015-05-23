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

    private String sqlCreateCityTable =
            "create table city (name VARCHAR(60) not null)";

    private String sqlInsertCityTable =
            "insert into city (name) values ('Lima', 'Santiago', 'Tarapoto')";

    private String sqlDropCityTable = " drop table if exists city ";

    public MySQLOpenHelper(Context context) {
        super(context, "myDatabaseNew.db", null, 1);
        Log.d(this.getClass().toString(), "Constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(this.getClass().toString(), "before onCreate");
        db.execSQL(sqlCreateCityTable);
        Log.d(this.getClass().toString(), "after onCreate");
        db.execSQL(sqlInsertCityTable);
        Log.d(this.getClass().toString(), "after Insert");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlDropCityTable);
        onCreate(db);
        Log.d(this.getClass().toString(), "onUpgrade");
    }
}

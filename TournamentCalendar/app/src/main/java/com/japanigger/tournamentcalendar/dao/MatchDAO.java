package com.japanigger.tournamentcalendar.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

//import com.example.student.sqllitedemo.domain.User;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 02/08/2014.
 */

/*
public class MatchDAO {
    private MySQLOpenHelper dbHelper;
    private SQLiteDatabase database;

    public MatchDAO(Context context) {
        dbHelper = new MySQLOpenHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<User>();
        String[] columns = {"id", "name"};
        Cursor cursor = database.query("user", columns, null, null, null, null, null);
        try {
            for (boolean hasItem = cursor.moveToFirst(); hasItem; hasItem = cursor.moveToNext()) {
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex("id")));
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                users.add(user);
            }
        } finally {
            cursor.close();
        }
        return users;
    }

    public void save(User user) {
        if (user != null){
            if (user.getId() == 0)
                //new
              add(user);
            else
                update(user);
        }
    }

    protected long add(User user){
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        long id =  database.insert("user", null, values);
        user.setId(id);
        return user.getId();
    }

    protected int update(User user) {
        ContentValues setValues = new ContentValues();
        setValues.put("name", user.getName());
        String whereStatement = "id = ?";
        String[] whereValues = { user.getId()+"" };

        int count =  database.update("user", setValues, whereStatement, whereValues);
        return count;

    }


    public void delete(int id) {
    }
}

*/
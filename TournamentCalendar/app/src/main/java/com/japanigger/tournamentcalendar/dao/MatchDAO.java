package com.japanigger.tournamentcalendar.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.japanigger.tournamentcalendar.data.Match;

//import com.example.student.sqllitedemo.domain.User;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 02/08/2014.
 */


public class MatchDAO {
    private MySQLOpenHelper dbHelper;
    private SQLiteDatabase database;

    public MatchDAO(Context context) {
        dbHelper = new MySQLOpenHelper(context,null,null,1);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /*public List<Match> getAll() {
        List<Match> matches = new ArrayList<Match>();
        String[] columns = {"id", "matchdate", "city", "team1", "team2"};
        Cursor cursor = database.query("match", columns, null, null, null, null, null);
        try {
            for (boolean hasItem = cursor.moveToFirst(); hasItem; hasItem = cursor.moveToNext()) {
                Match match = new Match();
                match.setId(cursor.getLong(cursor.getColumnIndex("id")));
                match.setDate(cursor.getString(cursor.getColumnIndex("matchdate")));
                matches.add(match);
            }
        } finally {
            cursor.close();
        }
        return match;
    }*/

    public void save(Match match) {
        if (match != null){
            if (match.getId() == 0)
                //new
              add(match);
            //else
                //update(match);
        }
    }

    protected long add(Match match){
        ContentValues values = new ContentValues();
        values.put("matchdate", match.getDate().toString());
        values.put("city", match.getLocation().getName());
        values.put("team1", match.getTeam1().getName());
        values.put("team2", match.getTeam2().getName());
        //add userid

        long id =  database.insert("match", null, values);
        match.setId(id);
        return match.getId();
    }

    /*protected int update(Match match) {
        ContentValues setValues = new ContentValues();
        setValues.put("name", match.getName());
        String whereStatement = "id = ?";
        String[] whereValues = { user.getId()+"" };

        int count =  database.update("user", setValues, whereStatement, whereValues);
        return count;

    }*/


    public void delete(int id) {
    }
}
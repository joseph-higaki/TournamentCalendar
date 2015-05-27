        package com.japanigger.tournamentcalendar.dao;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteException;
        import android.util.Log;

        import com.japanigger.tournamentcalendar.data.City;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Noro on 23/05/2015.
 */
public class CityDAO {
    private MySQLOpenHelper dbHelper;
    private SQLiteDatabase database;

    public CityDAO(Context context) {
        dbHelper = new MySQLOpenHelper(context, null, null, 1);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<City> getAll() {
        List<City> cities = new ArrayList<City>();
        String query = "SELECT id, name FROM city";

        try {
            open();
            Cursor cursor = database.rawQuery(query, null);
            for (boolean hasItem = cursor.moveToFirst(); hasItem; hasItem = cursor.moveToNext()) {
                City city = new City(cursor.getInt(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("name")));
                cities.add(city);
            }
            cursor.close();
        } catch (SQLiteException ex){
            Log.d(this.getClass().toString(), "ex---> " + ex.getMessage());

        }finally {

            close();
        }
        return cities;
    }

    public void updateCities(List<City> cities){
        try {
            open();
            // TRUNCATE TABLE CITY
            database.delete("city",null,null);
            for (int i = 0; i < cities.size(); i++) {
                ContentValues values = new ContentValues();
                values.put("id", cities.get(i).getId());
                values.put("name", cities.get(i).getName());
                database.insert("city", null, values);
            }
        }catch (SQLiteException ex){
            Log.d(this.getClass().toString(), "ex---> " + ex.getMessage());
        }finally {
            close();
        }
    }
}
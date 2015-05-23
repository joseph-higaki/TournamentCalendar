        package com.japanigger.tournamentcalendar.dao;

        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;

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
        dbHelper = new MySQLOpenHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<City> getAll() {
        List<City> cities = new ArrayList<City>();
        String[] columns = {"name"};
        Cursor cursor = database.query("city", columns, null, null, null, null, null);
        try {
            for (boolean hasItem = cursor.moveToFirst(); hasItem; hasItem = cursor.moveToNext()) {
                City city = new City(cursor.getString(cursor.getColumnIndex("name")));
                cities.add(city);
            }
        } finally {
            cursor.close();
        }
        return cities;
    }
}
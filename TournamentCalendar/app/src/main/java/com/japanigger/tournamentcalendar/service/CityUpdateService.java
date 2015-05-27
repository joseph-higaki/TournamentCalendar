package com.japanigger.tournamentcalendar.service;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import com.japanigger.tournamentcalendar.dao.rest.TaskGetCities;
import com.japanigger.tournamentcalendar.data.City;

import java.util.List;

/**
 * Created by Cesar on 26/05/2015.
 */
public class CityUpdateService extends IntentService implements TaskGetCities.OnGetCitiesTaskCompleted{
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public CityUpdateService(String name) {
        super(name);
    }

    public CityUpdateService(){
        super("CityUpdateService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        TaskGetCities task = new TaskGetCities(this);
        task.execute();
    }

    @Override
    public void onGetCitiesTaskCompleted(List<City> cities) {
        Toast.makeText(this,"Cities updated succesfully",Toast.LENGTH_LONG).show();
    }
}

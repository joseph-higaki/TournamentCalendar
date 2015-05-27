package com.japanigger.tournamentcalendar;

//import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.japanigger.tournamentcalendar.dao.CityDAO;
import com.japanigger.tournamentcalendar.dao.rest.TaskGetCities;
import com.japanigger.tournamentcalendar.data.City;

import java.util.List;

public class MatchActivity extends ActionBarActivity implements TaskGetCities.OnGetCitiesTaskCompleted{

    private static final long FIVE_SECONDS=5000;
    private BroadcastReceiver broadcastReceiver;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private Toolbar toolbar;

    // UpdateCities
    private List<City> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        toolbar = (Toolbar)findViewById(R.id.team_player_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupAlarm();
    }

    public void showNewMatchDialog(){
        FragmentManager fragmentManager = getFragmentManager();
        NewMatchDialog newMatchDialog = new NewMatchDialog();
        newMatchDialog.show(fragmentManager, "newMatchDialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_match_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.update_cities) {
            //UPDATE CITIES
            Toast.makeText(this, "Cities will be updated in 5 seconds.", Toast.LENGTH_SHORT).show();
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime()+FIVE_SECONDS,pendingIntent);
            return true;
        }

        if (id == R.id.new_match) {
            //UPDATE CITIES
            showNewMatchDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupAlarm(){
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                executeUpdateCitiesTask();
                Toast.makeText(context, "Cities Updated", Toast.LENGTH_SHORT).show();
            }
        };

        registerReceiver(broadcastReceiver,new IntentFilter("Update cities intent"));

        pendingIntent = PendingIntent.getBroadcast(this, 0,
                new Intent("Update cities intent"), 0);
        alarmManager= (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
    }

    private void executeUpdateCitiesTask(){
        TaskGetCities task = new TaskGetCities(this);
        task.execute();
    }

    @Override
    protected void onDestroy(){
        alarmManager.cancel(pendingIntent);
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    @Override
    public void onGetCitiesTaskCompleted(List<City> cities) {
        //cityList = cities;
        CityDAO cityDAO = new CityDAO(this);
        cityDAO.updateCities(cities);
    }

}

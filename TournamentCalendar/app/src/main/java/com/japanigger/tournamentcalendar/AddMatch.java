package com.japanigger.tournamentcalendar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.japanigger.tournamentcalendar.dao.MatchDAO;
import com.japanigger.tournamentcalendar.data.City;
import com.japanigger.tournamentcalendar.data.Match;
import com.japanigger.tournamentcalendar.data.Team;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AddMatch extends ActionBarActivity {
    private MatchDAO matchDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);
        matchDAO = new MatchDAO(this);
        matchDAO.open();

    }

    public void saveMatch(View view) {
        try {

            Match match = new Match();
            //EditText txtUser = (EditText) findViewById(R.id.txtUser);
            match.setDate((new Date()).toString());

            City city = new City(1,"Lima");
            match.setLocation(city);

            Team team1 = new Team("Peru");
            match.setTeam1(team1);
            Team team2 = new Team("Chile");
            match.setTeam2(team2);

            matchDAO.save(match);
        }
        finally{
            matchDAO.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_match, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.japanigger.tournamentcalendar;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;
import com.japanigger.tournamentcalendar.dao.TeamDAO;
import com.japanigger.tournamentcalendar.dao.rest.TaskGetTeams;
import com.japanigger.tournamentcalendar.data.Team;

import java.util.List;


public class TeamActivity extends ActionBarActivity  implements TeamListFragment.OnSelectedTeamListener{


    //private String selectedTeam;
   // private List<Team> teamList;

    private Toolbar toolbar;
    //private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_appbar);

        toolbar = (Toolbar)findViewById(R.id.team_player_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TeamListFragment teamListFragment = (TeamListFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_team_list);
        teamListFragment.setUp(R.id.fragment_team_list, (DrawerLayout)findViewById(R.id.drawer_layout), toolbar);

        Profile profile = Profile.getCurrentProfile();
        ProfilePictureView profilePic = (ProfilePictureView) findViewById(R.id.profilePicture);
        profilePic.setProfileId(profile.getId());
    }

    /*implementing TeamListFragment.OnSelectedTeamListener{ */
    public void onSelectTeam(int position, String selectedTeamName){
        selectItem(position, selectedTeamName);
    }

    private void selectItem(int position, String selectedTeamName) {
        // update the main content by replacing fragments
        Fragment fragment = new TeamPlayerFragment();
        Bundle args = new Bundle();
        args.putInt(TeamPlayerFragment.ARG_TEAM_POSITION, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
//        mDrawerList.setItemChecked(position, true);
        //selectedTeam =  teamList.get(position).getName();
        setTitle(selectedTeamName);

        //Creoq es una criollada
        ((DrawerLayout)findViewById(R.id.drawer_layout)).closeDrawer(Gravity.LEFT);
    }



    private void openMatchListFragment(){
        Intent intent = new Intent(this, MatchActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id){
            case R.id.view_calendar:
                openMatchListFragment();
                break;
        }

        /*
        if (id == R.id.action_settings) {
            openMatchListFragment();
        }
        if (id == android.R.id.home) {
            Toast.makeText(this, "fsdf", Toast.LENGTH_LONG).show();
        }
*/
        return super.onOptionsItemSelected(item);
    }
}

package com.japanigger.tournamentcalendar;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.japanigger.tournamentcalendar.dao.rest.TaskGetMatches;
import com.japanigger.tournamentcalendar.data.Match;
import com.japanigger.tournamentcalendar.dummy.DummyContent;

import java.util.List;

/**
 * Created by Noro on 23/05/2015.
 */
public class MatchListFragment extends Fragment  implements AbsListView.OnItemClickListener, TaskGetMatches.OnTaskCompleted{

    private List<Match> matchList;
    private ListView matchListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TaskGetMatches task = new TaskGetMatches(this);
        task.execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_match_list,container);
        matchListView = (ListView) view.findViewById(android.R.id.list);

        // Set OnItemClickListener so we can be notified on item clicks
        matchListView.setOnItemClickListener(this);
        return view;

    }

    public void onTaskCompleted(List<Match> matches) {
        matchList = matches;
        ListAdapter mAdapter = new ArrayAdapter<Match>(getActivity(),
                R.layout.match_list_item, android.R.id.text1, matchList);
        ((AdapterView<ListAdapter>) matchListView).setAdapter(mAdapter);
/*
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.team_list_item, teams));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        */
        /*

// enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(selectedTeam);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(selectedTeam + "aboureti");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        */
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


}

package com.japanigger.tournamentcalendar;

import android.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.japanigger.tournamentcalendar.dao.rest.TaskGetCities;
import com.japanigger.tournamentcalendar.dao.rest.TaskGetMatches;
import com.japanigger.tournamentcalendar.data.City;
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
        /*
        ListAdapter mAdapter = new ArrayAdapter<Match>(getActivity(),
                R.layout.match_list_item, android.R.id.text1, matchList);
        ((AdapterView<ListAdapter>) matchListView).setAdapter(mAdapter);*/


       // listview = (ListView) findViewById(R.id.listview);
        matchListView.setAdapter(new MatchAdapter(this.getActivity(), matchList));
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

    class MatchAdapter extends BaseAdapter {

        Context context;
        List<Match> data;
        private LayoutInflater inflater = null;

        public MatchAdapter(Context context, List<Match> data) {
            // TODO Auto-generated constructor stub
            this.context = context;
            this.data = data;
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return data.size();
        }

        @Override
        public Match getItem(int position) {
            // TODO Auto-generated method stub
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        private class ViewHolder{
            public TextView txtTeams;
            public TextView txtDate;

            private ViewHolder(TextView txtTeams, TextView txtDate) {
                this.txtTeams = txtTeams;
                this.txtDate = txtDate;
            }

            public void setMatch(Match match){
                txtTeams.setText(match.getTeam1().getName() + " vs " + match.getTeam2().getName());
                txtDate.setText(match.getDate());
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder viewHolder = null;
            View vi = convertView;
            if (vi == null) {
                vi = inflater.inflate(R.layout.match_list_item, null);
                viewHolder = new ViewHolder((TextView) vi.findViewById(R.id.txtTeams), (TextView) vi.findViewById(R.id.txtDate));
                vi.setTag(viewHolder);
            }
            else
                viewHolder = (ViewHolder)vi.getTag();

            Match match = data.get(position);
            viewHolder.setMatch(match);
            return vi;
        }
    }
}

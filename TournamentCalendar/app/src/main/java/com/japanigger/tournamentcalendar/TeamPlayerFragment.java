package com.japanigger.tournamentcalendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;


import com.japanigger.tournamentcalendar.dao.TeamDAO;
import com.japanigger.tournamentcalendar.dao.rest.TaskGetTeams;
import com.japanigger.tournamentcalendar.data.Team;
import com.japanigger.tournamentcalendar.data.TeamPlayer;
import com.japanigger.tournamentcalendar.dummy.DummyContent;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class TeamPlayerFragment extends Fragment implements AbsListView.OnItemClickListener, TaskGetTeams.OnTaskCompleted {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_TEAM_POSITION = "ARG_TEAM_POSITION";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mTeamPosition;
    private String mParam2;

    private List<Team> teamList;
    private TeamDAO teamDAO;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;


    public static TeamPlayerFragment newInstance(int teamPosition) {
        TeamPlayerFragment fragment = new TeamPlayerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TEAM_POSITION, teamPosition);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TeamPlayerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTeamPosition = getArguments().getInt(ARG_TEAM_POSITION);
        }

        TaskGetTeams task = new TaskGetTeams(this);
        task.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teamplayer_list, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void onTaskCompleted(List<Team> teams) {
        teamList=teams;
        Team selectedTeam = teamList.get(mTeamPosition);
        //mDrawerList.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.team_player_list_item, teams));


        mAdapter = new ArrayAdapter<TeamPlayer>(getActivity(),
                R.layout.team_player_list_item, selectedTeam.getPlayers());
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}

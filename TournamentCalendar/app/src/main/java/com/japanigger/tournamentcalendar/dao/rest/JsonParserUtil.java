package com.japanigger.tournamentcalendar.dao.rest;


import android.util.Log;

import com.japanigger.tournamentcalendar.data.Team;
import com.japanigger.tournamentcalendar.data.TeamPlayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 23/05/2015.
 */
public class JsonParserUtil {

    public List<Team> getTeams(JSONArray reader) throws JSONException {
        Log.d(getClass().getName(),"getTeams value: "+reader.toString());
        List<Team> teams = new ArrayList<>();
        Team temp;
        for (int i = 0; i < reader.length(); i++) {
            JSONObject jsonTeam = reader.getJSONObject(i);
            temp = getTeam(jsonTeam);
            teams.add(temp);
        }
        return teams;
    }

    public Team getTeam(JSONObject reader) throws JSONException {
        Log.d(getClass().getName(),"getTeam values: "+reader.toString());
        Team team = new Team();
        team.setId(reader.getInt("id"));
        team.setName(reader.getString("name"));
        team.setPlayers(getPlayers(reader.getJSONArray("players")));
        return team;
    }

    private List<TeamPlayer> getPlayers(JSONArray jsonPlayers) throws JSONException {
        Log.d(getClass().getName(),"getPlayers value: "+jsonPlayers.toString());
        List<TeamPlayer> players = new ArrayList<>();
        TeamPlayer temp;
        for (int i = 0; i < jsonPlayers.length(); i++) {
            temp = new TeamPlayer();
            JSONObject player = jsonPlayers.getJSONObject(i);
            if (player.has("id")) temp.setId(player.getInt("id"));
            if (player.has("firstName")) temp.setFirstName(player.getString("firstName"));
            if (player.has("lastName")) temp.setLastName(player.getString("lastName"));
            players.add(temp);
        }
        return players;
    }

}

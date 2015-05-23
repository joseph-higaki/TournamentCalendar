package com.japanigger.tournamentcalendar.dao.rest;


import android.util.Log;

import com.japanigger.tournamentcalendar.data.City;
import com.japanigger.tournamentcalendar.data.Match;
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

    public List<Match> getMatches(JSONArray reader) throws JSONException {
        List<Match> matches = new ArrayList<>();
        Match match;
        for (int i = 0; i < reader.length(); i++) {
            JSONObject jsonMatch = reader.getJSONObject(i);
            match = new Match();
            match.setId(jsonMatch.getInt("id"));
            match.setLocation(getLocation(jsonMatch.getJSONObject("location")));
            match.setTeam1(getTeam(jsonMatch.getJSONObject("team1")));
            match.setTeam2(getTeam(jsonMatch.getJSONObject("team2")));
            match.setDate(jsonMatch.has("date")?jsonMatch.getString("date"):"");
            matches.add(match);
        }
        return matches;
    }

    private City getLocation(JSONObject location) throws JSONException {
        City city = new City();
        city.setName(location.has("name")?location.getString("name"):"");
        city.setId(location.getInt("id"));
        return city;
    }

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
        team.setName(reader.has("name")?reader.getString("name"):"");
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

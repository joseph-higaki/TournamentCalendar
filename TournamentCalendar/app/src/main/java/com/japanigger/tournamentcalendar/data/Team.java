package com.japanigger.tournamentcalendar.data;

import java.util.List;

/**
 * Created by usuario on 5/21/2015.
 */
public class Team {

    private List<TeamPlayer> players;
    private int id;
    private String name;

    public List<TeamPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<TeamPlayer> players) {
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team(List<TeamPlayer> players, int id, String name) {
        super();
        this.players = players;
        this.id = id;
        this.name = name;
    }

    public Team() {
        super();
    }

    public Team (String name){
        this.name=name;
    }
}

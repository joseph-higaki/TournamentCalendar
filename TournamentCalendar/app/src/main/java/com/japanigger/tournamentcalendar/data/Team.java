
/** joseph Team*/
package com.japanigger.tournamentcalendar.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 5/21/2015.
 */
public class Team implements Serializable{
    private int id;
    private String name;
    private List<TeamPlayer> players;


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

    public Team(String name) {
        super();
        this.name = name;

        /* For testing purposes only*/
        players = new ArrayList<TeamPlayer>();
        for (int i=0; i<11; i++)
            players.add(new TeamPlayer(i, "Player "+i, name));
        /* For testing purposes only*/
    }

    public List<TeamPlayer> getPlayers() {
        return players;
    }
    public void setPlayers(List<TeamPlayer> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return getName();
    }

}

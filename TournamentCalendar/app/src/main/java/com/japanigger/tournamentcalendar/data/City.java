package com.japanigger.tournamentcalendar.data;

import java.io.Serializable;

/**
 * Created by usuario on 5/21/2015.
 */
public class City implements Serializable{
    private String name;
    private int id;

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

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public City() {
        
    }

    @Override
    public String toString() {
        return getName();
    }
}

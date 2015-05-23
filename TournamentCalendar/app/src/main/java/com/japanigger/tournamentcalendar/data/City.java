package com.japanigger.tournamentcalendar.data;

/**
 * Created by usuario on 5/21/2015.
 */
public class City {
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}

package com.japanigger.tournamentcalendar.data;

/**
 * Created by usuario on 5/21/2015.
 */
public class TeamPlayer {
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String firstName;
    private String lastName;


    private int id;

}

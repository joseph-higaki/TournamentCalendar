package com.japanigger.tournamentcalendar.dao;

import com.japanigger.tournamentcalendar.data.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 5/23/2015.
 */
public class TeamDAO{

       public List<Team> getAll(){
           List<Team> list = new ArrayList<Team>();
           list.add(new Team("Argentina"));
           list.add(new Team("Paraguay"));
           list.add(new Team("Peru"));
           return list;
        }
}

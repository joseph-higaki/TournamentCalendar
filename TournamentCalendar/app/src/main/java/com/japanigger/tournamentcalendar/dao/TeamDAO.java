package com.japanigger.tournamentcalendar.dao;

import android.util.Log;

import com.japanigger.tournamentcalendar.dao.rest.JsonParserUtil;
import com.japanigger.tournamentcalendar.dao.rest.RestConstants;
import com.japanigger.tournamentcalendar.data.Team;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 5/23/2015.
 */
public class TeamDAO {

    public List<Team> getAll() {
        List<Team> teams = new ArrayList<Team>();

        String resultStr;
        HttpClient client = new DefaultHttpClient();
        JsonParserUtil parser = new JsonParserUtil();
        HttpGet get = new HttpGet(RestConstants.GET_TEAMS);
        get.setHeader("Accept", "application/json");

        try {
            HttpResponse httpResponse = client.execute(get);
            resultStr = EntityUtils.toString(httpResponse.getEntity());
            Log.d("RESPONSE:: ", "response: " + resultStr);
            JSONArray jsonTeams = new JSONArray(resultStr);
            teams = parser.getTeams(jsonTeams);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e(getClass().getName(), "error parsing team object", e);
        }

        return teams;
    }
}

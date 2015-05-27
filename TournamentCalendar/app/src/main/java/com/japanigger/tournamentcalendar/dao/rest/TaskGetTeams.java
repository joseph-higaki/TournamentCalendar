package com.japanigger.tournamentcalendar.dao.rest;

import android.os.AsyncTask;
import android.util.Log;

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
 * Created by Cesar on 23/05/2015.
 */
public class TaskGetTeams extends AsyncTask<String, Integer, List<Team>> {

    private OnTaskCompleted listener;

    public TaskGetTeams(OnTaskCompleted listener) {
        this.listener=listener;
    }

    @Override
    protected List<Team> doInBackground(String... params) {

        String resultStr;
        List<Team> teams = new ArrayList<>();
        HttpClient client = new DefaultHttpClient();
        JsonParserUtil parser = new JsonParserUtil();
        HttpGet get = new HttpGet(RestConstants.GET_TEAMS);
        get.setHeader("Accept", "application/json");

        try {
            HttpResponse httpResponse = client.execute(get);
            resultStr = EntityUtils.toString(httpResponse.getEntity());
            //Log.d("RESPONSE:: ", "response: " + resultStr);
            JSONArray jsonTeams = new JSONArray(resultStr);
            teams = parser.getTeams(jsonTeams);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e(getClass().getName(), "error parsing team object", e);
        }

        return teams;
    }

    @Override
    protected void onPostExecute(List<Team> teams) {
        listener.onTaskCompleted(teams);
    }

    public interface OnTaskCompleted {
        void onTaskCompleted(List<Team> teams);
    }
}

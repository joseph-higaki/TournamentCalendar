package com.japanigger.tournamentcalendar.dao.rest;

import android.os.AsyncTask;

import com.japanigger.tournamentcalendar.data.Match;

import java.util.List;

/**
 * Created by usuario on 5/23/2015.
 */
public class TaskGetMatches extends AsyncTask<String, Integer, List<Match>> {
private OnTaskCompleted listener;

public TaskGetMatches(OnTaskCompleted listener) {
        this.listener=listener;
        }

@Override
protected List<Match> doInBackground(String... params) {
/*
        String resultStr;
        List<Match> matches = new ArrayList<>();
        HttpClient client = new DefaultHttpClient();
        JsonParserUtil parser = new JsonParserUtil();
        HttpGet get = new HttpGet(RestConstants.GET_TEAMS);
        get.setHeader("Accept", "application/json");
/*
        try {
        HttpResponse httpResponse = client.execute(get);
        resultStr = EntityUtils.toString(httpResponse.getEntity());
        Log.d("RESPONSE:: ", "response: " + resultStr);
        JSONArray jsonTeams = new JSONArray(resultStr);
        matches = parser.getMatches(jsonTeams);
        } catch (IOException e) {
        e.printStackTrace();
        } catch (JSONException e) {
        Log.e(getClass().getName(), "error parsing team object", e);
        }
*/
        return null;
        }

@Override
protected void onPostExecute(List<Match> teams) {
        listener.onTaskCompleted(teams);
        }

public interface OnTaskCompleted {
    void onTaskCompleted(List<Match> teams);
}
}

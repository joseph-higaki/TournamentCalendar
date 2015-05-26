package com.japanigger.tournamentcalendar.dao.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.japanigger.tournamentcalendar.data.Match;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Cesar on 23/05/2015.
 */
public class TaskPostMatch extends AsyncTask<Match, Integer, Boolean> {

    private OnTaskCompleted listener;

    public TaskPostMatch(OnTaskCompleted listener) {
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Match... params) {
        Match match = params[0];
        JsonParserUtil parser = new JsonParserUtil();
        String resultStr = "";
        Boolean response = false;
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(RestConstants.GET_MATCH);
        post.setHeader("Accept", "text/plain");
        post.setHeader("Content-type", "application/json");
        try {
            JSONObject holder = parser.getJsonFromMatch(match);
            StringEntity se = new StringEntity(holder.toString());
            post.setEntity(se);
            HttpResponse httpResponse = client.execute(post);
            resultStr = EntityUtils.toString(httpResponse.getEntity());
            if (!resultStr.equals("")) response = true;
            Log.d("RESPONSE:: ", "response: " + resultStr);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(Boolean response) {
        listener.onTaskPostCompleted(response);
    }

    public interface OnTaskCompleted {
        void onTaskPostCompleted(Boolean response);
    }
}

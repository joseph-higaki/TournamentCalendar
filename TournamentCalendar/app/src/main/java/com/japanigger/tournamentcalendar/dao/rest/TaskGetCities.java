package com.japanigger.tournamentcalendar.dao.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.japanigger.tournamentcalendar.data.City;

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
public class TaskGetCities extends AsyncTask<String, Integer, List<City>> {
    private OnGetCitiesTaskCompleted listener;

    public TaskGetCities(OnGetCitiesTaskCompleted listener) {
        this.listener=listener;
    }

    @Override
    protected List<City> doInBackground(String... params) {

        String resultStr;
        List<City> cities = new ArrayList<>();
        HttpClient client = new DefaultHttpClient();
        JsonParserUtil parser = new JsonParserUtil();
        HttpGet get = new HttpGet(RestConstants.GET_CITY);
        get.setHeader("Accept", "application/json");

        try {
            HttpResponse httpResponse = client.execute(get);
            resultStr = EntityUtils.toString(httpResponse.getEntity());
            //Log.d("RESPONSE:: ", "response: " + resultStr);
            JSONArray jsonCities = new JSONArray(resultStr);
            cities = parser.getCities(jsonCities);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e(getClass().getName(), "error parsing city object", e);
        }

        return cities;
    }

    @Override
    protected void onPostExecute(List<City> cities) {
        listener.onGetCitiesTaskCompleted(cities);
    }

    public interface OnGetCitiesTaskCompleted {
        void onGetCitiesTaskCompleted(List<City> cities);
    }
}

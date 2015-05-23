package com.japanigger.tournamentcalendar;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.japanigger.tournamentcalendar.dao.CityDAO;
import com.japanigger.tournamentcalendar.data.City;

import java.util.List;

public class NewMatchDialog extends DialogFragment {
    CityDAO cityDAO;
    List<City> cities;

    private int year_x, month_x, day_x;
    static final int DIALOG_ID = 123;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_match,null);

        cityDAO = new CityDAO(getActivity());
        cities = cityDAO.getAll();

        Spinner spinner = (Spinner) view.findViewById(R.id.city_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<City> adapter = new ArrayAdapter<City>(this.getActivity(),android.R.layout.simple_spinner_item, cities);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        return view;
    }

    public void selectDate(){

    }
}

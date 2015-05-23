package com.japanigger.tournamentcalendar;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.japanigger.tournamentcalendar.dao.CityDAO;
import com.japanigger.tournamentcalendar.data.City;

import java.util.List;

public class NewMatchDialog extends DialogFragment {
    CityDAO cityDAO;
    List<City> cities;

    Button btnSelectDate;
    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        cityDAO = new CityDAO(getActivity());
        cities = cityDAO.getAll();

        return inflater.inflate(R.layout.fragment_new_match,null);
    }


}

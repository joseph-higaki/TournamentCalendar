package com.japanigger.tournamentcalendar;


import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.japanigger.tournamentcalendar.dao.CityDAO;
import com.japanigger.tournamentcalendar.dao.rest.TaskGetTeams;
import com.japanigger.tournamentcalendar.data.City;
import com.japanigger.tournamentcalendar.data.Team;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NewMatchDialog extends DialogFragment implements TaskGetTeams.OnTaskCompleted{
    CityDAO cityDAO;
    List<City> cities;

    //date picker
    private int mYear, mMonth, mDay;
    private TextView tvSelectDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    // Time picker
    private int mHour, mMinute;
    private TextView tvSelectTime;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;

    //Teams
    private Spinner team1, team2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_match, null);
        // City Spinner
        cityDAO = new CityDAO(getActivity());
        cities = cityDAO.getAll();
        Spinner spinner = (Spinner) view.findViewById(R.id.city_spinner);
        ArrayAdapter<City> adapter = new ArrayAdapter<City>(this.getActivity(), android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Date Picker
        tvSelectDate = (TextView) view.findViewById(R.id.tvSelectDate);
        tvSelectDate.setOnClickListener(new TextView.OnClickListener() {
            public void onClick(View v) {
                selectDate();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
                updateDate();
            }
        };

        // Time Picker
        tvSelectTime = (TextView) view.findViewById(R.id.tvSelectTime);
        tvSelectTime.setOnClickListener(new TextView.OnClickListener() {
            public void onClick(View v) {
                selectTime();
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hour,
                                  int minute) {
                mMinute = minute;
                mHour = hour;
                updateTime();
            }
        };

        // Team Spinner
        team1 = (Spinner)view.findViewById(R.id.team1_spinner);
        team2 = (Spinner)view.findViewById(R.id.team2_spinner);

        TaskGetTeams task = new TaskGetTeams(this);
        task.execute();


        return view;
    }

    public void selectDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                R.style.AppTheme, mDateSetListener, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    private void updateDate() {
        GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        tvSelectDate.setText(sdf.format(c.getTime()));
        //sdf = new SimpleDateFormat("yyyy-MM-dd");
        //transDateString=sdf.format(c.getTime());
    }

    public void selectTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                mTimeSetListener, mHour, mMinute,false);
        timePickerDialog.show();
    }


    private void updateTime() {
        tvSelectTime.setText(new StringBuilder().append(mHour)
                .append(":").append(mMinute));
    }

    @Override
    public void onTaskCompleted(List<Team> teams) {
        //teamList = teams;
        ArrayAdapter<Team> adapter = new ArrayAdapter<Team>(this.getActivity(), android.R.layout.simple_spinner_item, teams);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        team1.setAdapter(adapter);
        team2.setAdapter(adapter);
    }
}

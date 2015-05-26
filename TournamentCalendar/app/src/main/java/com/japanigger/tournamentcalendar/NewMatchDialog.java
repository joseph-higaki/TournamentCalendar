package com.japanigger.tournamentcalendar;


import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.japanigger.tournamentcalendar.dao.CityDAO;
import com.japanigger.tournamentcalendar.dao.rest.TaskGetTeams;
import com.japanigger.tournamentcalendar.dao.rest.TaskPostMatch;
import com.japanigger.tournamentcalendar.data.City;
import com.japanigger.tournamentcalendar.data.Match;
import com.japanigger.tournamentcalendar.data.Team;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NewMatchDialog extends DialogFragment implements TaskGetTeams.OnTaskCompleted, TaskPostMatch.OnTaskCompleted {
    CityDAO cityDAO;
    List<City> cities;

    // city spinner
    private Spinner selectedCity;

    //date picker
    private int mYear = 0, mMonth = 0, mDay = 0;
    private TextView tvSelectDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    // Time picker
    private int mHour = 0, mMinute = 0;
    private TextView tvSelectTime;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;

    //Teams
    private Spinner team1, team2;

    //Button
    private Button btnSaveMatch;

    //Match
    private Match mMatch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_match, null);
        // City Spinner
        cityDAO = new CityDAO(getActivity());
        cities = cityDAO.getAll();
        selectedCity = (Spinner) view.findViewById(R.id.city_spinner);
        ArrayAdapter<City> adapter = new ArrayAdapter<City>(this.getActivity(), android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectedCity.setAdapter(adapter);

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
        team1 = (Spinner) view.findViewById(R.id.team1_spinner);
        team2 = (Spinner) view.findViewById(R.id.team2_spinner);

        // Save Button
        btnSaveMatch = (Button) view.findViewById(R.id.btnSaveMatch);
        btnSaveMatch.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                saveMatch();
            }
        });

        TaskGetTeams task = new TaskGetTeams(this);
        task.execute();

        return view;
    }

    private void saveMatch() {
        mMatch = new Match();
        City city = (City) selectedCity.getSelectedItem();
        Team team1Value = (Team) team1.getSelectedItem();
        Team team2Value = (Team) team2.getSelectedItem();
        mMatch.setLocation(city);
        mMatch.setTeam1(team1Value);
        mMatch.setTeam2(team2Value);
        String selectedDate = tvSelectDate.getText().toString()+" "+tvSelectTime.getText().toString();
        mMatch.setDate(selectedDate);
        Log.d(getClass().getName(), "city: " + city.getId() + " " + city.getName());
        Log.d(getClass().getName(), "Team 1: " + team1Value.getId() + " " + team1Value.getName());
        Log.d(getClass().getName(), "Team 2: " + team2Value.getId() + " " + team2Value.getName());
        Log.d(getClass().getName(), "Date: " + selectedDate);
        TaskPostMatch task = new TaskPostMatch(this);
        task.execute(mMatch);
    }

    public void selectDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                R.style.AppTheme, mDateSetListener, mYear, mMonth, mDay);
        if (mDay > 0) {
            datePickerDialog.updateDate(mYear, mMonth, mDay);
        } else {
            Calendar c = Calendar.getInstance();
            datePickerDialog.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        }
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
                mTimeSetListener, mHour, mMinute, true);
        if (mHour > 0) {
            timePickerDialog.updateTime(mHour, mMinute);
        } else {
            Calendar c = Calendar.getInstance();
            timePickerDialog.updateTime(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
        }
        timePickerDialog.show();
    }


    private void updateTime() {
        tvSelectTime.setText(new StringBuilder().append(mHour)
                .append(":").append((mMinute < 10) ? "0" + mMinute : mMinute));
    }

    @Override
    public void onTaskCompleted(List<Team> teams) {
        //teamList = teams;
        ArrayAdapter<Team> adapter = new ArrayAdapter<Team>(this.getActivity(), android.R.layout.simple_spinner_item, teams);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        team1.setAdapter(adapter);
        team2.setAdapter(adapter);
    }

    @Override
    public void onTaskPostCompleted(Boolean response) {
        if (response){
            Toast.makeText(this.getActivity().getApplicationContext(), "Match Saved", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this.getActivity().getApplicationContext(), "Error saving match", Toast.LENGTH_LONG).show();
        }
    }
}

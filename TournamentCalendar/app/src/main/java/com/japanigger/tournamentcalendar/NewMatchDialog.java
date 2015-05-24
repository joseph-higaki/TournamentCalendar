package com.japanigger.tournamentcalendar;


import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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

import com.japanigger.tournamentcalendar.dao.CityDAO;
import com.japanigger.tournamentcalendar.data.City;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NewMatchDialog extends DialogFragment {
    CityDAO cityDAO;
    List<City> cities;
    TextView tvSelectDate;
    DatePickerDialog.OnDateSetListener mDateSetListener;

    //date picker
    private int mYear, mMonth, mDay;
    static final int DIALOG_ID = 123;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_match, null);

        cityDAO = new CityDAO(getActivity());
        cities = cityDAO.getAll();

        Spinner spinner = (Spinner) view.findViewById(R.id.city_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<City> adapter = new ArrayAdapter<City>(this.getActivity(), android.R.layout.simple_spinner_item, cities);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        tvSelectDate = (TextView) view.findViewById(R.id.tvSelectDate);

        //TextView buttonOne = (Button) findViewById(R.id.button1);
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
                updateDisplay();
            }
        };

        return view;
    }

    public void selectDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                R.style.AppTheme, mDateSetListener, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    private void updateDisplay() {

        GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");

        tvSelectDate.setText(sdf.format(c.getTime()));

        //sdf = new SimpleDateFormat("yyyy-MM-dd");

        //     transDateString=sdf.format(c.getTime());
    }// updateDisplay

}

package com.u0509421.todayinhistory.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.u0509421.todayinhistory.Activities.DayActivity;
import com.u0509421.todayinhistory.Activities.DayListActivity;
import com.u0509421.todayinhistory.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Terry on 21/3/16.
 */
public class EverydayFragment extends Fragment implements OnDateSelectedListener,OnMonthChangedListener {

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    private MaterialCalendarView widget = null;
    private TextView tvDate = null;

    private Bundle bundle;

    public EverydayFragment() {
    }

    public static EverydayFragment newInstance() {
        EverydayFragment sampleFragment = new EverydayFragment();
        return sampleFragment;
    }

    @Override
    public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
        tvDate.setText(getSelectedDateString());
        System.out.println(widget.getSelectedDate());
        String str = widget.getSelectedDate().toString();
        dateParse(str);
        System.out.println(bundle.getString("month"));
        System.out.println(bundle.getString("day"));

        Intent intent = new Intent(getContext(), DayListActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_date_select,container,false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity)getActivity()).setTitle("历史上的每天");


        widget = (MaterialCalendarView) rootView.findViewById(R.id.calendarView);
        widget.setOnDateChangedListener(this);
        widget.setOnMonthChangedListener(this);

        tvDate = (TextView) rootView.findViewById(R.id.tvDate);
        tvDate.setText(getSelectedDateString());

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DayListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        return rootView;
    }

    private String getSelectedDateString(){
        CalendarDay date = widget.getSelectedDate();
        if (date == null){
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }

    private void dateParse(String date){
        //月份需要+1才是真正的月份
        String temp = date.substring(13, 21);
        String[] dateArray = temp.split("-");
        bundle = new Bundle();
        bundle.putString("month",String.valueOf((Integer.parseInt(dateArray[1])+1)));
        if (dateArray[2].substring(1,2).equals("}")){
            bundle.putString("day",dateArray[2].substring(0,1));
        }else{
            bundle.putString("day",dateArray[2]);
        }
    }
}

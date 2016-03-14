package com.u0509421.todayinhistory.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.u0509421.todayinhistory.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * Created by Terry on 15/3/16.
 */
public class DateSelectActivity extends AppCompatActivity implements OnDateSelectedListener,OnMonthChangedListener{

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    private MaterialCalendarView widget = null;
    private TextView tvDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_select);

        widget = (MaterialCalendarView) findViewById(R.id.calendarView);
        widget.setOnDateChangedListener(this);
        widget.setOnMonthChangedListener(this);

        tvDate = (TextView) findViewById(R.id.tvDate);
        tvDate.setText(getSelectedDateString());
    }

    @Override
    public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
        tvDate.setText(getSelectedDateString());
        System.out.println(widget.getSelectedDate());
        String str = widget.getSelectedDate().toString();
        Bundle bundle = dateParse(str);
        System.out.println(bundle.getString("month"));
        System.out.println(bundle.getString("day"));
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
    }

    private String getSelectedDateString(){
        CalendarDay date = widget.getSelectedDate();
        if (date == null){
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }

    private Bundle dateParse(String date){
        //月份需要+1才是真正的月份
        String temp = date.substring(13,21);
        String[] dateArray = temp.split("-");
        Bundle bundle = new Bundle();
        bundle.putString("month",dateArray[1]);
        bundle.putString("day",dateArray[2]);

        return bundle;
    }
}

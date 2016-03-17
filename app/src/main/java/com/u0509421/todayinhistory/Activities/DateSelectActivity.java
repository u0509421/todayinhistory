package com.u0509421.todayinhistory.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_select_page);

        //单独设定Action Bar title
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setTitle("历史上的每天");

        widget = (MaterialCalendarView) findViewById(R.id.calendarView);
        widget.setOnDateChangedListener(this);
        widget.setOnMonthChangedListener(this);

        tvDate = (TextView) findViewById(R.id.tvDate);
        tvDate.setText(getSelectedDateString());

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DateSelectActivity.this,DayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
        tvDate.setText(getSelectedDateString());
        System.out.println(widget.getSelectedDate());
        String str = widget.getSelectedDate().toString();
        bundle = dateParse(str);
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
        bundle.putString("month",String.valueOf((Integer.parseInt(dateArray[1])+1)));
        if (dateArray[2].length() == 2){
            bundle.putString("day",dateArray[2].substring(0,1));
        }else{
            bundle.putString("day",dateArray[2]);
        }
        return bundle;
    }
}

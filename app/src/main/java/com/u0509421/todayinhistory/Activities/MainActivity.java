package com.u0509421.todayinhistory.Activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.u0509421.todayinhistory.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnEveryday,btnSearch,btnStar,btnMore,btnToday;
    private String monthTemp = null,month = null,day = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        final Calendar calendar = Calendar.getInstance();


        //获取当前系统时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        String todayDate = dateFormat.format(new Date());
        String[] dateArray = todayDate.split("-");
        monthTemp = dateArray[0];
        day = dateArray[1];
        if (Integer.parseInt(monthTemp.substring(0,1)) == 0){
            month = monthTemp.substring(1);
        }
        if (Integer.parseInt(monthTemp.substring(0,1))!= 0){
            month = monthTemp;
        }
        System.out.println(month+":"+day);


    }

    private void init(){
        btnMore = (Button) findViewById(R.id.btnMore);
        btnToday = (Button) findViewById(R.id.btnToday);
        btnStar = (Button) findViewById(R.id.btnStar);
        btnEveryday = (Button) findViewById(R.id.btnEveryday);
        btnSearch = (Button) findViewById(R.id.btnSearch);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnToday:

                break;
            case R.id.btnEveryday:

                break;
            case R.id.btnSearch:

                break;
            case R.id.btnStar:

                break;
            case R.id.btnMore:

                break;
        }
    }
}

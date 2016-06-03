package com.u0509421.todayinhistory.activities;



import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarFragment;
import com.u0509421.todayinhistory.R;
import com.u0509421.todayinhistory.view.FragmentEveryday;
import com.u0509421.todayinhistory.view.FragmentMore;
import com.u0509421.todayinhistory.view.FragmentFavourite;
import com.u0509421.todayinhistory.view.FragmentToday;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentToday fragmentToday;
    private FragmentEveryday fragmentEveryday;
    private FragmentFavourite fragmentFavourite;
    private FragmentMore fragmentMore;

    private FragmentManager fragmentManager;

    private TextView tvToday,tvEveryday,tvFavourite,tvMore;
    private ImageView ivToday,ivEveryday,ivFavourite,ivMore;
    private LinearLayout tabToday,tabEveryday,tabFavourite,tabMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initView(){
        tvToday = (TextView) findViewById(R.id.tvToday);
        tvEveryday = (TextView) findViewById(R.id.tvEveryday);
        tvFavourite = (TextView) findViewById(R.id.tvFavourite);
        tvMore = (TextView) findViewById(R.id.tvMore);

        ivToday = (ImageView) findViewById(R.id.ivToday);
        ivEveryday = (ImageView) findViewById(R.id.ivEveryday);
        ivFavourite = (ImageView) findViewById(R.id.ivFavourite);
        ivMore = (ImageView) findViewById(R.id.ivMore);

        ivToday.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_calendar_check_o));
        ivEveryday.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_calendar));
        ivFavourite.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_heart_o));
        ivMore.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_info));

        tabToday = (LinearLayout) findViewById(R.id.tabToday);
        tabEveryday = (LinearLayout) findViewById(R.id.tabEveryday);
        tabFavourite = (LinearLayout) findViewById(R.id.tabFavourite);
        tabMore = (LinearLayout) findViewById(R.id.tabMore);

        tabToday.setOnClickListener(this);
        tabEveryday.setOnClickListener(this);
        tabFavourite.setOnClickListener(this);
        tabMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tabToday:
                setTabSelection(0);
                getSupportActionBar().setTitle("历史上的今天");
                break;
            case R.id.tabEveryday:
                setTabSelection(1);
                getSupportActionBar().setTitle("历史上的每天");
                break;
            case R.id.tabFavourite:
                setTabSelection(2);
                getSupportActionBar().setTitle("收藏");
                break;
            case R.id.tabMore:
                setTabSelection(3);
                getSupportActionBar().setTitle("更多");
                break;
            default:
                break;
        }
    }

    private void setTabSelection(int index){
        clearSelection();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        hideFragment(transaction);

        switch (index){
            case 0:

                tvToday.setTextColor(Color.parseColor("#836464"));
                ivToday.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_calendar_check_o)
                        .colorRes(R.color.colorPrimary));
                if (fragmentToday == null){
                    fragmentToday = new FragmentToday();
                    transaction.add(R.id.fragmentContainer,fragmentToday);
                }else {
                    transaction.show(fragmentToday);
                }
                break;
            case 1:

                tvEveryday.setTextColor(Color.parseColor("#836464"));
                ivEveryday.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_calendar)
                        .colorRes(R.color.colorPrimary));
                if (fragmentEveryday == null){
                    fragmentEveryday = new FragmentEveryday();
                    transaction.add(R.id.fragmentContainer,fragmentEveryday);
                }else {
                    transaction.show(fragmentEveryday);
                }
                break;
            case 2:

                tvFavourite.setTextColor(Color.parseColor("#836464"));
                ivFavourite.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_heart_o)
                        .colorRes(R.color.colorPrimary));
                if (fragmentFavourite == null){
                    fragmentFavourite = new FragmentFavourite();
                    transaction.add(R.id.fragmentContainer,fragmentFavourite);
                }else {
                    transaction.show(fragmentFavourite);
                }
                break;
            case 3:

                tvMore.setTextColor(Color.parseColor("#836464"));
                ivMore.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_info)
                        .colorRes(R.color.colorPrimary));
                if (fragmentMore == null){
                    fragmentMore = new FragmentMore();
                    transaction.add(R.id.fragmentContainer,fragmentMore);
                }else {
                    transaction.show(fragmentMore);
                }
                break;
        }
        transaction.commit();
    }

    private void clearSelection(){

        ivToday.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_calendar_check_o));
        ivEveryday.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_calendar));
        ivFavourite.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_heart_o));
        ivMore.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_info));

        tvToday.setTextColor(Color.BLACK);
        tvEveryday.setTextColor(Color.BLACK);
        tvFavourite.setTextColor(Color.BLACK);
        tvMore.setTextColor(Color.BLACK);
    }

    private void hideFragment(FragmentTransaction transaction){
        if(fragmentToday != null){
            transaction.hide(fragmentToday);
        }
        if(fragmentEveryday != null){
            transaction.hide(fragmentEveryday);
        }
        if(fragmentFavourite != null){
            transaction.hide(fragmentFavourite);
        }
        if(fragmentMore != null){
            transaction.hide(fragmentMore);
        }
    }

}

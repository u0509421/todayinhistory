package com.u0509421.todayinhistory.activities;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarFragment;
import com.u0509421.todayinhistory.R;
import com.u0509421.todayinhistory.view.FragmentEveryday;
import com.u0509421.todayinhistory.view.FragmentMore;
import com.u0509421.todayinhistory.view.FragmentFavourite;
import com.u0509421.todayinhistory.view.FragmentToday;

public class MainActivity extends AppCompatActivity{

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomBar = BottomBar.attach(this,savedInstanceState);
        mBottomBar.setFragmentItems(getSupportFragmentManager(),R.id.fragmentContainer,
                new BottomBarFragment(FragmentToday.newInstance(),R.drawable.ic_today,"今天"),
                new BottomBarFragment(FragmentEveryday.newInstance(),R.drawable.ic_everyday,"每天"),
                new BottomBarFragment(FragmentFavourite.newInstance(),R.drawable.ic_favorites,"收藏"),
                new BottomBarFragment(FragmentMore.newInstance(),R.drawable.ic_more,"更多"));

        mBottomBar.mapColorForTab(0, "#836464");
        mBottomBar.mapColorForTab(1,"#836464");
        mBottomBar.mapColorForTab(2,"#836464");
        mBottomBar.mapColorForTab(3,"#836464");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mBottomBar.onSaveInstanceState(outState);
    }
}

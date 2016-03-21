package com.u0509421.todayinhistory.Activities;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarFragment;
import com.u0509421.todayinhistory.R;
import com.u0509421.todayinhistory.view.EverydayFragment;
import com.u0509421.todayinhistory.view.MoreFragment;
import com.u0509421.todayinhistory.view.StarFragment;
import com.u0509421.todayinhistory.view.TodayFragment;

public class MainActivity extends AppCompatActivity{

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomBar = BottomBar.attach(this,savedInstanceState);
        mBottomBar.setFragmentItems(getSupportFragmentManager(),R.id.fragmentContainer,
                new BottomBarFragment(TodayFragment.newInstance(),R.drawable.ic_nearby,"今天"),
                new BottomBarFragment(EverydayFragment.newInstance(),R.drawable.ic_recents,"每天"),
                new BottomBarFragment(StarFragment.newInstance(),R.drawable.ic_favorites,"收藏"),
                new BottomBarFragment(MoreFragment.newInstance(),R.drawable.ic_friends,"更多"));

        mBottomBar.mapColorForTab(0,"#8a7370");
        mBottomBar.mapColorForTab(1,"#8a7370");
        mBottomBar.mapColorForTab(2,"#8a7370");
        mBottomBar.mapColorForTab(3,"#8a7370");
//
//        list_today.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String e_id = list.get(position).getE_id();
//                Intent intent = new Intent(MainActivity.this,DayActivity.class);
//                intent.putExtra("e_id",e_id);
//                startActivity(intent);
//            }
//        });
    }

}

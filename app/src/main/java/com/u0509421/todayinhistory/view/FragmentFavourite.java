package com.u0509421.todayinhistory.view;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.u0509421.todayinhistory.activities.DayActivity;
import com.u0509421.todayinhistory.adapter.EventListAdapter;
import com.u0509421.todayinhistory.db.HistoryDb;
import com.u0509421.todayinhistory.bean.EventList;
import com.u0509421.todayinhistory.R;

import java.util.ArrayList;

/**
 * Created by Terry on 21/3/16.
 */
public class FragmentFavourite extends Fragment {

    private ListView sList_star;
    private EventListAdapter sAdapter;
    private ArrayList<EventList> list;

    private HistoryDb historyDb;
    private SQLiteDatabase dbRead;

    public FragmentFavourite() {
    }

    public static FragmentFavourite newInstance() {
        FragmentFavourite sampleFragment = new FragmentFavourite();
        return sampleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_favourite,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity)getActivity()).setTitle("收藏");

        sList_star = (ListView) rootView.findViewById(R.id.star_list);

        historyDb = new HistoryDb(getActivity());
        dbRead = historyDb.getReadableDatabase();

        refreshListView();

        sList_star.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = list.get(position).getTitle();
                String date = list.get(position).getDate();
                String e_id = list.get(position).getE_id();
                Bundle bundle = new Bundle();
                bundle.putString("title",title);
                bundle.putString("date",date);
                bundle.putString("e_id",e_id);
                Intent intent = new Intent(getContext(), DayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return rootView;
    }

    private void refreshListView(){
        sAdapter = new EventListAdapter(getActivity());
        list = new ArrayList<EventList>();
        Cursor cursor = dbRead.query("history",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            for (int i = 0;i < cursor.getCount();i++){
                System.out.println("title"+cursor.getString(1)+"date"+cursor.getString(2)+"e_id"+cursor.getString(3));
                EventList eventList = new EventList();
                eventList.setDate(cursor.getString(2));
                eventList.setTitle(cursor.getString(1));
                eventList.setE_id(cursor.getString(3));
                list.add(eventList);
                sAdapter.setData(list);
                sList_star.setAdapter(sAdapter);
                cursor.moveToNext();
            }
        }
    }
}

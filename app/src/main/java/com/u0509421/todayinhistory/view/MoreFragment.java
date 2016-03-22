package com.u0509421.todayinhistory.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.u0509421.todayinhistory.Controllers.EventListAdapter;
import com.u0509421.todayinhistory.Controllers.MoreListAdapter;
import com.u0509421.todayinhistory.Model.EventList;
import com.u0509421.todayinhistory.Model.MoreItem;
import com.u0509421.todayinhistory.R;

import java.util.ArrayList;

/**
 * Created by Terry on 21/3/16.
 */
public class MoreFragment extends Fragment {

    private ListView list_more;
    private MoreListAdapter mAdapter;
    private ArrayList<MoreItem> list;

    public MoreFragment() {
    }

    public static MoreFragment newInstance() {
        MoreFragment sampleFragment = new MoreFragment();
        return sampleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_more,container,false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity)getActivity()).setTitle("更多");

        list_more = (ListView) rootView.findViewById(R.id.list_more);
        mAdapter = new MoreListAdapter(getActivity());
        list = new ArrayList<MoreItem>();
        list.add(new MoreItem("意见反馈"));
        list.add(new MoreItem("觉得好，就赏个好评吧"));
        list.add(new MoreItem("开发者"));
        mAdapter.setData(list);
        list_more.setAdapter(mAdapter);

        return rootView;
    }
}

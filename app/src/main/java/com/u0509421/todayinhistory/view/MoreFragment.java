package com.u0509421.todayinhistory.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.u0509421.todayinhistory.R;

/**
 * Created by Terry on 21/3/16.
 */
public class MoreFragment extends Fragment {

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

        return rootView;
    }
}

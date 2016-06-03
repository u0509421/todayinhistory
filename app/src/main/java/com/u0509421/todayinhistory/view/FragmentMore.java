package com.u0509421.todayinhistory.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.u0509421.todayinhistory.adapter.MoreListAdapter;
import com.u0509421.todayinhistory.bean.MoreItem;
import com.u0509421.todayinhistory.R;

import java.util.ArrayList;

/**
 * Created by Terry on 21/3/16.
 */
public class FragmentMore extends Fragment{

    private ListView list_more;
    private MoreListAdapter mAdapter;
    private ArrayList<MoreItem> list;

    public FragmentMore() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_more,container,false);

        list_more = (ListView) rootView.findViewById(R.id.list_more);
        mAdapter = new MoreListAdapter(getActivity());
        list = new ArrayList<MoreItem>();
        list.add(new MoreItem("意见反馈"));
        list.add(new MoreItem("觉得好，就赏个好评吧"));
        list.add(new MoreItem("开发者网站"));
        mAdapter.setData(list);
        list_more.setAdapter(mAdapter);
        list_more.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intentFeedback = new Intent(Intent.ACTION_SENDTO);
                        intentFeedback.setData(Uri.parse("mailto:u0509421@gmail.com"));
                        intentFeedback.putExtra(Intent.EXTRA_SUBJECT, "历史上的今天 意见与建议");
                        startActivity(intentFeedback);
                        break;
                    case 1:
                        Uri uri = Uri.parse("market://details?id=" + getContext().getPackageName());
                        Intent intentMarket = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intentMarket);
                        break;
                    case 2:
                        Intent intentAuthor = new Intent(Intent.ACTION_VIEW);
                        intentAuthor.setData(Uri.parse("http://u0509421.github.io/about/"));
                        startActivity(intentAuthor);
                        break;
                }
            }
        });
        return rootView;
    }
}

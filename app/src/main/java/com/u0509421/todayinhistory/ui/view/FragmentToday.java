package com.u0509421.todayinhistory.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.u0509421.todayinhistory.db.bean.Result;
import com.u0509421.todayinhistory.network.HistoryService;
import com.u0509421.todayinhistory.network.RetrofitManager;
import com.u0509421.todayinhistory.ui.activities.DayActivity;
import com.u0509421.todayinhistory.ui.adapter.EventListAdapter;
import com.u0509421.todayinhistory.db.bean.EventList;
import com.u0509421.todayinhistory.R;
import com.u0509421.todayinhistory.util.VolleyUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Terry on 21/3/16.
 */
public class FragmentToday extends Fragment {

    private ListView list_today;
    private SwipeRefreshLayout swipeRefresh;
    private EventListAdapter adapter;
//    private ProgressBar progressBar;

    private String monthTemp = null,dayTemp = null,month = null,day = null, date = null;
    private static final String TAG = "TodayInHistory";

    private Observer<Result> observer;
    private ArrayList<EventList> list = new ArrayList<EventList>();

    public FragmentToday() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_today,container,false);

        initView(rootView);
        initDataObserver();
        getTodayDate();
        fetchHistory();

//        //开始网络请求
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(urlString, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        System.out.println(response.toString());
//                        parseJson(response);
//                        adapter.setData(list);
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(getActivity(), "网络不给力，重新连接一下吧~", Toast.LENGTH_SHORT).show();
//            }
//        });
//        // 请求加上Tag，用于取消请求
//        jsonObjectRequest.setTag(this);
//        VolleyUtil.getRequestQueue(getActivity()).add(jsonObjectRequest);

        return rootView;
    }


    @Override
    public void onDestroyView() {
        VolleyUtil.getRequestQueue(getActivity()).cancelAll(this);
        super.onDestroyView();
    }


//    private List<EventList> parseJson(JSONObject jsonObject){
//        try {
//            JSONArray results = new JSONArray(jsonObject.getString("result"));
//
//            for (int i = 0; i < results.length(); i++){
//                JSONObject object = results.getJSONObject(i);
//                EventList eventList = new EventList();
//                eventList.setDate(object.getString("date"));
//                eventList.setTitle(object.getString("title"));
//                eventList.setE_id(object.getString("e_id"));
//                list.add(eventList);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }


    private void initView(View rootView){

//        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        swipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorSchemeColors(
                R.color.colorPrimary,
                R.color.colorPrimaryDark);
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(true);
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchHistory();
            }
        });

        adapter = new EventListAdapter(getActivity());

        list_today = (ListView) rootView.findViewById(R.id.list_today);
        list_today.setAdapter(adapter);
        list_today.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    }

    //获取当前系统日期

    private void getTodayDate(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        String todayDate = dateFormat.format(new Date());
        String[] dateArray = todayDate.split("-");
        monthTemp = dateArray[0];
        dayTemp = dateArray[1];
        
        month = (Integer.parseInt(monthTemp.substring(0,1)) == 0)? monthTemp.substring(1) : monthTemp;
        day = (Integer.parseInt(dayTemp.substring(0,1)) == 0)? dayTemp.substring(1) : dayTemp;
        date = month+"/"+day;
    }

    // 获取历史事件
    private void fetchHistory(){
        RetrofitManager.getHistoryService(getContext())
                .getResult(HistoryService.KEY,date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //初始化Observer
    private void initDataObserver(){
        observer = new Observer<Result>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onNext(Result result) {
                list = result.getResult();
                adapter.setData(list);
            }
        };
    }
}

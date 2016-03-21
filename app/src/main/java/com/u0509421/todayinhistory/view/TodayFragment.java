package com.u0509421.todayinhistory.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.u0509421.todayinhistory.Controllers.EventListAdapter;
import com.u0509421.todayinhistory.Model.EventList;
import com.u0509421.todayinhistory.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Terry on 21/3/16.
 */
public class TodayFragment extends Fragment {

    private ArrayList<EventList> list = new ArrayList<EventList>();
    private ListView list_today;
    private EventListAdapter adapter;
    private String monthTemp = null,month = null,day = null;

    public TodayFragment() {
    }

    public static TodayFragment newInstance() {
        TodayFragment sampleFragment = new TodayFragment();
        return sampleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_today,container,false);

        getTodayDate();
        String urlString = "http://v.juhe.cn/todayOnhistory/queryEvent.php?key="+
                "a87c2d7033aedc2b2460de9117588285"+"&date="+month+"/"+day;
        System.out.println(urlString);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity)getActivity()).setTitle("历史上的"+month+"月"+day+"日");
        list_today = (ListView) rootView.findViewById(R.id.list_today);
        adapter = new EventListAdapter(getActivity());
        list_today.setAdapter(adapter);


        //开始网络请求
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(urlString, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        parseJson(response);
                        adapter.setData(list);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage().toString());
                Toast.makeText(getActivity(), "网络不给力，重新连接一下吧~", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
        return rootView;
    }



    /**
     * 解析网络请求返回的JSON数据
     * @param jsonObject
     * @return list
     */
    private List<EventList> parseJson(JSONObject jsonObject){
        try {
            JSONArray results = new JSONArray(jsonObject.getString("result"));

            for (int i = 0; i < results.length(); i++){
                JSONObject object = results.getJSONObject(i);
                EventList eventList = new EventList();
                eventList.setDate(object.getString("date"));
                eventList.setTitle(object.getString("title"));
                eventList.setE_id(object.getString("e_id"));
                list.add(eventList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void getTodayDate(){

        //获取当前系统日期
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

    }

}
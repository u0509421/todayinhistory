package com.u0509421.todayinhistory.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.u0509421.todayinhistory.ui.adapter.EventListAdapter;
import com.u0509421.todayinhistory.db.bean.EventList;
import com.u0509421.todayinhistory.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terry on 21/3/16.
 */
public class DayListActivity extends AppCompatActivity {

    private String month,day,e_id;

    private ArrayList<EventList> mlist = new ArrayList<EventList>();
    private ListView mDay_list;
    private EventListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daylist);

        Intent intent = getIntent();
        month = intent.getStringExtra("month");
        day = intent.getStringExtra("day");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setTitle("历史上的"+month+"月"+day+"日");

        String urlString = "http://v.juhe.cn/todayOnhistory/queryEvent.php?key="+
                "a87c2d7033aedc2b2460de9117588285"+"&date="+month+"/"+day;


        mDay_list = (ListView) findViewById(R.id.daylist);
        mAdapter = new EventListAdapter(this);
        mDay_list.setAdapter(mAdapter);
        mDay_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = mlist.get(position).getTitle();
                String date = mlist.get(position).getDate();
                String e_id = mlist.get(position).getE_id();
                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("date", date);
                bundle.putString("e_id", e_id);
                Intent intent = new Intent(DayListActivity.this, DayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



        //开始网络请求
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(urlString, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        parseJson(response);
                        mAdapter.setData(mlist);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage().toString());
                Toast.makeText(DayListActivity.this, "网络不给力，重新连接一下吧~", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
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
                mlist.add(eventList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mlist;
    }
}

package com.u0509421.todayinhistory.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terry on 17/3/16.
 */
public class DayActivity extends AppCompatActivity {

    private ArrayList<EventList>list = new ArrayList<EventList>();
    private ListView lvDay;
    private EventListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_page);

        lvDay = (ListView) findViewById(R.id.lvDay);
        adapter = new EventListAdapter(DayActivity.this);
        lvDay.setAdapter(adapter);

        //获取传过来的日期
        Bundle bundle = getIntent().getExtras();
        String day = bundle.getString("day");
        String month = bundle.getString("month");
        String urlString = "http://v.juhe.cn/todayOnhistory/queryEvent.php?key="+
                "a87c2d7033aedc2b2460de9117588285"+"&date="+month+"/"+day;
        System.out.println(urlString);

        //单独设定Action Bar title
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setTitle("历史上的"+month+"月"+day+"日");

        //开始网络请求
        RequestQueue queue = Volley.newRequestQueue(this);
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
                        Toast.makeText(getApplicationContext(),"网络不给力，重新连接一下吧~",Toast.LENGTH_SHORT).show();
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
                list.add(eventList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}

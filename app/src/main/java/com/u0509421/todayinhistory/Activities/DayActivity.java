package com.u0509421.todayinhistory.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.u0509421.todayinhistory.Model.EventList;
import com.u0509421.todayinhistory.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Terry on 20/3/16.
 */
public class DayActivity extends AppCompatActivity {

    private TextView tvDayContent,tvDayTitle;
    private static final String KEY = "http://v.juhe.cn/todayOnhistory/queryDetail.php?key=a87c2d7033aedc2b2460de9117588285&e_id=";
    private String title,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        tvDayContent = (TextView) findViewById(R.id.tvDayContent);
        tvDayTitle = (TextView) findViewById(R.id.tvDayTitle);
        tvDayContent.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        String url = KEY + intent.getStringExtra("e_id");
        System.out.println(url);
        title = intent.getStringExtra("title");
        date = intent.getStringExtra("date");
        System.out.println(title + date);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setTitle(date);

        RequestQueue dayQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        parseJson(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage().toString());
                        Toast.makeText(DayActivity.this, "网络不给力，重新连接一下吧~", Toast.LENGTH_SHORT).show();
                    }
                });
        dayQueue.add(jsonObjectRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_favourite:
                Toast.makeText(this, "Favourite selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_share:
                Toast.makeText(this, "Share selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 解析网络请求返回的JSON数据
     * @param jsonObject
     * @return list
     */
    private void parseJson(JSONObject jsonObject){
        try {
            JSONArray results = new JSONArray(jsonObject.getString("result"));

            JSONObject result = results.getJSONObject(0);
            tvDayTitle.setText(result.getString("title"));
            tvDayContent.setText(result.getString("content"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

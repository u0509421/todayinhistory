package com.u0509421.todayinhistory.ui.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.u0509421.todayinhistory.db.HistoryDb;
import com.u0509421.todayinhistory.R;
import com.u0509421.todayinhistory.util.VolleyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Terry on 20/3/16.
 */
public class DayActivity extends AppCompatActivity {

    private TextView tvDayTitle;
    private WebView wbContent;

    private static final String KEY = "http://v.juhe.cn/todayOnhistory/queryDetail.php?key=a87c2d7033aedc2b2460de9117588285&e_id=";
    private String title,date,eid;

    private com.u0509421.todayinhistory.db.HistoryDb historyDb;
    private SQLiteDatabase dbWrite;
    private ContentValues cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        //View初始化
        wbContent = (WebView) findViewById(R.id.wbContent);
        tvDayTitle = (TextView) findViewById(R.id.tvDayTitle);

        //接收传来的日期跟标题
        Intent intent = getIntent();
        String url = KEY + intent.getStringExtra("e_id");

        eid = intent.getStringExtra("e_id");
        title = intent.getStringExtra("title");
        date = intent.getStringExtra("date");

        //准备把数据放到本地数据库中
        cv = new ContentValues();
        cv.put("title",title);
        cv.put("date",date);
        cv.put("eid",eid);
        historyDb = new HistoryDb(this);
        dbWrite = historyDb.getWritableDatabase();

        //设定标题
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(date);

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
        // 请求加上Tag，用于取消请求
        jsonObjectRequest.setTag(this);
        VolleyUtil.getRequestQueue(getApplicationContext()).add(jsonObjectRequest);

    }

    @Override
    protected void onDestroy() {
        VolleyUtil.getRequestQueue(getApplication()).cancelAll(this);
        super.onDestroy();
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
                dbWrite.replace("history",null,cv);
                Toast.makeText(this, "已添加到收藏", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_share:
                share();
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
            JSONArray picUrl = new JSONArray(results.getJSONObject(0).getString("picUrl"));
            JSONObject result = results.getJSONObject(0);

            int picNo = Integer.parseInt(result.getString("picNo"));
            String[] picUrls ,picTitles ;
            picUrls = new String[picNo];
            picTitles = new String[picNo];
            if (picNo != 0){
                for (int i = 0;i < picNo; i++){
                    picUrls[i] = picUrl.getJSONObject(i).getString("url");
                    picTitles[i] = picUrl.getJSONObject(i).getString("pic_title");
                }
            }

            tvDayTitle.setText(result.getString("title"));

            StringBuilder sb = new StringBuilder();
            sb.append("<html><body>");
            if (picNo != 0){
                sb.append("<img src="+picUrls[0]+" width=\"100%\">");
                sb.append("<h5 align=\"center\">"+picTitles[0]+"</h5>");
            }
            sb.append(result.getString("content").replace("\r\n","<br/>"));
            if (picNo > 1){
                for (int i = 1; i < picNo; i++){
                    sb.append("<img src="+picUrls[i]+" width=\"100%\">");
                    sb.append("<h5 align=\"center\">"+picTitles[i]+"</h5>");
                }
            }
            sb.append("</body></html>");
            wbContent.loadDataWithBaseURL(null, sb.toString(), "text/html", "UTF-8", null);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void share(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "来自「历史上的今天」的分享" + title);
        startActivity(Intent.createChooser(intent, title));
    }
}

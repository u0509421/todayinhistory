package com.u0509421.todayinhistory.Activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.u0509421.todayinhistory.R;

import java.net.URL;

/**
 * Created by Terry on 20/3/16.
 */
public class DayActivity extends AppCompatActivity {

    private TextView tvDayContent;
    private static final String KEY = "http://v.juhe.cn/todayOnhistory/queryDetail.php?key=a87c2d7033aedc2b2460de9117588285&e_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_page);

        tvDayContent = (TextView) findViewById(R.id.tvDayContent);

        Intent intent = getIntent();
        String url = KEY + intent.getStringExtra("e_id");
        System.out.println(url);

//        SpannableStringBuilder builder = new SpannableStringBuilder();
//        builder.append("My String. I").
//                append(" ", new ImageSpan(this,R.drawable.author),0).
//                append(" Cree by Dexode");
//        tvDayContent.setText(builder);
//        Html.ImageGetter imgGetter2 = new Html.ImageGetter() {
//            @Override
//            public Drawable getDrawable(String source) {
//                Drawable drawable = null;
//                URL url;
//                try {
//                    url = new URL(source);
//                    drawable = Drawable.createFromStream(url.openStream(), "");
//                } catch (Exception e) {
//                    return null;
//                }
//
//                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//                return drawable;
//            }
//        };
//        tvDayContent.append(Html.fromHtml("网络下载的图片加上空格防止不"
//
//                +"到换行的时候字就因为图片的原因被换行       <img src=\""+url+"\">此处也可以加文字",imgGetter2, null) );
    }
}

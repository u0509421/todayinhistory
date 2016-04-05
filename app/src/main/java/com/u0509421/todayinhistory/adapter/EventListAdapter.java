package com.u0509421.todayinhistory.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.u0509421.todayinhistory.bean.EventList;
import com.u0509421.todayinhistory.bean.EventListHolder;
import com.u0509421.todayinhistory.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Terry on 17/3/16.
 */
public class EventListAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<EventList>arrayList = new ArrayList<EventList>();
    private Typeface typeface;

    public EventListAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
//        this.typeface = Typeface.createFromAsset(mContext.getAssets(),"fonts/fangzhenglantingzhunheijianti.ttf");
    }

    public void setData(ArrayList<EventList>list){
        arrayList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EventListHolder holder = null;
        if (convertView == null){
            holder = new EventListHolder();
            convertView = mLayoutInflater.inflate(R.layout.cell_event_listview,null);
            holder.tvDay = (TextView) convertView.findViewById(R.id.tvDay);
            holder.tvEventListDay = (TextView) convertView.findViewById(R.id.tvEventListDay);
            holder.tvEventListTitle = (TextView) convertView.findViewById(R.id.tvEventListTitle);
            convertView.setTag(holder);
        }else {
            holder = (EventListHolder) convertView.getTag();
        }

        String[] day = arrayList.get(position).getDate().split("年");
        //设置字体
        holder.tvEventListDay.setTypeface(typeface);
        holder.tvEventListTitle.setTypeface(typeface);
        holder.tvEventListTitle.setText(arrayList.get(position).getTitle());
        holder.tvEventListDay.setText(arrayList.get(position).getDate());
        holder.tvDay.setText(day[0]);
        holder.tvDay.setTextColor(Color.parseColor(RandomColor()));
        return convertView;
    }

    private String RandomColor(){
        String randomColor = null;
        int random = (int) (Math.random()*10 + 1);
        switch (random){
            case 0:
                randomColor = "#2dd4ca";
                break;
            case 1:
                randomColor = "#b30707";
                break;
            case 2:
                randomColor = "#da70d6";
                break;
            case 3:
                randomColor = "#17324e";
                break;
            case 4:
                randomColor = "#6ba6d4";
                break;
            case 5:
                randomColor = "#da70d6";
                break;
            case 6:
                randomColor = "#2585ae";
                break;
            case 7:
                randomColor = "#a5bd97";
                break;
            case 8:
                randomColor = "#000000";
                break;
            case 9:
                randomColor = "#a7823d";
                break;
            case 10:
                randomColor = "#a0a0a0";
                break;
        }
        return randomColor;
    }
}

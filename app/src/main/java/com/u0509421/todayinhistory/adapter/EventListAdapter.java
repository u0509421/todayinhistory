package com.u0509421.todayinhistory.adapter;

import android.content.Context;
import android.content.res.AssetManager;
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
        this.typeface = Typeface.createFromAsset(mContext.getAssets(),"fonts/fangzhenglantingzhunheijianti.ttf");
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
            holder.tvEventListDay = (TextView) convertView.findViewById(R.id.tvEventListDay);
            holder.tvEventListTitle = (TextView) convertView.findViewById(R.id.tvEventListTitle);
            convertView.setTag(holder);
        }else {
            holder = (EventListHolder) convertView.getTag();
        }

        //设置字体
        holder.tvEventListDay.setTypeface(typeface);
        holder.tvEventListTitle.setTypeface(typeface);
        holder.tvEventListTitle.setText(arrayList.get(position).getTitle());
        holder.tvEventListDay.setText(arrayList.get(position).getDate());

        return convertView;
    }

}

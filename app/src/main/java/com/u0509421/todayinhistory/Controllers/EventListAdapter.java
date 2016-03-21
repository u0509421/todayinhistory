package com.u0509421.todayinhistory.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.u0509421.todayinhistory.Model.EventList;
import com.u0509421.todayinhistory.Model.EventListHolder;
import com.u0509421.todayinhistory.R;

import java.util.ArrayList;

/**
 * Created by Terry on 17/3/16.
 */
public class EventListAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<EventList>arrayList = new ArrayList<EventList>();


    public EventListAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
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
            convertView = mLayoutInflater.inflate(R.layout.event_listview_cell,null);
//            holder.ivEventList = (ImageView) convertView.findViewById(R.id.ivEventList);
            holder.tvEventListDay = (TextView) convertView.findViewById(R.id.tvEventListDay);
            holder.tvEventListTitle = (TextView) convertView.findViewById(R.id.tvEventListTitle);
            convertView.setTag(holder);
        }else {
            holder = (EventListHolder) convertView.getTag();
        }
        holder.tvEventListTitle.setText(arrayList.get(position).getTitle());
        holder.tvEventListDay.setText(arrayList.get(position).getDate());
        return convertView;
    }

    private int getDynasty(String date){

        String[] temp = date.split("年");
        String first = temp[0];
        int year = 0;
        if (first.substring(1,2).equals("前")){
            year = - Integer.parseInt(first.substring(2));
        }else {
            year = Integer.parseInt(temp[0]);
        }

        int dynasty = 0;
        if (year > 1949){
            //中华人民共和国
        }else if (year > 1912){
            //民国
        }else if (year > 1636){
            //清朝
        }else if (year > 1368){
            //明朝
        }else if (year > 1271){
            //元朝
        }else if (year > 1127){
            //南宋
        }else if (year > 960){
            //北宋
        }else if (year > 891){
            //五代十国
        }else if (year > 618){
            //唐朝
        }else if (year > 581){
            //隋朝
        }else if (year > 420){
            //南北朝
        }else if (year > 266){
            //晋朝
        }else if (year > 220){
            //三国
        }else if (year > -202){
            //汉朝
        }else if (year > -221){
            //秦朝
        }else if (year > -475){
            //战国
        }else if (year > -770){
            //春秋
        }else if (year > -1046){
            //周朝
        }else if (year > -1559){
            //商朝
        }else if (year > -2029){
            //夏朝
        }else {
            //远古时代
        }
        return dynasty;
    }
}
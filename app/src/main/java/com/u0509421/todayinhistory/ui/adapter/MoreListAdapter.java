package com.u0509421.todayinhistory.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.u0509421.todayinhistory.db.bean.MoreItem;
import com.u0509421.todayinhistory.R;

import java.util.ArrayList;

/**
 * Created by Terry on 23/3/16.
 */
public class MoreListAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<MoreItem>arrayList = new ArrayList<>();

    public MoreListAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    public void setData(ArrayList<MoreItem>list){
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
        MoreItemHolder holder = null;
        if (convertView == null){
            holder = new MoreItemHolder();
            convertView = mLayoutInflater.inflate(R.layout.cell_more_frag,null);
            holder.tvItem = (TextView) convertView.findViewById(R.id.tvItem);
            holder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
            convertView.setTag(holder);
        }else {
            holder = (MoreItemHolder) convertView.getTag();
        }
        holder.tvItem.setText(arrayList.get(position).getItem());
        TextPaint tp = holder.tvItem.getPaint();
        tp.setFakeBoldText(true);
        switch (position){
            case 0:
                Drawable drawable = convertView.getResources().getDrawable(R.drawable.information);
                holder.ivImage.setImageDrawable(drawable);
                break;
            case 1:
                drawable = convertView.getResources().getDrawable(R.drawable.thumb);
                holder.ivImage.setImageDrawable(drawable);
                break;
            case 2:
                drawable = convertView.getResources().getDrawable(R.drawable.linkedin);
                holder.ivImage.setImageDrawable(drawable);
                break;
            case 3:
                drawable = convertView.getResources().getDrawable(R.drawable.github);
                holder.ivImage.setImageDrawable(drawable);
        }
        return convertView;
    }

    public static class MoreItemHolder {

        public TextView tvItem = null;
        public ImageView ivImage = null;
        public MoreItemHolder(){
            super();
        }
    }
}

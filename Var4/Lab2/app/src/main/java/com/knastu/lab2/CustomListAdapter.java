package com.knastu.lab2;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {


    private List<Student> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext, List<Student> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Формирование layout строки listView
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView4);
            holder.rating = (RatingBar) convertView.findViewById(R.id.ratingBar3);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Заполненик layout для строки listView
        Student student = this.listData.get(position);
        holder.name.setText(student.name);
        holder.rating.setRating((float)student.rating);

        return convertView;
    }

    // Структура layout строки listView
    static class ViewHolder {
        TextView name;
        RatingBar rating;
    }
}


package com.example.lab3;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private List<Food> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext, List<Food> listData) {
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
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.imageView);
            holder.name = (TextView) convertView.findViewById(R.id.textViewName);
            holder.weight = (TextView) convertView.findViewById(R.id.textViewWeight);
            holder.price = (TextView) convertView.findViewById(R.id.textViewPrice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Заполненик layout для строки listView
        Food food = this.listData.get(position);
        holder.image.setImageDrawable(context.getResources().getDrawable(food.image));
        holder.name.setText(food.name);
        holder.weight.setText(food.weight);
        holder.price.setText(food.price);

        // Установка обработчика
        convertView.setOnClickListener(v -> {
            MainActivity.updateTextViewInfo(food.name);
        });

        return convertView;
    }

    // Структура layout строки listView
    static class ViewHolder {
        ImageView image;
        TextView name;
        TextView weight;
        TextView price;
    }
}


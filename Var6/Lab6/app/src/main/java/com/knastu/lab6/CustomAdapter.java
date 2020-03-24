package com.knastu.lab6;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

// Адаптер отвечает за отображение ListView. Его нужно создавать если ListView содержит что-то сложнее, чем набор строк.
class CustomAdapter implements ListAdapter {
    ArrayList<Employee> arrayList;
    LayoutInflater layoutInflater;

    public CustomAdapter(Context context, ArrayList<Employee> arrayList) {
        this.arrayList=arrayList;
        layoutInflater = LayoutInflater.from(context);
    }
    //// Ниже описаны методы класса ListAdapter, которые мы обязаны определить для кастомного адаптера
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
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
    public boolean hasStableIds() {
        return false;
    }
    //// Главный метод, формирующий отображение+данные для ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Данные строки (элемента ListView )
        class ViewHolder {
            TextView name;
            ImageView photo;
        };

        // Формирование строки (элемента ListView) о сотруднике
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView);
            holder.photo = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Заполнение строки данными
        Employee employee = this.arrayList.get(position);
        // Установка имени сотрудника
        holder.name.setText(employee.Name);
        // Установка картинки сотрудника
        try {
            InputStream ims = null;
            ims = convertView.getContext().getAssets().open(employee.Image);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            holder.photo.setImageDrawable(d);
            ims .close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertView;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
}

package com.knastu.lab7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<Item> {
    public UsersAdapter(Context context, ArrayList<Item> users, ListView lv) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(user.item);
        return convertView;
    }
}

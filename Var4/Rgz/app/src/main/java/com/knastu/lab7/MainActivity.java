package com.knastu.lab7;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ListView listView_items;
    TextView  itemSelected;
    ArrayList<Item> arrayOfUsers ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        listView_items = (ListView) findViewById(R.id.listView_items);
        itemSelected = (TextView) findViewById(R.id.textView4);

        arrayOfUsers = new ArrayList();
        arrayOfUsers.add(new Item("item 1"));
        arrayOfUsers.add(new Item("item 2"));
        arrayOfUsers.add(new Item("item 3"));
        arrayOfUsers.add(new Item("item 4"));
        arrayOfUsers.add(new Item("item 5"));
        UsersAdapter adapter = new UsersAdapter(this, arrayOfUsers, listView_items);

        listView_items.setAdapter(adapter);

        listView_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                itemSelected.setText(String.valueOf(position+1));
                if (View.VISIBLE == listView_items.getVisibility())
                    listView_items.setVisibility(View.INVISIBLE);
                else listView_items.setVisibility(View.VISIBLE);
            }
        });
        listView_items.setVisibility(View.INVISIBLE);
    }

    public void onClickMenu(View view) {
        if (View.VISIBLE == listView_items.getVisibility())
            listView_items.setVisibility(View.INVISIBLE);
        else listView_items.setVisibility(View.VISIBLE);
    }
}


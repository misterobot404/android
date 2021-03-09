package com.example.lab2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mCatNameList = new ArrayList<>();
    private MyArrayAdapter mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        initList();

        mArrayAdapter = new MyArrayAdapter(this, R.layout.list_item,
                android.R.id.text1, mCatNameList);

        listView.setAdapter(mArrayAdapter);
        listView.setOnItemClickListener(myOnItemClickListener);
    }

    public void onClick(View view) {
        String result = "";
        List<String> resultList = mArrayAdapter.getCheckedItems();
        for (int i = 0; i < resultList.size(); i++) {
            result += resultList.get(i) + "\n";
        }

       TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(result);
    }

    private void initList() {
        mCatNameList.add("Рыжик");
        mCatNameList.add("Барсик");
        mCatNameList.add("Мурзик");
        mCatNameList.add("Мурка");
        mCatNameList.add("Васька");
        mCatNameList.add("Томасина");
        mCatNameList.add("Кристина");
        mCatNameList.add("Пушок");
        mCatNameList.add("Дымка");
    }

    AdapterView.OnItemClickListener myOnItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            mArrayAdapter.toggleChecked(position);
        }
    };

    private class MyArrayAdapter extends ArrayAdapter<String> {

        private HashMap<Integer, Boolean> mCheckedMap = new HashMap<>();

        MyArrayAdapter(Context context, int resource,
                       int textViewResourceId, List<String> objects) {
            super(context, resource, textViewResourceId, objects);

            for (int i = 0; i < objects.size(); i++) {
                mCheckedMap.put(i, false);
            }
        }

        void toggleChecked(int position) {
            if (mCheckedMap.get(position)) {
                mCheckedMap.put(position, false);
            } else {
                mCheckedMap.put(position, true);
            }

            notifyDataSetChanged();
        }

        List<String> getCheckedItems() {
            List<String> checkedItems = new ArrayList<>();

            for (int i = 0; i < mCheckedMap.size(); i++) {
                if (mCheckedMap.get(i)) {
                    (checkedItems).add(mCatNameList.get(i));
                }
            }

            return checkedItems;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;

            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.list_item, parent, false);
            }

            CheckedTextView checkedTextView = (CheckedTextView) row
                    .findViewById(R.id.checkedTextView);
            checkedTextView.setText(mCatNameList.get(position));

            Boolean checked = mCheckedMap.get(position);
            if (checked != null) {
                checkedTextView.setChecked(checked);
            }

            return row;
        }
    }
}
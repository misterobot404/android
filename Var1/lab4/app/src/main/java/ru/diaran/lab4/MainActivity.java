package ru.diaran.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listView1 = new ArrayList<String>();
    ArrayAdapter<String> adapter1;

    ArrayList<String> selected1 = new ArrayList<String>();
    ListView listViewList1;

    ArrayList<String> listView2 = new ArrayList<String>();
    ArrayAdapter<String> adapter2;

    ArrayList<String> selected2 = new ArrayList<String>();
    ListView listViewList2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewList1 = (ListView) findViewById(R.id.listView1);
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, listView1);
        listViewList1.setAdapter(adapter1);

        listViewList2 = (ListView) findViewById(R.id.listView2);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, listView2);
        listViewList2.setAdapter(adapter2);

        // обработка установки и снятия отметки в списке
        listViewList1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // получаем нажатый элемент
                String item = adapter1.getItem(position);
                if(listViewList1.isItemChecked(position)){
                    selected1.add(item);
                }
                else{

                    selected1.remove(item);
                }
            }
        });

        // обработка установки и снятия отметки в списке
        listViewList2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // получаем нажатый элемент
                String phone = adapter2.getItem(position);
                if(listViewList2.isItemChecked(position)){
                    selected2.add(phone);
                }
                else{

                    selected2.remove(phone);
                }
            }
        });

    }

    public void add(View view){

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        if (!checkBox.isChecked()){
            EditText listViewEditText = (EditText) findViewById(R.id.editText_1);
            String item = listViewEditText.getText().toString();
            if(!item.isEmpty() && !listView1.contains(item)){
                adapter1.add(item);
                listViewEditText.setText("");
                adapter1.notifyDataSetChanged();
            }
        }else{
            EditText listViewEditText = (EditText) findViewById(R.id.editText_1);
            String item = listViewEditText.getText().toString();
            if(!item.isEmpty() && !listView2.contains(item)){
                adapter2.add(item);
                listViewEditText.setText("");
                adapter2.notifyDataSetChanged();
            }
        }
    }
}

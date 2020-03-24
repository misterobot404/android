
// Из базы данных dbdemos.db3 через таблицы parts, orders и items вывести время прихода корабля для заданного груза.
package com.knastu.lab6;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {

    final String dbName = "dbdemos.db3";
    SQLiteDatabase db;
    DBHelper dbHelper;

    EditText times_editText;
    ListView items_listView;
    ArrayList<String> items = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        times_editText = (EditText) findViewById(R.id.multilineText_times);
        items_listView = (ListView) findViewById(R.id.listView_items);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        items_listView.setAdapter(adapter);
        items_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = items.get(position);
                // делаем запрос всех данных из таблицы dbdemon, получаем Cursor
                Cursor c = db.rawQuery("select ShipDate FROM orders join items ON orders.OrderNo = items.OrderNo join parts ON items.PartNo = parts.PartNo where parts.Description = ?", new String[]{item});
                // ставим позицию курсора на первую строку выборк если в выборке нет строк, вернется false
                if (c.moveToFirst()) {
                    times_editText.setText("");
                    int description = c.getColumnIndex("ShipDate");
                    do {
                        // получаем значения по номерам столбцов и пишем все в лог
                        times_editText.setText(times_editText.getText() + c.getString(description) + "\n");
                        // переход на следующую строку а если следующей нет (текущая - последняя), то false - выходим из цикла
                    } while (c.moveToNext());
                }
                c.close();
            }
        });

        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(this);

        // подключаемся к БД
        db = dbHelper.getWritableDatabase();

        // делаем запрос всех данных из таблицы dbdemon, получаем Cursor
        Cursor c = db.rawQuery("SELECT DISTINCT Description FROM parts", null);

        // ставим позицию курсора на первую строку выборк если в выборке нет строк, вернется false
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int description = c.getColumnIndex("Description");
            do {
                // получаем значения по номерам столбцов и пишем все в лог
                items.add(c.getString(description));
                // переход на следующую строку а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        }
        c.close();
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, dbName, null, 1);

            File dbFile = context.getDatabasePath(dbName);

            if (!dbFile.exists()) {
                dbFile.getParentFile().mkdirs();
                InputStream is = null;
                try {
                    is = context.getAssets().open(dbName);
                    OutputStream os = new FileOutputStream(dbFile);

                    byte[] buffer = new byte[1024];
                    while (is.read(buffer) > 0) {
                        os.write(buffer);
                    }

                    os.flush();
                    os.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}


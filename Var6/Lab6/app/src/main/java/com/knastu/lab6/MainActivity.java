
// В одной таблице хранятся наименование профессий, в другой – данные по людям (с фотографией).
// Приложение выводит информацию по людям заданной профессии.

package com.knastu.lab6;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {
    // Название БД
    String dbName = "myDB.db3";
    // БД
    SQLiteDatabase db;
    // Список профессий
    ArrayList<String> items = new ArrayList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Список профессий
        ListView items_listView = (ListView) findViewById(R.id.listView_items);
        // Создание адаптера (типа отображения каждой строки в ListView)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        // Установка адаптера (типа отображения) для списка профессий
        items_listView.setAdapter(adapter);

        // создаем объект создания и управления версиями БД
        DBHelper dbHelper = new DBHelper(this);
        // подключаемся к БД
        db = dbHelper.getWritableDatabase();
        // делаем запрос на получение профессий из таблицы professions, получаем Cursor с данными
        Cursor c = db.rawQuery("SELECT description FROM professions", null);

        // ставим позицию курсора на первую строку выборк если в выборке нет строк, вернется false
        if (c.moveToFirst()) {
            // получаем столбцы для выборки из курсора
            int name = c.getColumnIndex("description");
            do {
                // Добавляем в список профессий одну профессию (строку)
                items.add(c.getString(name));
            } while (c.moveToNext());
        }
        c.close();

        // Событие нажатия на элемент списка профессий
        items_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = items.get(position);
                // делаем запрос всех данных из таблицы dbdemon, получаем Cursor
                Cursor c = db.rawQuery("select name, photo from staff where profession_id=(select id from professions where description=?)", new String[]{item});
                // ставим позицию курсора на первую строку выборк если в выборке нет строк, вернется false
                if (c.moveToFirst()) {
                    // Список сотрудников
                    ArrayList<Employee> employees = new ArrayList();
                    // добавляем по одному в список
                    int name = c.getColumnIndex("name");
                    int img = c.getColumnIndex("photo");
                    do {
                        employees.add(new Employee(c.getString(name),c.getString(img)));
                        // переход на следующую строку а если следующей нет (текущая - последняя), то false - выходим из цикла
                    } while (c.moveToNext());
                    ((ListView) findViewById(R.id.emploees_listView)).setAdapter(new CustomAdapter(view.getContext(), employees));
                }
                c.close();
            }
        });
    }

    // Вспомогательный класс для управления созданием базы данных
    class DBHelper extends SQLiteOpenHelper {
        // Конструктор класса, который будет вызван при создании обьекта DBHelper
        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, dbName, null, 1);

            // Инициализируеб файл базой данных
            File dbFile = context.getDatabasePath(dbName);
            // Если БД не существует, копируем БД из папки assets
            if (!dbFile.exists()) {
                dbFile.getParentFile().mkdirs();
                try {
                    // Открываем поток на чтение БД из asset
                    InputStream is = context.getAssets().open(dbName);
                    // Открываем поток на запись локальной БД
                    OutputStream os = new FileOutputStream(dbFile);

                    // Записываем
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
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    }
}


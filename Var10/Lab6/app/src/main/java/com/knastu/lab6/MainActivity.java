/*
В базе данных хранится информация в двух таблицах: данные о водителе, данные о автомобиле.
Приложение должно выводить на экран информацию об автомобиле и владельце по заданному номеру (или его части) автомобиля.
*/

package com.knastu.lab6;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends Activity {

    final String dbName = "myDB.db3";
    SQLiteDatabase db;
    DBHelper dbHelper;

    EditText info_editText, number_editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info_editText = (EditText) findViewById(R.id.multilineText_times);
        number_editText = (EditText) findViewById(R.id.editText);

        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(this);
        // подключаемся к БД
        db = dbHelper.getWritableDatabase();

        number_editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence number, int start,
                                      int before, int count) {
                info_editText.setText("");

                // делаем запрос всех данных, получаем Cursor
                String[] selectionArgs = new String[] { number.toString().trim() + "%" };
                Cursor c = db.rawQuery("select drivers.name, cars.model from cars join drivers on cars.driver_id = drivers.id where cars.number LIKE ?", selectionArgs);
                // ставим позицию курсора на первую строку выборк если в выборке нет строк, вернется false
                if (c.moveToFirst()) {
                    int name = c.getColumnIndex("name");
                    int model = c.getColumnIndex("model");
                    do {
                        // получаем значения по номерам столбцов и пишем все в лог
                        info_editText.setText(info_editText.getText() + "Имя владельца: " + c.getString(name) + "\n");
                        info_editText.setText(info_editText.getText() + "Машина: " + c.getString(model) + "\n\n");
                        // переход на следующую строку а если следующей нет (текущая - последняя), то false - выходим из цикла
                    } while (c.moveToNext());
                }
                c.close();
            }
        });
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


package com.knastu.file001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    // определяем размер буфера при считывании с файла
    private static final int READ_BLOCK_SIZE = 100;

    private SharedPreferences sharedPrefs;
    public static final String myPrefs = "MyPrefs";
    public static final String nameKey = "MyKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edit_field);

        sharedPrefs = getSharedPreferences(myPrefs, Context.MODE_PRIVATE);
        if (sharedPrefs.contains(nameKey)) {
            // если есть, то ставим значение этого ключа в EditText
            editText.setText(sharedPrefs.getString(nameKey, ""));
        }
    }

    // метод для записи в файл
    public void writeToFile(View v) {
        // открываем файл по названию
        try {
            FileOutputStream fileOutputStream = openFileOutput("simplefile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOutputStream);
            outputWriter.write(editText.getText().toString());
            outputWriter.close();

            // создаем всплывающее окно c результатом выполнения записи в файл
            Toast.makeText(getBaseContext(), "Запись в файл успешно проведена!",
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // метод для чтения из файла
    public void readFromFile(View v) {
        try {
            FileInputStream fileInputStream = openFileInput("simplefile.txt");
            InputStreamReader reader = new InputStreamReader(fileInputStream);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            // цикл читает данные из файла,
            while ((charRead = reader.read(inputBuffer)) != -1) {
                // конвертируем char в строку
                String rString = String.copyValueOf(inputBuffer, 0, charRead);
                s += rString;
            }
            reader.close();
            editText.setText(s);
            // создаем всплывающее окно c результатом выполнения чтения из файла
            Toast.makeText(getBaseContext(), "Чтение из файла успешно проведено!", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // метод для сохранения текста в файл настроек
    public void saveText(View v) {
// получаем текст
        String editTextValue = editText.getText().toString();

// получаем доступ к файлу
        SharedPreferences.Editor editor = sharedPrefs.edit();
// сохраняем по текст из EditText по ключу nameKey
        editor.putString(nameKey, editTextValue);
        editor.apply();
    }
}

/*
На Activity поле ввода, многострочное текстовое поле и две кнопки: ПИСАТЬ, ЧИТАТЬ.
Программа запоминает количество запусков и каждые три запуска поздравляет пользователя, присуждая ему увеличивающуюся сумму баллов.
Кроме того, при нажатии на кнопку ПИСАТЬ в файл сохраняется текст из поля (дописывается в конец файла).
При нажатии на кнопку ЧИТАТЬ – в многострочное поле выводится весь текст, накопленный в файле.
*/

package com.knastu.file001;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    // Основные элементы интерфейса
    EditText editText_fileContent;

    // Обьекты для работы с конфигом приложения
    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_fileContent = (EditText) findViewById(R.id.editText_fileContent);

        // Связываем обьект конфигурации с файлом
        sharedPrefs = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        // получаем доступ к редактированию файла конфигурации

        SharedPreferences.Editor editor = sharedPrefs.edit();

        if (!sharedPrefs.contains("runCount"))
            editor.putString("runCount", "0");

        Integer runCount = Integer.parseInt(sharedPrefs.getString("runCount", "0"));
        editor.putString("runCount", String.valueOf(++runCount));
        editor.apply();

        if (runCount % 3 == 0 && runCount / 3 != 0)
            Toast.makeText(this, "Поздравляем! Сумма баллов: " + String.valueOf(runCount / 3), Toast.LENGTH_SHORT).show();
    }

    // Метод для записи в файл
    public void writeToFile(View v) {
        // открываем файл по названию
        try {
            FileOutputStream fileOutputStream = openFileOutput("text_file", MODE_PRIVATE);

            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOutputStream);
            outputWriter.write(editText_fileContent.getText().toString());
            outputWriter.close();

            // создаем всплывающее окно c результатом выполнения записи в файл
            Toast.makeText(getBaseContext(), "Запись в файл успешно проведена!",
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для чтения из файла
    public void readFromFile(View v) {
        try {
            FileInputStream fileInputStream = openFileInput("text_file");

            InputStreamReader reader = new InputStreamReader(fileInputStream);

            char[] inputBuffer = new char[30];
            String s = "";
            int charRead;
            // цикл читает данные из файла,
            while ((charRead = reader.read(inputBuffer)) != -1) {
                // конвертируем char в строку
                String rString = String.copyValueOf(inputBuffer, 0, charRead);
                s += rString;
            }
            reader.close();

            editText_fileContent.setText(s);
            // создаем всплывающее окно c результатом выполнения чтения из файла
            Toast.makeText(getBaseContext(), "Чтение из файла успешно проведено!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            editText_fileContent.setText("");
            e.printStackTrace();
        }
    }
}

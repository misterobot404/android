
//  На Activity две радиокнопки задают направление записи: внутренняя память или внешняя.
//  Поле ввода – задает имя файла.
//  Кнопка Запись записывает файл.
//  Кнопка Чтение – читает файл.
//  Содержимое файла – текстовое, набирается в отдельном многострочном поле.
//  Настройки записываются в файл конфигурации при закрытии программы и считываются при открытии.

package com.knastu.file001;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    // Основные элементы интерфейса
    RadioButton radioButton_inside, radioButton_outside;
    EditText editText_fileName, editText_fileContent;

    // Обьекты для работы с конфигом приложения
    SharedPreferences sharedPrefs;
    public static final String pref_saveType = "saveType";
    public static final String pref_fileName = "fileName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioButton_inside = (RadioButton) findViewById(R.id.radioButton_inside);
        radioButton_outside = (RadioButton) findViewById(R.id.radioButton_outside);
        editText_fileName = (EditText) findViewById(R.id.editText_fileName);
        editText_fileContent = (EditText) findViewById(R.id.editText_fileContent);

        // Связываем обьект с файлом конфигурации
        sharedPrefs = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        // Если существует ключ, означающий тип записи (на внутреннюю или внешнюю память), то устанавливаем объекту интерфейса нужное состояние
        if (sharedPrefs.contains(pref_saveType)) {

            String saveType = sharedPrefs.getString(pref_saveType, "");

            // Если тип записи - на внутреннюю память, устанавливаем нужный checkbox
            if (saveType.equals("inside")) radioButton_inside.setChecked(true);

            // Если тип записи - на внешнюю память, устанавливаем нужный checkbox
            if (saveType.equals("outside")) radioButton_outside.setChecked(true);
        }

        // Если существует ключ, означающий название файла, то устанавливаем объекту интерфейса нужное состояние
        if (sharedPrefs.contains(pref_fileName)) {

            String fileName = sharedPrefs.getString(pref_fileName, "");

            // Устанавливаем название в текстовое поле
            editText_fileName.setText(fileName);
            readFromFile(findViewById(android.R.id.content).getRootView());
        }
    }

    // Сохранение состояния приложения
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // получаем доступ к файлу конфигурации
        SharedPreferences.Editor editor = sharedPrefs.edit();

        // устанавливаем в файл конфигурации тип записи
        if (radioButton_inside.isChecked())  editor.putString(pref_saveType, "inside");
        else if (radioButton_outside.isChecked()) editor.putString(pref_saveType, "outside");

        // устанавливаем в файл конфигурации название файла
        editor.putString(pref_fileName, editText_fileName.getText().toString());

        // применяем изменения
        editor.apply();
    }

    // Метод для записи в файл
    public void writeToFile(View v) {
        // открываем файл по названию
        try {
            FileOutputStream fileOutputStream;

            // Если выбран режим записи на внешню память
            if (radioButton_outside.isChecked() == true) {


                // формируем объект File, который содержит путь к файлу
                File sdFile = new File(getExternalFilesDir("Lab5"),  editText_fileName.getText().toString());

                fileOutputStream = new FileOutputStream(sdFile);
            }
            // Если выбран режим записи на внутреннюю память
            else {
                fileOutputStream = openFileOutput(editText_fileName.getText().toString(), MODE_PRIVATE);
            }

            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOutputStream);
            outputWriter.write(editText_fileContent.getText().toString());
            outputWriter.close();

            // создаем всплывающее окно c результатом выполнения записи в файл
            Toast.makeText(getBaseContext(), "Запись в файл успешно проведена!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для чтения из файла
    public void readFromFile(View v) {
        try {
            FileInputStream fileInputStream;

            // Если выбран режим записи на внешню память
            if (radioButton_outside.isChecked() == true) {

                // формируем объект File, который содержит путь к файлу
                File sdFile = new File(getExternalFilesDir("Lab5"),  editText_fileName.getText().toString());

                String s = sdFile.getAbsolutePath();
                fileInputStream = new FileInputStream(sdFile);
            }
            // Если выбран режим записи на внутреннюю память
            else {
                fileInputStream = openFileInput(editText_fileName.getText().toString());
            }

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

package com.example.lab5;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MainActivity extends AppCompatActivity {

    // Объекты layout
    EditText editTextOutput, editTextInput, editTextSize, editTextColor;
    View settingsView;
    AlertDialog alert;

    // Объекты для работы с записью / чтением настроек
    SharedPreferences sharedPrefs;
    String fontSizeKey = "font-size";
    String fontColorKey = "font-color";
    String textFileName = "text.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = (EditText) findViewById(R.id.edit_field);
        editTextOutput = (EditText) findViewById(R.id.edit_field2);
        settingsView = LayoutInflater.from(this).inflate(R.layout.settings_dialog, null);
        editTextSize = (EditText) settingsView.findViewById(R.id.editText_textSize);
        editTextColor = (EditText) settingsView.findViewById(R.id.editText_textColor);

        // Создем AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Настраиваем AlertDialog
        {
            builder.setTitle("Настройка шрифта.");
            builder.setView(settingsView);
            builder.setPositiveButton("Применить", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    // Установка размера шрифта
                    String sizeStr = editTextSize.getText().toString().trim();
                    if (sizeStr.length() > 0) {
                        float size = Float.parseFloat(sizeStr);
                        editTextOutput.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
                        // Сохраняем настройки в файле конфигурации
                        saveSettings();
                        // Применяем настройки к приложению
                        loadSettings();
                    }

                    // Установка цвета шрифта
                    String colorStr = editTextColor.getText().toString().trim();
                    if (colorStr.length() > 0) {
                        editTextOutput.setTextColor(Color.parseColor(colorStr));
                        // Сохраняем настройки в файле конфигурации
                        saveSettings();
                        // Применяем настройки к приложению
                        loadSettings();
                    }
                }
            });
        }
        alert = builder.create();

        sharedPrefs = getSharedPreferences("Pref", Context.MODE_PRIVATE);
        fontSizeKey = "font-size";
        fontColorKey = "font-color";
        textFileName = "text.txt";

        loadSettings();
        loadTextFromFile();
    }

    // Загрузка состояния приложения
    public void loadSettings() {
        if (sharedPrefs.contains(fontSizeKey)) {
            float size = Float.parseFloat(sharedPrefs.getString(fontSizeKey, ""));
            editTextOutput.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            editTextInput.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        }

        if (sharedPrefs.contains(fontColorKey)) {
            String color = sharedPrefs.getString(fontColorKey, "");
            editTextOutput.setTextColor(Integer.parseInt(color));
            editTextInput.setTextColor(Integer.parseInt(color));
        }
        Toast.makeText(getBaseContext(), "Настройки загружены!", Toast.LENGTH_LONG).show();
    }

    // Получение текста из файла
    public void loadTextFromFile() {
        try {
            FileInputStream fileInputStream = openFileInput(textFileName);
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
            editTextOutput.setText(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // сохранения настроек
    public void saveSettings() {
        // Запись конфигурации
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(fontSizeKey, String.valueOf(editTextOutput.getTextSize()));
        editor.putString(fontColorKey, String.valueOf(editTextOutput.getCurrentTextColor()));
        editor.apply();

        Toast.makeText(getBaseContext(), "Настройки сохранены!", Toast.LENGTH_SHORT).show();
    }

    // отображение диалога настройки текста
    public void settings(View view) {
        editTextSize.setHint("Размер (текущий " + editTextOutput.getTextSize() + ")");
        editTextColor.setHint("Цвет (текущий " + String.format("#%X", editTextOutput.getCurrentTextColor()) + ")");
        alert.show();
    }

    // FileWriter
    public void clickButtonFileWriter(View view) throws IOException {
        File file = new File(getFilesDir(), textFileName);
        FileWriter fr = null;

        fr = new FileWriter(file, true);
        fr.write(editTextInput.getText().toString() + System.getProperty("line.separator"));

        fr.close();

        editTextInput.setText("");
        loadTextFromFile();
    }

    // BufferedWriter
    public void clickButtonBufferedWriter(View view) throws IOException {
        File file = new File(getFilesDir(), textFileName);
        FileWriter fr = null;
        BufferedWriter br = null;
        String dataWithNewLine = editTextInput.getText().toString() + System.getProperty("line.separator");

        fr = new FileWriter(file, true);
        br = new BufferedWriter(fr);

        br.write(dataWithNewLine);

        br.close();
        fr.close();

        editTextInput.setText("");
        loadTextFromFile();
    }

    // FileOutputStream
    public void clickButtonFileOutputStream(View view) throws IOException {
        FileOutputStream fileOutputStream = openFileOutput(textFileName, MODE_APPEND);
        OutputStreamWriter outputWriter = new OutputStreamWriter(fileOutputStream);
        outputWriter.write(editTextInput.getText().toString() + System.getProperty("line.separator"));
        outputWriter.close();

        editTextInput.setText("");
        loadTextFromFile();
    }

    // Files
    public void clickButtonFiles(View view) throws IOException {
        File file = new File(getFilesDir(), textFileName);
        if(!file.exists()) file.createNewFile();
        String data = editTextInput.getText().toString() + System.getProperty("line.separator");
        Files.write(file.toPath(), data.getBytes(), StandardOpenOption.APPEND);

        editTextInput.setText("");
        loadTextFromFile();
    }

    // Прочитать данные из файла
    public void clickButtonRead(View view) {
        loadTextFromFile();
    }

    // Удалить файл
    public void clickButtonDeleteTextFile(View view) {
        File file = new File(getFilesDir(), textFileName);
        file.delete();
        editTextOutput.setText("");
    }
}

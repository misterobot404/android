
// На Activity вводится текст. С помощью диалога устанавливается цвет и размер текста.
// При нажатии на кнопку Сохранить текст сохраняется  во внутренней памяти устройства. А настройки шрифта сохраняются в файле конфигурации.
// При нажатии на кнопку Читать - текст восстанавливается с цветом и размером шрифта из файла конфигурации.

package com.knastu.Lab5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    // Объекты layout
    EditText editText;
    View settingsView;
    EditText editTextSize;
    EditText editTextColor;
    AlertDialog alert;

    // Объекты для работы с записью / чтением настроек
    SharedPreferences sharedPrefs;
    String fontSizeKey = "font-size";
    String fontColorKey = "font-color";
    String SettingsFileName = "text.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edit_field);
        settingsView = LayoutInflater.from(this).inflate(R.layout.settings_dialog, null);
        editTextSize = (EditText) settingsView.findViewById(R.id.editText_textSize);
        editTextColor = (EditText) settingsView.findViewById(R.id.editText_textColor);

        // Создем builder для AlertDialog'a
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Настраиваем Builder
        {
            builder.setTitle("Настройка шрифта.");
            builder.setView(settingsView);
            builder.setPositiveButton("Применить", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    // Установка размера шрифта
                    String sizeStr = editTextSize.getText().toString().trim();
                    if (sizeStr.length()>0) {
                        float size = Float.parseFloat(sizeStr);
                        editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
                    }

                    // Установка цвета шрифта
                    String colorStr = editTextColor.getText().toString().trim();
                    if (colorStr.length()>0) {
                        editText.setTextColor(Color.parseColor(colorStr));
                    }
                }
            });
        }
        // Создали объект AlertDialog, готовый к выводу на экран
        alert = builder.create();

        sharedPrefs = getSharedPreferences("Pref", Context.MODE_PRIVATE);
        fontSizeKey = "font-size";
        fontColorKey = "font-color";
        SettingsFileName = "text.txt";

        loadSettings(getWindow().getCurrentFocus());
    }

    // метод для загрузки настроек
    public void loadSettings(View v) {

        // Получение текста из файла
        try {
            FileInputStream fileInputStream = openFileInput(SettingsFileName);
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
            editText.setText(s);

            if (sharedPrefs.contains(fontSizeKey)) {
                float size = Float.parseFloat(sharedPrefs.getString(fontSizeKey, ""));
                editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            }

            if (sharedPrefs.contains(fontColorKey)) {
                String color = sharedPrefs.getString(fontColorKey, "");
                editText.setTextColor(Integer.parseInt(color));
            }
            Toast.makeText(getBaseContext(), "Настройки загружены!", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // сохранения настроек
    public void saveSettings(View v) {
        try {
            // Запись текста в файл
            FileOutputStream fileOutputStream = openFileOutput(SettingsFileName, MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOutputStream);
            outputWriter.write(editText.getText().toString());
            outputWriter.close();

            // Запись конфигурации
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(fontSizeKey, String.valueOf(editText.getTextSize()));
            editor.putString(fontColorKey, String.valueOf(editText.getCurrentTextColor()));
            editor.apply();

            Toast.makeText(getBaseContext(), "Настройки сохранены!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void settings(View view) {
        editTextSize.setHint("Размер (текущий " + editText.getTextSize() + ")");
        editTextColor.setHint("Цвет (текущий " + String.format("#%X", editText.getCurrentTextColor()) + ")");
        alert.show();
    }
}

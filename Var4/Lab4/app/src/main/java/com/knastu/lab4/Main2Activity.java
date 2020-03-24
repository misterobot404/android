package com.knastu.lab4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private String[] items;
    private boolean[] stateCheckBox;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items = new String[]{"Лук", "Салат", "Помидор"};

        stateCheckBox = new boolean[3];
        stateCheckBox = getIntent().getBooleanArrayExtra("stateCheckBox");


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        // Создание кастомного Title
        TextView textView = new TextView(this);
        textView.setText("Выберите продукты:");
        textView.setPadding(60, 60, 40, 60);
        textView.setTextSize(25F);
        textView.setTextColor(Color.parseColor("#23AFE2"));
        builder.setCustomTitle(textView);

        // Формируем View для вставки в содержимое диалога
        builder.setMultiChoiceItems(items, stateCheckBox, new
                DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int which, boolean
                            isChecked) {
                        Toast.makeText(getBaseContext(), items[which] + ": " + isChecked, Toast.LENGTH_LONG).show();
                    }
                });

        // Устанавливаем слушатель события "Нажали кнопку Yes"
        builder.setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Intent intent = new Intent();
                intent.putExtra("stateCheckBox", stateCheckBox);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        // Устанавливаем слушатель события "Нажали кнопку No"
        builder.setNegativeButton("Вернуться", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}

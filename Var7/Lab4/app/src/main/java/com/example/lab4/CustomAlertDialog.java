package com.example.lab4;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAlertDialog extends AppCompatActivity {
    private String msg;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        // Создание кастомного Title
        TextView textView = new TextView(this);
        textView.setText("Собственное диалоговое окно!");
        textView.setPadding(60, 60, 40, 60);
        textView.setTextSize(25F);
        textView.setTextColor(Color.parseColor("#23AFE2"));
        builder.setCustomTitle(textView);

        // Формируем View для вставки в содержимое диалога
        builder.setView(R.layout.custom_dialog_content);

        // Устанавливаем слушатель события "Нажали кнопку Yes"
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Intent intent = new Intent();
                intent.putExtra("msg", "Нажата ДА");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        // Устанавливаем слушатель события "Нажали кнопку No"
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent();
                intent.putExtra("msg", "Нажата ОТМЕНА");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}

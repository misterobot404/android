package com.example.lab4;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAlertDialog2 extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Отображаем полученное сообщение с предыдущего Activity
        Toast.makeText(this, getIntent().getStringExtra("msg"), Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        // Создание кастомного Title
        TextView textView = new TextView(this);
        textView.setText("Выберите");
        textView.setPadding(60, 60, 40, 60);
        textView.setTextSize(25F);
        textView.setTextColor(Color.parseColor("#23AFE2"));
        builder.setCustomTitle(textView);

        // Формируем View для вставки в содержимое диалога
        builder.setView(R.layout.custom_dialog_content2);

        // Устанавливаем слушатель события "Нажали кнопку Yes"
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getApplicationContext(), "Нажата кнопка 'Да'", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // Устанавливаем слушатель события "Нажали кнопку No"
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getApplicationContext(), "Нажата кнопка 'Нет'", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}

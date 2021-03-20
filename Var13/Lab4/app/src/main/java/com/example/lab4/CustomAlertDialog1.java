package com.example.lab4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAlertDialog1 extends AppCompatActivity {
    EditText editText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Отображаем полученное сообщение с предыдущего Activity
        Toast.makeText(this, getIntent().getStringExtra("msg"), Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        // Создание кастомного Title
        TextView textView = new TextView(this);
        textView.setText("Введите имя");
        textView.setPadding(60, 60, 40, 60);
        textView.setTextSize(25F);
        textView.setTextColor(Color.parseColor("#23AFE2"));
        builder.setCustomTitle(textView);

        // Формируем View для вставки в содержимое диалога
        builder.setView(R.layout.custom_dialog_content1);

        // Устанавливаем слушатель события "Нажали кнопку Yes"
        builder.setPositiveButton("Отправить", (dialog, which) -> {
            Intent intent = new Intent(this, CustomAlertDialog2.class);
            intent.putExtra("msg", "Нажата кнопка 'Отправить'. Введенное имя: " + editText.getText().toString());
            startActivity(intent);
            finish();
        });

        AlertDialog alert = builder.create();
        alert.show();
        editText = (EditText) alert.findViewById(R.id.editTextTextPersonName);
    }
}

/*Все тексты и заголовки представить на русском языке. На каждую кнопку присоединить сообщение*/

package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickForward(View view) {
        // Говорим между какими Activity будет происходить связь
        Intent intent = new Intent(this, CustomAlertDialog1.class);
        // Добавляем сообщение для передачи
        intent.putExtra("msg", "Нажата кнопка 'Открыть кастомное диалоговое окно'");
        // показываем новое Activity
        startActivity(intent);
    }
}

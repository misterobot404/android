package com.knastu.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private int hour, minute;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        // Устанавливаем текущее время полям класса
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        // Обновляем текстовое поле со значением времени
        textView.setText("Часы: " + hour + " Минуты: " + minute);
    }

    //  Событие нажатия на кнопку "Перейти к выбору", которое запускает новое Activity
    public void onClickForward(View view) {
        // Устанавливаем между какими Activity будет происходить связь
        Intent intent = new Intent(this, Main2Activity.class);

        // Добавляем данные
        intent.putExtra("Hour", hour);
        intent.putExtra("Minute", minute);

        // запускаем новое Activity и асинхронно ждём его выполнения (с помощью переопределенного метода onActivityResult)
        startActivityForResult(intent, 1);
    }

    // Событие при закрытии ActivityForResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Если пришли какие-то данные
        if (data != null) {

            // Присваиваем полученные данные полям класса
            hour = data.getIntExtra("Hour",00);
            minute = data.getIntExtra("Minute",00);

            // Обновляем текстовое поле со значением времени
            textView.setText("Часы: " + hour + " Минуты: " + minute);
        }
    }
}

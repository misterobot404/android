package com.knastu.lab4;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

public class Main2Activity extends Activity {

    private int hour, minute;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Устанавливаем время полям класса из полученных значений
        hour = getIntent().getIntExtra("Hour", 00);
        minute = getIntent().getIntExtra("Minute", 00);

        // Описываем событие выбора времени у виджета TimePickerDialog
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            // Сработает при нажатии на кнопку "Подтвердить"
            @Override
            public void onTimeSet(TimePicker view, int t_hourOfDay, int t_minute) {

                // t_hourOfDay - Выбранные часы, t_minute - Выбранные минуты
                hour = t_hourOfDay;
                minute = t_minute;

                // Всплывающее сообщение
                (Toast.makeText(getBaseContext(), "Часы: " + hour + " Минуты: " + minute, Toast.LENGTH_LONG)).show();

                // Возвращаем данные из Activity
                Intent intent = new Intent();
                intent.putExtra("Hour", hour);
                intent.putExtra("Minute", minute);
                setResult(RESULT_OK, intent);
                finish();
            }
        };

        /*
        Создаем виджет выбора времени
        * timeSetListener - событие нажатия на кнопу "Подтвердить"
        * hour и minute - начальные значения времени
        */
        TimePickerDialog tpd = new TimePickerDialog(this, timeSetListener, hour, minute, true);

        // Устанавливаем слушатель события на нажатие кнопки "Вернуться"
        tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "Вернуться", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        // Устанавливаем слушатель события на нажатие кнопки "Вернуться"
        tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, "Подтердить", tpd);

        tpd.setCancelable(false);
        tpd.show();
    }
}

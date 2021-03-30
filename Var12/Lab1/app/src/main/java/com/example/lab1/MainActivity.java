/*На Activity – одна кнопка. Если на неё нажать, она отскакивает в сторону в произвольном направлении (но не на границу экрана).*/

package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Определяем объект Runnable
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        // Получаем случайное XY расстояние отскока кнопки
                        int distanceX = (int) (-300 + Math.random() * 600);
                        int distanceY = (int) (-300 + Math.random() * 600);

                        // Провереряем выход за пределы экрана
                        int allowX = getWindowManager().getDefaultDisplay().getWidth();
                        int allowY = getWindowManager().getDefaultDisplay().getHeight();
                        float expectedPositionX = button.getX() + distanceX;
                        float expectedPositionY = button.getY() + distanceY;

                        if (expectedPositionX <= 0 || expectedPositionX >= allowX || expectedPositionY <= 0 || expectedPositionY >= allowY) return;

                        // Перемещаем кнопку 100 тиков, пауза между тиками 10 мс (суммарное время отскока 1с)
                        for (int i = 0; i < 100; i++) {

                            button.setX(button.getX() + distanceX / 100);
                            button.setY(button.getY() + distanceY / 100);

                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                // Определяем объект Thread - новый поток
                Thread thread = new Thread(runnable);
                // Запускаем поток
                thread.start();
            }
        });
    }
}
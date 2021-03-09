package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button[] buttons = new Button[11];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons[0] = (Button)findViewById(R.id.button3);
        buttons[1] = (Button)findViewById(R.id.button4);
        buttons[2] = (Button)findViewById(R.id.button5);
        buttons[3] = (Button)findViewById(R.id.button6);
        buttons[4] = (Button)findViewById(R.id.button7);
        buttons[5] = (Button)findViewById(R.id.button8);
        buttons[6] = (Button)findViewById(R.id.button8);
        buttons[7] = (Button)findViewById(R.id.button9);
        buttons[8] = (Button)findViewById(R.id.button10);
        buttons[9] = (Button)findViewById(R.id.button11);
        buttons[10] = (Button)findViewById(R.id.button12);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Определяем объект Runnable
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            for (int i = 0; i < 11; i++) {
                                if (i != 0) buttons[i - 1].setBackgroundColor(Color.GRAY);
                                buttons[i].setBackgroundColor(Color.YELLOW);
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            // после полного прохода закрашиваем в серый цвет последнюю кнопку
                            buttons[10].setBackgroundColor(Color.GRAY);
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
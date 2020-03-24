package com.example.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {
    // Создаем обьект ImageView для более быстрого обращения в будущем
    ImageView imgView;
    // Ключ для кодового замка
    final String key = "169AF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализируем обьект ImageView
        imgView = (ImageView) findViewById(R.id.imageView);
    }

    // Нажатие на кнопку подтверждения пароля
    public void button_17_onClick(View view) {
        // Если пароль совпадает с ключём, меняем цвет картинки на залёный
        if (key.equals(getEnteredKey())) {
            imgView.setColorFilter(Color.argb(255, 0, 255, 0));
        }
        // Иначе, меняем цвет картинки на красный
        else imgView.setColorFilter(Color.argb(255, 255, 0, 0));
    }

    // Получить пароль введённый от пользователя
    public String getEnteredKey() {
        ToggleButton btn;
        // Ключ полученный от пользователя
        String entered_key = "";

        // С актитивных кнопок получаем значения и добовляем их к строке entered_key
        for (int i = 1; i <= 16; i++) {
            btn = (ToggleButton) findViewById(getBaseContext().getResources().getIdentifier
                    ("button" + i, "id", getBaseContext().getPackageName()));
            if ((btn.isChecked())) entered_key += btn.getText();
        }

        return entered_key;
    }
}



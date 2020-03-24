package com.knastu.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализируем обьект TextView
        textView = (TextView) findViewById(R.id.textView);
    }

    // Перезагрузка метода создания меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Добавление пользовательского меню
        getMenuInflater().inflate(R.menu.main, menu);

        // FIX Отображение значков в выпадающем меню
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    // Перезагрузка метода выбора пунка меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // Логика радиокнопок
            case R.id.item4:
            case R.id.item5:
                item.setChecked(!item.isChecked());
                textView.setText(item.getTitle() + ". Текущее состояние радиокнопки: " + item.isChecked());
                break;

            // Логика флажков
            case R.id.item6:
            case R.id.item7:
                item.setChecked(true);
                textView.setText(item.getTitle() + ". Текущее состояние флажка: " + item.isChecked());
                break;

            // Логика всех остальных кнопок
            default:
                textView.setText(item.getTitle());
        }
        return false;
    }
}

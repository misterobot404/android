package com.example.lab3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    // Логика радиокнопок
                    case R.id.item4:
                    case R.id.item5:
                        item.setChecked(!item.isChecked());
                        Toast.makeText(getApplicationContext(), item.getTitle() + ". Текущее состояние радиокнопки: " + item.isChecked(),Toast.LENGTH_SHORT).show();
                        break;

                    // Логика флажков
                    case R.id.item6:
                    case R.id.item7:
                        item.setChecked(true);
                        Toast.makeText(getApplicationContext(), item.getTitle() + ". Текущее состояние флажка: " + item.isChecked(),Toast.LENGTH_SHORT).show();
                        break;

                    // Логика всех остальных кнопок
                    default:
                        Toast.makeText(getApplicationContext(), item.getTitle(),Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        popup.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                showPopup(v);
            }
        });
    }

    // Перезагрузка метода создания меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Добавление пользовательского меню
        getMenuInflater().inflate(R.menu.main, menu);
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
                Toast.makeText(getApplicationContext(), item.getTitle() + ". Текущее состояние радиокнопки: " + item.isChecked(),Toast.LENGTH_SHORT).show();
                break;

            // Логика флажков
            case R.id.item6:
            case R.id.item7:
                item.setChecked(true);
                Toast.makeText(getApplicationContext(), item.getTitle() + ". Текущее состояние флажка: " + item.isChecked(),Toast.LENGTH_SHORT).show();
                break;

            // Логика всех остальных кнопок
            default:
                Toast.makeText(getApplicationContext(), item.getTitle(),Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}

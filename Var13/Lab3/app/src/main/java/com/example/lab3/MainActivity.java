package com.example.lab3;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(v -> showPopup(v));
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());
        popup.setForceShowIcon(true);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

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
        });

        popup.show();
    }
}

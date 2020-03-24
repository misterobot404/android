package com.knastu.lab3;

import androidx.appcompat.app.AppCompatActivity;

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

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                textView.setText("Поиск");
                break;
            case R.id.item2:
                textView.setText("Поделиться");
                break;
            case R.id.item3:
                textView.setText("Отправить");
                break;
            default:
                textView.setText(item.getTitle());
        }
    }
}

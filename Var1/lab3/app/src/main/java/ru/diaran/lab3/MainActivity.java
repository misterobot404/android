package ru.diaran.lab3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();

        TextView infoTextView = (TextView) findViewById(R.id.textView);

        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_settings:
                infoTextView.setText("Вы выбрали настройки!");
                return true;
            case R.id.action_text_menu_2:
                infoTextView.setText("Вы выбрали 2 опцию!");
                return true;
            case R.id.action_text_menu_3:
                infoTextView.setText("Вы выбрали 3 опцию!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));
        // активируем иконку как кнопку домой
        bar.setHomeButtonEnabled (true);
        bar.setDisplayShowHomeEnabled (true);
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME |
                ActionBar.DISPLAY_SHOW_TITLE);
        bar.setDisplayUseLogoEnabled(true);
        bar.setIcon(R.drawable.ic_action_name);
        bar.setLogo(R.drawable.ic_action_name);

    }
}

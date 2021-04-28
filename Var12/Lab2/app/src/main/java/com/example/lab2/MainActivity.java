package com.example.lab2;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Chronometer mChronometer;
    Random rand;
    SeekBar seekBar1, seekBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rand = new Random();
        mChronometer = findViewById(R.id.chronometer);
        seekBar1 = (SeekBar)findViewById(R.id.seekBar);
        seekBar1.setOnSeekBarChangeListener(seekBarChangeListener1);
        seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
        seekBar2.setOnSeekBarChangeListener(seekBarChangeListener2);
    }

    public void onStartClick(View view) {
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
    }

    public void onStopClick(View view) {
        mChronometer.stop();
    }

    public void onResetClick(View view) {
        mChronometer.setBase(SystemClock.elapsedRealtime());
    }

    // Обработчик изменения состояния SeekBar цвета фона
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener1 = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateBackground();
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };

    // Обработчик изменения состояния SeekBar цвета шрифта
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener2 = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateTextColor();
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };

    // Изменение цвета фона
    private void updateBackground() {
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        // меняем цвет фона через формат RGB
        mChronometer.setBackgroundColor(0xff000000 + r * 0x10000 + g * 0x100 + b);
    }

    // Изменение цвета текста
    private void updateTextColor() {
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        // меняем цвет фона через формат RGB
        mChronometer.setTextColor(0xff000000 + r * 0x10000 + g * 0x100 + b);
    }
}

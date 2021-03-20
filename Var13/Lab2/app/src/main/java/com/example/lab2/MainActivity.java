package com.example.lab2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    // Текущий введенный код
    String code[] = {"0","0","0","0"};
    // Правильный код
    String trueCode = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY );

        // Получаем NumberPickers
        NumberPicker number_picker1 = findViewById(R.id.numberPicker1);
        NumberPicker number_picker2 = findViewById(R.id.numberPicker2);
        NumberPicker number_picker3 = findViewById(R.id.numberPicker3);
        NumberPicker number_picker4 = findViewById(R.id.numberPicker4);

        String[] values = new String[] {"0","1","2","3","4","5","6","7","8","9"};

        // Устанавливаем свойства NumberPickers
        number_picker1.setMinValue(0);
        number_picker1.setMaxValue(9);
        number_picker1.setDisplayedValues(values);
        number_picker2.setMinValue(0);
        number_picker2.setMaxValue(9);
        number_picker2.setDisplayedValues(values);
        number_picker3.setMinValue(0);
        number_picker3.setMaxValue(9);
        number_picker3.setDisplayedValues(values);
        number_picker4.setMinValue(0);
        number_picker4.setMaxValue(9);
        number_picker4.setDisplayedValues(values);

        // Устанавливаем обработчики NumberPickers
        number_picker1.setOnValueChangedListener((picker, oldVal, newVal) ->
                {
                    code[0] = String.valueOf(newVal);
                    showCode();
                }
        );
        number_picker2.setOnValueChangedListener((picker, oldVal, newVal) ->
                {
                    code[1] = String.valueOf(newVal);
                    showCode();
                }
        );
        number_picker3.setOnValueChangedListener((picker, oldVal, newVal) ->
                {
                    code[2] = String.valueOf(newVal);
                    showCode();
                }
        );
        number_picker4.setOnValueChangedListener((picker, oldVal, newVal) ->
                {
                    code[3] = String.valueOf(newVal);
                    showCode();
                }
        );

        //

    }

    public void showCode() {
        TextView textView = findViewById(R.id.textView);
        textView.setText(code[0] + code[1] + code[2] + code[3]);
    }

    public void open(View view) {
        ImageView imageView = findViewById(R.id.imageView);
        if (trueCode.equals(code[0] + code[1] + code[2] + code[3])) {
            imageView.setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY );
        }
        else {
            imageView.setColorFilter( Color.RED, PorterDuff.Mode.MULTIPLY );
        }
    }
}
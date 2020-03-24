package com.knastu.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private boolean[] stateCheckBox;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        stateCheckBox = new boolean[]{false,true,false};

        textView.setText("Лук: " + stateCheckBox[0] + "\nСалат: " + stateCheckBox[1] + "\nПомидор: " + stateCheckBox[2]);
    }

    public void onClickForward(View view) {
        // Говорим между какими Activity будет происходить связь
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("stateCheckBox", stateCheckBox);
        // показываем новое Activity
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            stateCheckBox = data.getBooleanArrayExtra("stateCheckBox");
            textView.setText("Лук: " + stateCheckBox[0] + "\nСалат: " + stateCheckBox[1] + "\nПомидор: " + stateCheckBox[2]);
        }
    }
}

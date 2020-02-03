package ru.diaran.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
    }

    private Button button1, button2, button3, button4;

    public void onClickButton1(View view) {
        button4.setText(R.string.button1);
        button3.setText(R.string.button2);
        button2.setText(R.string.button3);
        button1.setText(R.string.button4);
    }

    public void onClickButton2(View view) {
        button4.setText(R.string.button2);
        button3.setText(R.string.button3);
        button2.setText(R.string.button4);
        button1.setText(R.string.button1);
    }

    public void onClickButton3(View view) {
        button4.setText(R.string.button3);
        button3.setText(R.string.button4);
        button2.setText(R.string.button1);
        button1.setText(R.string.button2);
    }

    public void onClickButton4(View view) {
        button4.setText(R.string.button4);
        button3.setText(R.string.button1);
        button2.setText(R.string.button2);
        button1.setText(R.string.button3);
    }
}

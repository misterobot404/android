package com.example.lab001;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mTView;

    Random rand = new Random();
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTView = (TextView)findViewById(R.id.textView);
    }

    @TargetApi(Build.VERSION_CODES.O)

    public void onClick(View v) {
    switch (v.getId())
    {
        case R.id.b1:
        mTView.setTextColor(Color.rgb(r,g,b));
        break;

        case R.id.b2:
            mTView.setBackgroundColor(Color.YELLOW);
            break;

        case R.id.b3:
            mTView.setTextSize(150);
            break;
    }
    }
}

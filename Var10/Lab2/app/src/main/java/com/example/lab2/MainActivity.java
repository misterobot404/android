package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TextSwitcher.ViewFactory {

    static TextSwitcher mTextSwitcherMIN, mTextSwitcherSEC;
    static int SEC = 0, MIN = 0;
    static boolean run = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mTextSwitcherMIN = (TextSwitcher) findViewById(R.id.min);
        mTextSwitcherMIN.setFactory(this);
        mTextSwitcherSEC = (TextSwitcher) findViewById(R.id.sec);
        mTextSwitcherSEC.setFactory(this);

        Animation inAnimation = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        Animation outAnimation = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);
        mTextSwitcherMIN.setInAnimation(inAnimation);
        mTextSwitcherMIN.setOutAnimation(outAnimation);
        mTextSwitcherSEC.setInAnimation(inAnimation);
        mTextSwitcherSEC.setOutAnimation(outAnimation);

        mTextSwitcherMIN.setText(String.valueOf(MIN));
        mTextSwitcherSEC.setText(String.valueOf(SEC));

        TextSwitcher mTextSwitcher = (TextSwitcher) findViewById(R.id.separator);
        mTextSwitcher.setFactory(this);
        mTextSwitcher.setText(":");

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (run) {

                        try {
                            Thread.sleep(1000);
                            SEC++;
                            if (SEC % 60 == 0) {
                                SEC = 0;
                                MIN++;

                                if (MIN % 60 == 0) MIN = 0;
                            }
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    mTextSwitcherMIN.setText(String.valueOf(MIN));
                                    mTextSwitcherSEC.setText(String.valueOf(SEC));
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public void OnClick(View view) {
        run = !run;
    }

    public void OnClickReset(View view) {
        MIN = 0;
        SEC = 0;
    }

    @Override
    public View makeView() {
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
        textView.setTextSize(70);
        textView.setTextColor(Color.RED);
        return textView;
    }
}

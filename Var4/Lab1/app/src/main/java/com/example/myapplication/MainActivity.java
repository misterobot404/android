package com.example.myapplication;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;
    Display display;
    Point size;
    boolean up;
    int startButtonWidth;
    int startButtonHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button1);
        size = new Point();


//        ViewGroup.LayoutParams params = btn.getLayoutParams();
//        params.width = ViewGroup.LayoutParams.WRAP_CONTENT; // или в пикселях

        up = true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        startButtonWidth = btn.getWidth();
        startButtonHeight = btn.getHeight();
    }

    public void button_onClick(View view) {

        display = getWindowManager().getDefaultDisplay();
        display.getRealSize(size);

        // ********
        size.x = 1080;
        size.y = 1600;
        // ********

        int btn_width = btn.getWidth();
        int btn_height = btn.getHeight();

        if (up == true) {
            if (btn_width < size.x - 60) btn.setWidth(btn_width + 60);
            if (btn_height < size.y - 60) btn.setHeight(btn_height + 60);

            if(!(btn_width < size.x-60) && !(btn_height <= size.y-60)) up = false;
        } else {
            if (btn_width-60 >= startButtonWidth) btn.setWidth(btn_width - 60);
            if (btn_height-60 >= startButtonHeight) btn.setHeight(btn_height - 60);

            if(!(btn_height-60 > startButtonHeight) && !(btn_width-60 > startButtonWidth)) up = true;
        }
    }
}

//    Var2

//    Button btn1;
//    Button btn2;
//    int temp_width;
//    int temp_height;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        btn1 = (Button) findViewById(R.id.button1);
//        btn2 = (Button) findViewById(R.id.button2);
//        temp_width = 0;
//        temp_height = 0;
//    }
//
//    public void button_onClick() {
//        temp_width = btn1.getWidth();
//        temp_height = btn1.getHeight();
//
//        btn1.setWidth(btn2.getWidth());
//        btn1.setHeight(btn2.getHeight());
//
//        btn2.setWidth(this.temp_width);
//        btn2.setHeight(temp_height);
//    }
//}

//    <?xml version="1.0" encoding="utf-8"?>
//<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
//        xmlns:app="http://schemas.android.com/apk/res-auto"
//        xmlns:tools="http://schemas.android.com/tools"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:animateLayoutChanges="false"
//        tools:context=".MainActivity">
//
//<Button
//        android:id="@+id/button1"
//                android:layout_width="wrap_content"
//                android:layout_height="wrap_content"
//                android:onClick="button_onClick"
//                android:text="Button1"
//                app:layout_constraintBottom_toTopOf="@+id/button2"
//                app:layout_constraintEnd_toEndOf="parent"
//                app:layout_constraintHorizontal_bias="0.5"
//                app:layout_constraintStart_toStartOf="parent"
//                app:layout_constraintTop_toTopOf="parent" />
//
//<Button
//        android:id="@+id/button2"
//                android:layout_width="0dp"
//                android:layout_height="0dp"
//                android:onClick="button_onClick"
//                android:text="Button2"
//                app:layout_constraintBottom_toBottomOf="parent"
//                app:layout_constraintEnd_toEndOf="parent"
//                app:layout_constraintHorizontal_bias="0.5"
//                app:layout_constraintStart_toStartOf="parent"
//                app:layout_constraintTop_toBottomOf="@+id/button1" />
//</android.support.constraint.ConstraintLayout>

//LayoutParams lp = new LayoutParams(10,LayoutParams.wrap_content);
//        View_instance.setLayoutParams(lp);
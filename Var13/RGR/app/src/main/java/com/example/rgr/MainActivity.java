package com.example.rgr;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements View.OnTouchListener {

    ImageView mImageView;
    ViewGroup mMoveLayout;
    int mX;
    int mY;
    // координаты X и Y от центра
    double x = 0;
    double y = 0;
    EditText radianInput, distInput, grInput, xInput, yInput;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radianInput = (EditText) findViewById(R.id.editTextNumber);
        distInput = (EditText) findViewById(R.id.editTextNumber5);
        grInput = (EditText) findViewById(R.id.editTextNumber2);
        xInput = (EditText) findViewById(R.id.editTextNumber3);
        yInput = (EditText) findViewById(R.id.editTextNumber4);
        imageView = (ImageView) findViewById(R.id.ImageView);

        //Связываемся с нашими объектами, определяя изображение через заданный ViewGroup:
        mMoveLayout = (ViewGroup) findViewById(R.id.move);
        mImageView = (ImageView) mMoveLayout.findViewById(R.id.ImageView);

        //Создаем программно RelativeLayout с параметрами 100*100:
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(50, 50);

        //Применяем эти параметры к нашему изображению:
        mImageView.setLayoutParams(layoutParams);
        //И настраиваем ему слушателя (обработчик) прикосновений:
        mImageView.setOnTouchListener(this);
    }

    //Обрабатываем прикосновения к объекту:
    public boolean onTouch(View view, MotionEvent event) {

        //Определение координат через getRawX() и getRawY() дает
        //координаты по отношению к размерам экрана устройства:
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();

        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            //ACTION_DOWN срабатывает при прикосновении к экрану,
            //здесь определяется начальное стартовое положение объекта:
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                mX = X - lParams.leftMargin;
                mY = Y - lParams.topMargin;

                x = X - mMoveLayout.getWidth() / 2;
                y = -Y + mMoveLayout.getHeight() + mMoveLayout.getHeight() /2 + 70;
                reset();

                break;

            //ACTION_MOVE обрабатывает случившиеся в процессе прикосновения изменения, здесь
            //содержится информация о последней точке, где находится объект после окончания действия прикосновения ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams.leftMargin = X - mX;
                layoutParams.topMargin = Y - mY;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                view.setLayoutParams(layoutParams);

                x = X - mMoveLayout.getWidth() / 2;
                y = -Y + mMoveLayout.getHeight() + mMoveLayout.getHeight() /2 + 70;
                reset();

                break;
        }
        return true;
    }

    public void reset() {
        xInput.setText(String.valueOf(x));
        yInput.setText(String.valueOf(y));
        // Установка градусов
        double angle;
        if (x < 0) {
            angle = 270 - (Math.atan(y / -x) * 180 / Math.PI);
        } else {
            angle = 90 + (Math.atan(y / x) * 180 / Math.PI);
        }
        grInput.setText(String.valueOf(angle));
        // Установка радиан
        radianInput.setText(String.valueOf(Math.toRadians(angle)));
        // Установка дистанции
        distInput.setText(String.valueOf(Math.sqrt(x*x + y*y)));

    }

    public void setToCenter(View view) {
        x = 0;
        y = 0;
        mImageView.setX(mMoveLayout.getPivotX() - mImageView.getWidth() / 2);
        mImageView.setY(mMoveLayout.getPivotY() - mImageView.getHeight() / 2);
        reset();
    }
}
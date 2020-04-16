package com.knastu.lab7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class Board extends View {

    private Bitmap mBallBitmap;

    private int mPosX, mPosY; // координаты клубка
    private int mVelocityX = 10; // скорость
    private int mVelocityY = 10;
    private int mBallWidth; // ширина клубка

    public Board(Context context) {
        super(context);
        mBallBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boll);

        mPosX = mPosY = 0;

        // Получим ширину клубка
        mBallWidth = mBallBitmap.getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPosX += mVelocityX;
        mPosY += mVelocityY;

        // Если коснулись левой стенки, то меняем направление
        if (mPosX < 0) {
            mPosX = 0;
            mVelocityX = -mVelocityX;
        }

        // Если коснулись правой стенки
        if (mPosX + mBallWidth > canvas.getWidth()) {
            mPosX = canvas.getWidth() - mBallWidth;
            mVelocityX = -mVelocityX;
        }

        // Если коснулись верха, то меняем направление
        if (mPosY < 0) {
            mPosY = 0;
            mVelocityY = -mVelocityY;
        }

        // Если коснулись низа
        if (mPosY + mBallWidth > canvas.getHeight()) {
            mPosY = canvas.getHeight() - mBallWidth;
            mVelocityY = -mVelocityY;
        }

        canvas.drawBitmap(mBallBitmap, mPosX, mPosY, null);
        invalidate();
    }
}

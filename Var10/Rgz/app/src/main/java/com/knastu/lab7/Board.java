package com.knastu.lab7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;


public class Board extends View implements View.OnTouchListener {

    Bitmap unit;
    Integer unitSpeed = 14;
    Integer unitRotate = 0;
    float unitPosX = 0, unitPosY = 0;

    RectF rectGame, rectButtonUp, rectButtonDown, rectButtonLeft, rectButtonRight;
    Paint paint, paintButton, paintText, paintBorderGameBoard;

    public Board(Context context) {
        super(context);

        unit = BitmapFactory.decodeResource(getResources(), R.drawable.juchok);

        paint = new Paint();
        paint.setColor(Color.rgb(89, 114, 139));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        paintBorderGameBoard = new Paint();
        paintBorderGameBoard.setColor(Color.rgb(117, 144, 172));
        paintBorderGameBoard.setStrokeWidth(unit.getWidth());
        paintBorderGameBoard.setStyle(Paint.Style.STROKE);

        paintButton = new Paint();
        paintButton.setColor(Color.BLACK);
        paintButton.setStrokeWidth(14);
        paintButton.setStyle(Paint.Style.STROKE);

        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setAntiAlias(true);
        paintText.setTextSize(48);
        paintText.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //// DRAW LAYOUT
        canvas.drawRGB(117, 144, 172);

        //// DRAW GAME BOARD
        rectGame = new RectF(getWidth() / 2.5F, getHeight() / 12, getWidth() * 0.98F, getHeight() * 0.96F);
        canvas.drawRect(rectGame, paint);

        if (unitPosX == 0 && unitPosY == 0) {
            unitPosX = rectGame.centerX();
            unitPosY = rectGame.centerY();
        }

        //// DRAW UNIT
        RectF unit_rect = new RectF(unitPosX - unit.getWidth() / 2, unitPosY - unit.getHeight() / 2, unitPosX + unit.getWidth() / 2, unitPosY + unit.getHeight() / 2);

        canvas.save();
        canvas.rotate(unitRotate, unit_rect.centerX(), unit_rect.centerY());
        canvas.drawBitmap(unit, null, unit_rect, null);
        canvas.restore();

        //// DRAW GAME BOARD BORDER
        canvas.drawRect(rectGame, paintBorderGameBoard);

        //// DRAW BUTTONS
        rectButtonUp = new RectF(getWidth() / 7, 200, getWidth() / 3, 300);
        canvas.drawRect(rectButtonUp, paintButton);
        canvas.drawText("Вверх↑", rectButtonUp.centerX(), rectButtonUp.centerY() + 7, paintText);

        rectButtonDown = new RectF(getWidth() / 7, 200 + 120, getWidth() / 3, 300 + 120);
        canvas.drawRect(rectButtonDown, paintButton);
        canvas.drawText("Вниз↓", rectButtonDown.centerX(), rectButtonDown.centerY() + 7, paintText);

        rectButtonLeft = new RectF(getWidth() / 7, 200 + 240, getWidth() / 3, 300 + 240);
        canvas.drawRect(rectButtonLeft, paintButton);
        canvas.drawText("←Влево", rectButtonLeft.centerX(), rectButtonLeft.centerY() + 7, paintText);

        rectButtonRight = new RectF(getWidth() / 7, 200 + 360, getWidth() / 3, 300 + 360);
        canvas.drawRect(rectButtonRight, paintButton);
        canvas.drawText("Вправо→", rectButtonRight.centerX(), rectButtonRight.centerY() + 7, paintText);

        invalidate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (rectButtonUp.contains(event.getX(), event.getY())) {
            unitPosY -= unitSpeed;
            unitRotate = 0;

            if (unitPosY < rectGame.top)
                unitPosY = rectGame.bottom;

        } else if (rectButtonDown.contains(event.getX(), event.getY())) {
            unitPosY += unitSpeed;
            unitRotate = 180;

            if (unitPosY > rectGame.bottom)
                unitPosY = rectGame.top;

        } else if (rectButtonLeft.contains(event.getX(), event.getY())) {
            unitPosX -= unitSpeed;
            unitRotate = 270;

            if (unitPosX < rectGame.left)
                unitPosX = rectGame.right;

        } else if (rectButtonRight.contains(event.getX(), event.getY())) {
            unitPosX += unitSpeed;
            unitRotate = 90;

            if (unitPosX > rectGame.right)
                unitPosX = rectGame.left;
        }

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}
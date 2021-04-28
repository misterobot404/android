package com.example.rgr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public class Board extends View implements View.OnTouchListener {

    Bitmap unit, controller;
    float startX = 0, startY = 0, ammountX = 0, ammountY = 0;
    boolean controllerPressed = false;
    int controllerSpaceRadius = 220;
    Integer unitSpeed = 4;
    Region regionForController;
    Integer unitRotate = 0;
    float unitPosX = 0, unitPosY = 0;

    RectF rectGame;
    Paint paint, paintButton, paintText, paintBorderGameBoard;

    public Board(Context context) {
        super(context);

        unit = BitmapFactory.decodeResource(getResources(), R.drawable.juchok);

        Bitmap bitmapSource = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
        controller = Bitmap.createBitmap(bitmapSource);

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

        new Thread(() -> {
            while (true) {
                if (controllerPressed) {
                    if (ammountX < 0) {
                        unitPosX -= unitSpeed;
                        if (Math.abs(ammountX) > Math.abs(ammountY)) unitRotate = 270;

                        if (unitPosX < rectGame.left)
                            unitPosX = rectGame.right;

                    } else if (ammountX > 0) {
                        unitPosX += unitSpeed;
                        if (Math.abs(ammountX) > Math.abs(ammountY)) unitRotate = 90;

                        if (unitPosX > rectGame.right)
                            unitPosX = rectGame.left;
                    }

                    if (ammountY < 0) {
                        unitPosY -= unitSpeed;
                        if (Math.abs(ammountX) < Math.abs(ammountY)) unitRotate = 0;

                        if (unitPosY < rectGame.top)
                            unitPosY = rectGame.bottom;

                    } else if (ammountY > 0) {
                        unitPosY += unitSpeed;
                        if (Math.abs(ammountX) < Math.abs(ammountY)) unitRotate = 180;

                        if (unitPosY > rectGame.bottom)
                            unitPosY = rectGame.top;
                    }
                }
                try {
                    invalidate();
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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

        //// DRAW CONTROLLER
        canvas.drawCircle(getWidth() / 4, getPivotY(), controllerSpaceRadius, paintButton);
        regionForController = new Region(getWidth() / 4 - controllerSpaceRadius, (int) getPivotY() - controllerSpaceRadius, getWidth() / 4 + controllerSpaceRadius, (int) getPivotY() + controllerSpaceRadius);

        startX = getWidth() / 4 - controller.getWidth() / 2;
        startY = getPivotY() - controller.getHeight() / 2;
        canvas.drawBitmap(controller, startX + ammountX, startY + ammountY, paintButton);

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        // Признак попадания в область с джостиком
        if (regionForController.contains((int) x, (int) y)) {
            // Прикосновение к джостику
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                controllerPressed = true;
                return true;
            }
            // Событие перетаскивания
            else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                controllerPressed = true;
                ammountX = x - startX - controller.getWidth() / 2;
                ammountY = y - startY - controller.getHeight() / 2;
                return true;
            }
            // Отпустить джостик
            else if (event.getAction() == MotionEvent.ACTION_UP) {
                ammountX = 0;
                ammountY = 0;
                controllerPressed = false;
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}


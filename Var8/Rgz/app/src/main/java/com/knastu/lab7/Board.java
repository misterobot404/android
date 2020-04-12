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

public class Board extends View {

    Bitmap unit;
    float unitSpeedX = 0;
    float unitSpeedY = 0;
    double unitRotate = 0;

    float unitPosX = 0, unitPosY = 0;
    float verticalButtonPosY = 0, horizontalButtonPosX = 0;

    float sliderLength = 400, sliderButtonWight = 40, sliderButtonHeight = 60;
    boolean canHorizontalSliderMove = false, canVerticalSliderMove = false;

    float layoutMargin = 30;
    RectF rectGame, rectVerticalSlider, rectVerticalButton, rectHorizontalSlider, rectHorizontalButton;
    Paint paintGameBoard, paintSlider, paintBorderGameBoard;

    public Board(Context context) {
        super(context);

        unit = BitmapFactory.decodeResource(getResources(), R.drawable.juchok);

        paintGameBoard = new Paint();
        paintGameBoard.setColor(Color.rgb(89, 114, 139));
        paintGameBoard.setStyle(Paint.Style.FILL_AND_STROKE);

        paintBorderGameBoard = new Paint();
        paintBorderGameBoard.setColor(Color.rgb(117, 144, 172));
        paintBorderGameBoard.setStrokeWidth(unit.getWidth());
        paintBorderGameBoard.setStyle(Paint.Style.STROKE);

        paintSlider = new Paint();
        paintSlider.setColor(Color.BLACK);
        paintSlider.setStrokeWidth(14);
        paintSlider.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //// DRAW LAYOUT
        canvas.drawRGB(117, 144, 172);

        //// DRAW GAME BOARD
        rectGame = new RectF(unit.getWidth(), layoutMargin, getWidth() - layoutMargin, getHeight() - layoutMargin - sliderButtonHeight);
        canvas.drawRect(rectGame, paintGameBoard);

        if (unitPosX == 0 && unitPosY == 0) {
            unitPosX = rectGame.centerX();
            unitPosY = rectGame.centerY();
        }

        //// DRAW UNIT

        // Rotate unit
        unitRotate = (float) Math.toDegrees(Math.atan2(unitSpeedY, unitSpeedX));

        // Move unit
        unitPosX += unitSpeedX;
        unitPosY += unitSpeedY;
        if (unitPosY < rectGame.top)
            unitPosY = rectGame.bottom;
        if (unitPosY > rectGame.bottom)
            unitPosY = rectGame.top;
        if (unitPosX < rectGame.left)
            unitPosX = rectGame.right;
        if (unitPosX > rectGame.right)
            unitPosX = rectGame.left;

        RectF unit_rect = new RectF(unitPosX - unit.getWidth() / 2, unitPosY - unit.getHeight() / 2, unitPosX + unit.getWidth() / 2, unitPosY + unit.getHeight() / 2);

        canvas.save();
        canvas.rotate((float) unitRotate, unit_rect.centerX(), unit_rect.centerY());
        canvas.drawBitmap(unit, null, unit_rect, null);
        canvas.restore();

        //// DRAW GAME BOARD BORDER
        canvas.drawRect(rectGame, paintBorderGameBoard);

        //// DRAW VERTICAL SLIDER

        // draw line
        rectVerticalSlider = new RectF(rectGame.left - layoutMargin - sliderButtonWight, getHeight() / 2 - sliderLength / 2, rectGame.left - layoutMargin, getHeight() / 2 + sliderLength / 2);
        canvas.drawLine(rectVerticalSlider.centerX(), rectVerticalSlider.top, rectVerticalSlider.centerX(), rectVerticalSlider.bottom, paintSlider);

        // draw button
        rectVerticalButton = new RectF(rectVerticalSlider.left, rectVerticalSlider.centerY() - sliderButtonHeight / 2 + verticalButtonPosY, rectVerticalSlider.right, rectVerticalSlider.centerY() + sliderButtonHeight / 2 + verticalButtonPosY);
        canvas.drawRect(rectVerticalButton, paintSlider);

        //// DRAW HORIZONTAL SLIDER

        // draw line
        rectHorizontalSlider = new RectF(rectGame.centerX() - sliderLength / 2, getHeight() - layoutMargin - sliderButtonWight, rectGame.centerX() + sliderLength / 2, getHeight() - layoutMargin);
        canvas.drawLine(rectHorizontalSlider.centerX() - sliderLength / 2, rectHorizontalSlider.centerY(), rectHorizontalSlider.centerX() + sliderLength / 2, rectHorizontalSlider.centerY(), paintSlider);

        // draw button
        rectHorizontalButton = new RectF(rectHorizontalSlider.centerX() - sliderButtonHeight / 2 + horizontalButtonPosX, rectHorizontalSlider.top, rectHorizontalSlider.centerX() + sliderButtonHeight / 2 + horizontalButtonPosX, rectHorizontalSlider.bottom);
        canvas.drawRect(rectHorizontalButton, paintSlider);

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float positionX = event.getX();
        float positionY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {

                if (rectVerticalButton.contains(positionX, positionY)) {
                    canVerticalSliderMove = true;

                } else if (rectHorizontalButton.contains(positionX, positionY)) {
                    canHorizontalSliderMove = true;
                }
            }
            break;

            case MotionEvent.ACTION_MOVE: {
                if (canVerticalSliderMove) {
                    if (rectVerticalSlider.contains(positionX, positionY)) {
                        canVerticalSliderMove = true;
                        verticalButtonPosY = positionY - rectVerticalSlider.centerY();
                        unitSpeedY = verticalButtonPosY/10;
                        invalidate();
                    }
                }

                if (canHorizontalSliderMove) {
                    if (rectHorizontalSlider.contains(positionX, positionY)) {
                        canHorizontalSliderMove = true;
                        horizontalButtonPosX = positionX - rectHorizontalSlider.centerX();
                        unitSpeedX = horizontalButtonPosX/10;
                        invalidate();
                    }
                }
            }
            break;

            case MotionEvent.ACTION_UP: {
                canVerticalSliderMove = false;
                canHorizontalSliderMove = false;
            }
            break;
        }

        return true;
    }
}
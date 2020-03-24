package com.knastu.lab7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class Board extends View {
    final String message = "TOO HARD";
    final int marginTop = 100, marginRight = 200, marginBottom = 100, marginLeft = 50;

    Rect rect;
    Paint paint;
    int mPosX, mPosY, viewWidth, viewHeight;
    float alfa = 0;

    public Board(Context context) {
        super(context);

        paint = new Paint();
        rect = new Rect();

        paint.setTextSize(42);
        paint.isAntiAlias();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (viewWidth == 0 && viewHeight == 0) {

            viewWidth = canvas.getWidth();
            viewHeight = canvas.getHeight();

            mPosX = viewWidth / 2;
            mPosY = marginTop;
        }

        // Берём текст в прямоугольник, для поворота
        paint.getTextBounds(message, 0, message.length(), rect);

        // Перемещаем текст влево, если текст сверху
        if (mPosX > marginLeft && mPosY < marginTop+90) {
            mPosX--;
            // Если нужно, увеличиваем поворот
            if (mPosY - 90 < marginTop && mPosX > viewWidth-marginRight-90) {
                alfa--;
                mPosY--;
            } else if (mPosX - 90 < marginLeft) {
                alfa--;
                mPosY++;
            }
        }
        // Перемещаем текст вниз, если текст находиться слева
        else if (mPosY < viewHeight - marginBottom && mPosX < viewWidth / 2) {
            mPosY++;
            // Если нужно, увеличиваем поворот
            if (mPosY + 90 > viewHeight - marginBottom) {
                alfa--;
                mPosX++;
            }
        }
        // Перемещаем текст вправо, если текст находиться снизу
        else if (mPosX < viewWidth - marginRight && mPosY > viewHeight / 2) {
            mPosX++;
            // Если нужно, увеличиваем поворот
            if (mPosX + 90 > viewWidth - marginRight) {
                alfa--;
                mPosY--;
            }
        }
        // Перемещаем текст вверх, если текст находиться справа
        else if (mPosY < viewHeight - marginTop && mPosX > viewWidth / 2) {
            mPosY--;
        }

        //Отображение объекта
        canvas.rotate(alfa, mPosX + rect.exactCenterX(), mPosY + rect.exactCenterY());
        canvas.drawText(message, mPosX, mPosY, paint);
        invalidate();
    }
}
package com.knastu.lab7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Board extends View {

    private Paint paint1;
    boolean toogle = true;
    float angle = 5, radius = 100;
    int cx, cy;

    public Board(Context context) {
        super(context);
        // create the Paint and set its color
        paint1 = new Paint();
        paint1.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        cx = getHeight() / 2;
        cy = getWidth() / 2;

        if (toogle) {
            float x = (float) (cy + Math.sin(angle * Math.PI / 180F) * radius);
            float y = (float) (cx + Math.cos(angle * Math.PI / 180F) * radius);
            canvas.drawCircle(x, y, 30, paint1);

            if (angle == 360) {
                angle = 0;
                toogle = !toogle;
            } else {
                angle += 5;
            }
        } else {
            float x = (float) (cy - Math.sin(angle * Math.PI / 180F) * radius);
            float y = (float) (cx + radius*2 - Math.cos(angle * Math.PI / 180F) * radius);
            canvas.drawCircle(x, y, 30, paint1);

            if (angle == -360) {
                angle = 0;
                toogle = !toogle;
            } else {
                angle -= 5;
            }
        }
        invalidate();
    }
}
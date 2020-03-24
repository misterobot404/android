package com.knastu.lab7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import java.util.Calendar;

public class Board extends View {

    //  Side
    Bitmap side;
    // Clock
    Bitmap clock;
    RectF clock_rect;
    // Stopwatch
    Bitmap stopwatch;
    RectF stopwatch_rect;

    // Hour hand
    Paint hourHand_paint = new Paint();
    float hourHand_Angle;
    // Minute hand
    Paint minuteHand_paint = new Paint();
    float minuteHand_Angle;
    // Seconds hand
    Paint secondsHand_paint = new Paint();
    float secondsHand_Angle;

    public Board(Context context) {
        super(context);

        side = BitmapFactory.decodeResource(getResources(), R.drawable.aura_sunny);
        clock = BitmapFactory.decodeResource(getResources(), R.drawable.clock);
        stopwatch = BitmapFactory.decodeResource(getResources(), R.drawable.stopwatch);

        hourHand_paint.setStyle(Paint.Style.FILL_AND_STROKE);
        hourHand_paint.setStrokeWidth(8);
        hourHand_paint.setAntiAlias(true);

        minuteHand_paint.setStyle(Paint.Style.FILL_AND_STROKE);
        minuteHand_paint.setStrokeWidth(6);
        minuteHand_paint.setAntiAlias(true);

        secondsHand_paint.setStyle(Paint.Style.FILL_AND_STROKE);
        secondsHand_paint.setStrokeWidth(4);
        secondsHand_paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //*** Determine angles ***//
        Calendar currentTime = Calendar.getInstance();
        int totalSecondsToday = currentTime.get(Calendar.HOUR_OF_DAY) * 3600  + currentTime.get(Calendar.MINUTE) * 60 + currentTime.get(Calendar.SECOND);
        secondsHand_Angle = totalSecondsToday % 60f * 6f;
        minuteHand_Angle = totalSecondsToday % 3600f / 60 * 6f;
        hourHand_Angle = totalSecondsToday / 3600f * 30f;

        // *** Set rect for bitmap*** //
        clock_rect = new RectF(0, (getHeight() * 1.25f - getWidth()) * 0.5f, getWidth(), (getHeight() * 1.25f + getWidth()) * 0.5f);
        stopwatch_rect = new RectF(getWidth() * 0.5f, (getHeight() * 0.2f - getWidth() * 0.25f) * 0.5f, getWidth(), (getHeight() * 0.2f + getWidth() * 0.75f) * 0.5f);

        // *** Draw side *** //
        canvas.drawBitmap(side, 0,0, null);

        // *** Draw clock *** //
        canvas.drawBitmap(clock, null, clock_rect, null);

        // *** Draw stopwatch *** //
        canvas.drawBitmap(stopwatch, null, stopwatch_rect,null);

        // *** Draw hours hand *** //
        canvas.save();
        Path hourHand_path = new Path();
        hourHand_path.lineTo(0, -clock_rect.top * 0.75f + 40);
        hourHand_path.lineTo(-10, -clock_rect.top * 0.75f + 40);
        hourHand_path.lineTo(0, -clock_rect.top * 0.75f);
        hourHand_path.lineTo(10, -clock_rect.top * 0.75f + 40);
        hourHand_path.lineTo(0, -clock_rect.top * 0.75f + 40);
        hourHand_path.close();
        hourHand_path.offset(clock_rect.centerX(), clock_rect.centerY());
        canvas.rotate(hourHand_Angle, clock_rect.centerX(),clock_rect.centerY());
        canvas.drawPath(hourHand_path, hourHand_paint);
        canvas.restore();

        // *** Draw minute hand *** //
        canvas.save();
        Path minuteHand_path = new Path();
        minuteHand_path.lineTo(0, -clock_rect.top * 0.9f + 40);
        minuteHand_path.lineTo(-10, -clock_rect.top * 0.9f + 40);
        minuteHand_path.lineTo(0, -clock_rect.top * 0.9f);
        minuteHand_path.lineTo(10, -clock_rect.top * 0.9f + 40);
        minuteHand_path.lineTo(0, -clock_rect.top * 0.9f + 40);
        minuteHand_path.close();
        minuteHand_path.offset(clock_rect.centerX(), clock_rect.centerY());
        canvas.rotate(minuteHand_Angle, clock_rect.centerX(),clock_rect.centerY());
        canvas.drawPath(minuteHand_path, minuteHand_paint);
        canvas.restore();

        // *** Draw seconds hand *** //
        canvas.save();
        Path secondsHand_path = new Path();
        secondsHand_path.lineTo(-5, 0);
        secondsHand_path.lineTo(-5, -5);
        secondsHand_path.lineTo(5, -5);
        secondsHand_path.lineTo(5, 0);
        secondsHand_path.lineTo(0, 0);
        secondsHand_path.lineTo(0, -stopwatch_rect.height() * 0.3f + 20);
        secondsHand_path.lineTo(-10, -stopwatch_rect.height() * 0.3f + 20);
        secondsHand_path.lineTo(0, -stopwatch_rect.height() * 0.3f);
        secondsHand_path.lineTo(10, -stopwatch_rect.height() * 0.3f + 20);
        secondsHand_path.lineTo(0, -stopwatch_rect.height() * 0.3f + 20);
        secondsHand_path.close();
        secondsHand_path.offset(stopwatch_rect.centerX(), stopwatch_rect.centerY());
        canvas.rotate(secondsHand_Angle,stopwatch_rect.centerX(),stopwatch_rect.centerY());
        canvas.drawPath(secondsHand_path, secondsHand_paint);
        canvas.restore();

        invalidate();
    }
}
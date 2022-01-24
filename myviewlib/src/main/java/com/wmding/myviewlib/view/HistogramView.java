package com.wmding.myviewlib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author 明月
 * @version 1.0
 * @date 1/24/22 4:09 PM
 * @description: 自定义View实现直方图坐标系
 */
public class HistogramView extends View {
    private Paint mPaint;
    private Path mPath;

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制坐标轴
        mPaint.reset();
        mPath.reset();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.moveTo(100, 100);
        mPath.rLineTo(0, 402);
        mPath.rLineTo(800, 0);
        canvas.drawPath(mPath, mPaint);
        //绘制文字
        mPaint.reset();
        mPaint.setTextSize(30);
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(Color.RED);
        canvas.drawText("0", 100, 550, mPaint);
        canvas.drawText("X", 900, 550, mPaint);
        canvas.drawText("Y", 80, 100, mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawText("Froyo", 160, 540, mPaint);
        canvas.drawText("CB", 280, 540, mPaint);
        canvas.drawText("ICS", 380, 540, mPaint);
        canvas.drawText("J", 480, 540, mPaint);
        canvas.drawText("KitKat", 560, 540, mPaint);
        canvas.drawText("L", 690, 540, mPaint);
        canvas.drawText("M", 790, 540, mPaint);

        //绘制直方图，柱形图是用较粗的直线来实现的
        mPaint.reset();
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(80);
        float[] lines3 = {
                200, 500, 200, 495,
                300, 500, 300, 480,
                400, 500, 400, 480,
                500, 500, 500, 300,
                600, 500, 600, 200,
                700, 500, 700, 150,
                800, 500, 800, 350,
        };
        canvas.drawLines(lines3, mPaint);
    }

}

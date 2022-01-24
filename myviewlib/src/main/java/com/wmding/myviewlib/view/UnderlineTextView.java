package com.wmding.myviewlib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * @author 明月
 * @version 1.0
 * @date 1/24/22 3:39 PM
 * @description: 集成 TextView 来实现自定义 View
 */
public class UnderlineTextView extends TextView {
    public UnderlineTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        int width = getWidth();
        int height = getBaseline();
        canvas.drawLine(0, height, width, height, paint);
    }
}

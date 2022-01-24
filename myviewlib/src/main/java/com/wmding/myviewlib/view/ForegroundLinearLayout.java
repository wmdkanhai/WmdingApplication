package com.wmding.myviewlib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author 明月
 * @version 1.0
 * @date 1/24/22 3:46 PM
 * @description:
 */
public class ForegroundLinearLayout extends RelativeLayout {
    public ForegroundLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.drawColor(Color.parseColor("#50FF0000"));
    }
}

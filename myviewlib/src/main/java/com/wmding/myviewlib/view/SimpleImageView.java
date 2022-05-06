package com.wmding.myviewlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wmding.myviewlib.R;

/**
 * @author wmding
 * @date 5/2/22 3:59 PM
 * @describe 自定义ImageView，并可以设置长宽
 */
public class SimpleImageView extends View {


    private Paint paint;

    private Drawable drawable;

    private int width;

    private int height;

    public SimpleImageView(Context context) {
        this(context, null);
    }

    public SimpleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initAttrs(attrs);

        initPaint();
    }

    // 初始 paint
    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {

            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleImageView);

            drawable = array.getDrawable(R.styleable.SimpleImageView_src);
            measureDrawable();
            array.recycle();
        }
    }

    private void measureDrawable() {
        if (drawable != null) {
            width = drawable.getIntrinsicWidth();
            height = drawable.getIntrinsicHeight();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (drawable == null) {
            return;
        }

        Bitmap bitmap = drawableToBitmap(drawable);
        canvas.drawBitmap(bitmap, getLeft(), getTop(), paint);

        // 绘制文字，注意方向
        String text = "beautiful girl";
        drawText(canvas, text, getLeft() + 50, getTop() + 50);

        drawText2(canvas, text, getLeft() + 100, getTop() - 100);
    }

    /**
     * 绘制文字
     *
     * @param canvas 画布
     * @param text   要显示的文字
     * @param left   左边距
     * @param top    右边距
     */
    private void drawText(Canvas canvas, String text, int left, int top) {
        paint.setColor(Color.RED);
        paint.setTextSize(30);
        canvas.drawText(text, left, top, paint);
    }


    private void drawText2(Canvas canvas, String text, int left, int top) {
        // 保存画布状态
        canvas.save();

        // 旋转90°
        canvas.rotate(90);

        // 绘制文字
        paint.setColor(Color.RED);
        paint.setTextSize(30);
        canvas.drawText(text, left, top, paint);

        // 恢复到原来的状态
        canvas.restore();
    }

    /**
     * 将drawable转成bitmap
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        //根据获得的drawable的透明度/不透明度，来设置Bitmap配置
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);

        // 在onDraw方法中调用canvas.drawBitmap()时需要构造一个绘制位图的画布，否则canvas绘制的位图将不显示，变成黑色
        Canvas canvas = new Canvas(bitmap);
        //为可绘制的图形指定一个边框，大小为图片大固定宽和高
        drawable.setBounds(0, 0, width, height);
        //调用此方法绘制，绘制setBounds（）方法指定大小的边界
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //测量View尺寸，将View的宽和高设置为图片的宽和高
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    private int measureWidth(int widthMeasureSpec) {
        // 获取宽度的模式和大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED: //表示可以将视图设置成任意大小，无限制。一般不常用，也很少见。
            case MeasureSpec.AT_MOST: //wrap_content 模式
                break;
            case MeasureSpec.EXACTLY: //matchParent 模式、或者具体数值（200dp）
                width = widthSize;
                break;
        }
        return width;
    }

    private int measureHeight(int heightMeasureSpec) {
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
        }
        return height;
    }
}

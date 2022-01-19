package com.wmding.commonlib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wmding.commonlib.utils.MyLog;

/**
 * @author wmding
 * @date 1/19/22
 * @describe 功能：
 * 1、加载本地图片
 * 2、加载网络图片
 * 3、压缩图片
 * 4、多图列表
 * 5、DistLruCache
 * <p>
 * Q：使用各种方式加载图片，占用内存是多少？待考虑
 */
public class ImageViewActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        initView();
    }

    private void initView() {
        imageView = findViewById(R.id.image);
    }


    /**
     * 加载本地图片
     */
    public void loadLocalImage(View view) {

        // 方式一：setImageResource设置
        // imageView.setImageResource(R.drawable.image1);


        // 方式二：setImageDrawable设置

        // 该方法已经过时
        // Drawable drawable = getResources().getDrawable(R.drawable.image1);

        // 推荐使用该方法构建 drawable
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.image1);
//        imageView.setImageDrawable(drawable);


        // 方式三：setImageBitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
        imageView.setImageBitmap(bitmap);

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        MyLog.info(String.format("bitmap width: %d,height: %d", width, height));

        getScreenData();
    }

    /**
     * 加载网络图片
     *
     * @param view
     */
    public void loadNetImage(View view) {
        String url = "https://picsum.photos/id/1003/500/500";
        Glide.with(this)
                .load(url)
                .into(imageView);
    }


    /**
     * 获取屏幕大小
     * https://www.jianshu.com/p/385ea4e8d2ba
     */
    private void getScreenData(){
        WindowManager mWindowManager  = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;//获取到的是px，像素，绝对像素，需要转化为dpi
        int height = metrics.heightPixels;
        MyLog.info(String.format("screen width: %d,height: %d", width, height));
    }
}
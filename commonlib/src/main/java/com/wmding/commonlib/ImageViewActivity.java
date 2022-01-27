package com.wmding.commonlib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wmding.commonlib.utils.BitmapUtil;
import com.wmding.commonlib.utils.ImageUtil;
import com.wmding.commonlib.utils.MemoryUtil;
import com.wmding.commonlib.utils.MyLog;
import com.wmding.commonlib.utils.ScreenUtil;

import java.util.HashMap;

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
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 图片实际大小：1000 * 1000
        MyLog.info(String.format("bitmap width: %d,height: %d", width, height));

        imageView.setImageBitmap(bitmap);

        // 获取屏幕信息
        HashMap screenData = ScreenUtil.getScreenData(this);
        int width1 = (int) screenData.get("width");
        int height1 = (int) screenData.get("height");
        MyLog.info(String.format("屏幕 width1: %d,height2: %d", width1, height1));

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
     * 根据控件大小加载图片
     *
     * @param view
     */
    public void loadImage(View view) {

        // 获取当前控件的大小
        int width1 = imageView.getWidth();
        int height1 = imageView.getHeight();
        MyLog.info(String.format("imageView width1: %d,height1: %d", width1, height1));

        // 获取缩放后的大小
        Bitmap bitmap1 = BitmapUtil.decodeSampledBitmapFromResource(getResources(), R.drawable.image1, width1, height1);
        imageView.setImageBitmap(bitmap1);
    }


    public void compressImage(View view) {

        String s = MemoryUtil.printMemInfo();
        MyLog.error(s);


        long startTime = System.currentTimeMillis();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
        MyLog.info("bitmap");
        byte[] bytes = BitmapUtil.bitmapToByte(bitmap);
        MyLog.info("压缩前：bytes: " + bytes.length);

        byte[] bytes1 = ImageUtil.compressPng(bytes, imageView.getWidth(), imageView.getHeight());
        MyLog.info("压缩后：bytes1: " + bytes1.length);

        Bitmap bitmap1 = BitmapUtil.byteToBitmap(bytes1);

        long endTime = System.currentTimeMillis();

        MyLog.info("时间花费（ms）：" + (endTime - startTime));

        imageView.setImageBitmap(bitmap1);

        MemoryUtil.printMemoryInfo(this);
    }
}
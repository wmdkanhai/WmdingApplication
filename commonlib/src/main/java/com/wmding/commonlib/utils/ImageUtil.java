package com.wmding.commonlib.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.io.ByteArrayOutputStream;

public class ImageUtil {

    private ImageUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static byte[] compressPng(byte[] image, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 读取大小不读取内容
        options.inJustDecodeBounds = true;
        // 设置图片每个像素占2字节，没有透明度
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        // options读取图片
        BitmapFactory.decodeByteArray(image, 0, image.length, options);

        double outWidth = options.outWidth;
        // 获取到当前图片宽高
        double outHeight = options.outHeight;
        int inSampleSize = 1;


        /*
        先计算原图片宽高比ratio=width/height，再计算限定的范围的宽高比比reqRatio，
        若reqRatio > ratio，则说明限定的范围更加细长，则以高为标准计算inSampleSize
        否则，则说明限定范围更加粗矮，则以宽为计算标准
         */
        double ratio = outWidth / outHeight;
        double reqRatio = reqHeight / reqWidth;
        if (reqRatio > ratio) {
            while (outHeight / inSampleSize > reqHeight) {
                inSampleSize *= 2;
            }
        } else {
            while (outWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2;
            }
        }

        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;

        if (1 == inSampleSize) {
            // inSampleSize == 1，就说明原图比要求的尺寸小或者相等，那么不用继续压缩，直接返回。
            return image;
        }
        /*
        否则的话，先将图片通过减少采样点的方式，以一个比限定范围稍大的尺寸读入内存，
        防止因为图片太大而OOM，以及太大的图片加载时间过长
        然后继续进行压缩的步骤
        */
        options.inSampleSize = inSampleSize / 2;
        // options读取图片
        Bitmap baseBitmap = BitmapFactory.decodeByteArray(image, 0, image.length, options);

        /*
        使用之前计算过的宽高比，
        若reqRatio > ratio，则说明限定的范围更加细长，则以高为标准计算压缩比
        否则，则说明限定范围更加粗矮，则以宽为计算标准
        */
        float compressRatio = 1;
        if (reqRatio > ratio) {
            compressRatio = reqHeight * 1.0f / baseBitmap.getHeight();
        } else {
            compressRatio = reqWidth * 1.0f / baseBitmap.getWidth();
        }

        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (baseBitmap.getWidth() * compressRatio),
                (int) (baseBitmap.getHeight() * compressRatio),
                baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);

        Matrix matrix = new Matrix();
        matrix.setScale(compressRatio, compressRatio);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawBitmap(baseBitmap, matrix, paint);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        afterBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
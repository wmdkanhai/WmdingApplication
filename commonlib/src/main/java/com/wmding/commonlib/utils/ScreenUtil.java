package com.wmding.commonlib.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.HashMap;

/**
 * @author 明月
 * @version 1.0
 * @date 1/20/22 3:43 PM
 * @description:
 */
public class ScreenUtil {
    /**
     * 获取屏幕大小
     * https://www.jianshu.com/p/385ea4e8d2ba
     */
    public static HashMap getScreenData(Context context) {
        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;//获取到的是px，像素，绝对像素，需要转化为dpi
        int height = metrics.heightPixels;
        MyLog.info(String.format("screen width: %d,height: %d，单位px", width, height));
        HashMap<String, Integer> screenDataMap = new HashMap<>();
        screenDataMap.put("width", width);
        screenDataMap.put("height", height);
        return screenDataMap;
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}

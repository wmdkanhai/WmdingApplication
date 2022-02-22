package com.wmding.commonlib.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wmding
 * @date 2/22/22 9:28 PM
 * @describe 活动管理器
 */
public class ActivityCollector {
    public static List<Activity> list = new ArrayList<>();

    /**
     * 添加 activity，在 activity 的 onCreate 方法中调用
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        list.add(activity);
    }

    /**
     * 移除 activity，在 activity 的 onDestroy 方法中调用
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        list.remove(activity);
    }


    /**
     * 关闭所有的 activity
     */
    public static void finishAll() {
        for (Activity activity : list) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}

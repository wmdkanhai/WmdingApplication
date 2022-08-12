package com.wmding.commonlib.inject;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * @author wmding
 * @date 7/31/22 11:01 AM
 * @describe 通过注解+反射，给Activity中的View进行findViewById
 */
public class InjectUtils {
    public static void injectView(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectView.class)) {
                InjectView injectView = field.getAnnotation(InjectView.class);
                int id = injectView.value();
                View view = activity.findViewById(id);
                field.setAccessible(true);
                try {
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

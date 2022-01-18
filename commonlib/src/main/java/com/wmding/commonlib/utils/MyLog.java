package com.wmding.commonlib.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.wmding.commonlib.BuildConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author wmding
 * @date 2020/5/26
 * @describe
 */
public class MyLog {
    private MyLog() {
        throw new IllegalStateException("Utility class");
    }

    private static final String LOG_TAG = "MyLog";
    private static String logPath = null;
    static Boolean writeLog = false;

    public static void init(Context context) {

        if (BuildConfig.DEBUG) {

            logPath = getFilePath(context.getApplicationContext());
        }
    }

    private static String getFilePath(Context context) {
        File file;
        String path = null;
        //如果外部储存可用
        if (!Environment.isExternalStorageRemovable()) {

            file = context.getExternalFilesDir(null);
        } else {

            file = context.getFilesDir();
        }

        if (file != null) {

            path = file.getPath();
        }

        return path;
    }

    private static void write2File(String msg) {

        if (null == logPath) {

            return;
        }

        String fileName = logPath + "/my_log.log";

        //如果父路径不存在
        File file = new File(logPath);
        if (!file.exists()) {
            file.mkdirs();//创建父路径
        }

        //FileOutputStream会自动调用底层的close()方法，不用关闭
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            //这里的第二个参数代表追加还是覆盖，true为追加，flase为覆盖
            fos = new FileOutputStream(fileName, true);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(msg + "\n");

        } catch (IOException e) {

            error("MyLog write2File error:" + e.getMessage());

        } finally {
            try {
                if (bw != null) {
                    bw.close();//关闭缓冲流
                }

                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                MyLog.error("MyLog write2File error:" + e.getMessage());
            }
        }

    }

    public static void error(String msg) {

        String message = getMessage(msg);
        Log.e(LOG_TAG, message);
        if (BuildConfig.DEBUG || writeLog) {

            write2File(message);
        }
    }

    public static void warn(String msg) {


        if (BuildConfig.DEBUG || writeLog) {

            String message = getMessage(msg);
            write2File(message);
            Log.w(LOG_TAG, message);
        }

    }

    public static void info(String msg) {

        if (BuildConfig.DEBUG || writeLog) {
            String message = getMessage(msg);
            write2File(message);
            Log.i(LOG_TAG, message);
        }
    }

    public static void debug(String msg) {

        if (BuildConfig.DEBUG || writeLog) {
            String message = getMessage(msg);
            write2File(message);
            Log.d(LOG_TAG, message);
        }
    }

    /**
     * 获取当前时间并格式化
     *
     * @return 当前时间
     */
    public static String getCurrentTime() {
        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS", Locale.getDefault());
        return sf.format(data);
    }

    private static String getMessage(String msg) {
        return String.format("[%s]---> %s", getCurrentTime(), msg);
    }
}


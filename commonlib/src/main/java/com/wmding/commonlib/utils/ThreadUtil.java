package com.wmding.commonlib.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 明月
 * @version 1.0
 * @date 2020/8/27 2:07 PM
 * @description: 线程工具类
 */
public class ThreadUtil {
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    private static Handler handler = new Handler(Looper.getMainLooper());

    /**
     * 在主线程运行
     *
     * @param runnable 回调
     */
    public static void runOnMainThread(final Runnable runnable) {
        handler.post(runnable);
    }

    /**
     * 构建一个线程池
     *
     * @return
     */
    public static ExecutorService getThreadPool() {

        //创建基本线程池
        return new ThreadPoolExecutor(
                NUMBER_OF_CORES,
                NUMBER_OF_CORES * 2,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                new LinkedBlockingQueue<Runnable>(),
                new NameThreadFactory()
        );
    }

    /**
     * 判断当前线程是否为主线程
     *
     * @return true or false
     */
    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }


    /**
     * 提供带有name的线程工厂
     */
    static class NameThreadFactory implements ThreadFactory {
        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "WmdingApplication-thread-" + mThreadNum.getAndIncrement());
            return t;
        }
    }
}

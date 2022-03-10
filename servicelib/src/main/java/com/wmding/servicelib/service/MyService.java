package com.wmding.servicelib.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.wmding.commonlib.utils.MyLog;

public class MyService extends Service {

    public DownloadBinder downloadBinder = new DownloadBinder();

    public class DownloadBinder extends Binder {
        public void startDownload() {
            MyLog.info("startDownload");

        }

        public int getProgress() {
            MyLog.info("getProgress");
            return 0;
        }

        public String getData(String name) {
            MyLog.info("getData");
            return name + ", hello";
        }
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return downloadBinder;
    }
}
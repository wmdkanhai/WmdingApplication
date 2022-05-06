package com.wmding.servicelib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.wmding.commonlib.utils.MyLog;
import com.wmding.servicelib.service.MyService;

public class ServiceTest1Activity extends AppCompatActivity {

    private MyService.DownloadBinder downloadBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test1);
    }

    private ServiceConnection connection = new ServiceConnection() {

        /**
         * 绑定成功时调用
         * @param name
         * @param service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLog.info("onServiceConnected");
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();

            String str = downloadBinder.getData("wmding");
            MyLog.info("downloadBinder.getData: " + str);
        }

        /**
         * 解绑时调用
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void bindService(View view) {
        Intent intent = new Intent(this, MyService.class);
        // 绑定服务
        boolean b = bindService(intent, connection, BIND_AUTO_CREATE);
        MyLog.info("绑定服务: " + b);
    }


    public void unBindService(View view) {
        unbindService(connection);
    }
}
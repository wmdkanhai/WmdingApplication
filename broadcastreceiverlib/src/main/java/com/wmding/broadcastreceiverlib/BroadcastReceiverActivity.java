package com.wmding.broadcastreceiverlib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

import com.wmding.commonlib.utils.MyLog;

public class BroadcastReceiverActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    // LocalBroadcastManager 为本地广播，只能在应用内使用，提供安全性，但是该类已经被废弃，如需使用需要添加依赖
    // implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        initData();
    }

    private void initData() {

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        // 设置注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.wmding.localBroadcastManager");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    /**
     * 初始化一个接收网络状态改变的广播
     * @param view
     */
    public void test1(View view) {
        intentFilter = new IntentFilter();
        // 当网络发生变化时，系统会发送出值为 "android.net.conn.CONNECTIVITY_CHANGE" 的广播。
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        // 注册广播
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    /**
     * 发生一个自定义的本地广播
     * @param view
     */
    public void test2(View view) {
        // 发送一个广播
        Intent intent = new Intent("com.wmding.localBroadcastManager");
        localBroadcastManager.sendBroadcast(intent);
    }

    /**
     * 创建网络状态改变广播，接收网络状态改变时的广播
     */
    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 获取系统管理类，该类主要负责网络管理
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            // 获取网络信息
            /**
             * wifi状态下：[type: WIFI[], state: CONNECTED/CONNECTED, reason: (unspecified), extra: (none), failover: false, available: true, roaming: false]
             * 数据流量状态下：[type: MOBILE[LTE], state: CONNECTED/CONNECTED, reason: connected, extra: ***, failover: false, available: true, roaming: false]
             */
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (null != activeNetworkInfo && activeNetworkInfo.isAvailable()) {
                MyLog.error("当前网络可用");
                MyLog.info(activeNetworkInfo.toString());
            } else {
                MyLog.error("当前网络不可用");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 取消注册
        unregisterReceiver(networkChangeReceiver);

        // 取消注册
        localBroadcastManager.unregisterReceiver(localReceiver);
    }


    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            MyLog.info("接收到本地广播");
        }
    }
}
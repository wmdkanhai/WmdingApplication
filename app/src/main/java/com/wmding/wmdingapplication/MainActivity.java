package com.wmding.wmdingapplication;

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

import com.wmding.animationlib.AnimationActivity;
import com.wmding.commonlib.CommonActivity;
import com.wmding.commonlib.utils.MyLog;
import com.wmding.myviewlib.MyViewActivity;
import com.wmding.networklib.NetworkActivity;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    // LocalBroadcastManager 为本地广播，只能在应用内使用，提供安全性，但是该类已经被废弃，如需使用需要添加依赖
    // implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        intentFilter = new IntentFilter();
        // 当网络发生变化时，系统会发送出值为 "android.net.conn.CONNECTIVITY_CHANGE" 的广播。
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        // 注册广播
        registerReceiver(networkChangeReceiver, intentFilter);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);


        // 设置注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.wmding.localBroadcastManager");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    public void test1(View view) {
        Intent intent = new Intent(this, CommonActivity.class);
        startActivity(intent);
    }

    /**
     * 自定义View
     *
     * @param view
     */
    public void test2(View view) {
        Intent intent = new Intent(this, MyViewActivity.class);
        startActivity(intent);
    }

    /**
     * 动画
     *
     * @param view
     */
    public void test3(View view) {
        Intent intent = new Intent(this, AnimationActivity.class);
        startActivity(intent);
    }

    /**
     * 网络
     *
     * @param view
     */
    public void test4(View view) {
        Intent intent = new Intent(this, NetworkActivity.class);
        startActivity(intent);
    }

    public void test5(View view) {
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
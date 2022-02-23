package com.wmding.broadcastreceiverlib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wmding.broadcastreceiverlib.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void btnForceOffline(View view) {
        // 发送一个广播
        Intent intent = new Intent("com.wmding.broadcastreceiver.FORCE_OFFLINE");
        sendBroadcast(intent);
    }
}
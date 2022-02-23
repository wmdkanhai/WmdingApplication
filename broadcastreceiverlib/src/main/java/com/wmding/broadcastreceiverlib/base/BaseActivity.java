package com.wmding.broadcastreceiverlib.base;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wmding.broadcastreceiverlib.ForceOfflineReceiver;
import com.wmding.commonlib.utils.ActivityCollector;

/**
 * @author wmding
 * @date 2/23/22 8:38 PM
 * @describe
 */
public class BaseActivity extends AppCompatActivity {
    private ForceOfflineReceiver forceOfflineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.wmding.broadcastreceiver.FORCE_OFFLINE");
        forceOfflineReceiver = new ForceOfflineReceiver();
        registerReceiver(forceOfflineReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != forceOfflineReceiver) {
            unregisterReceiver(forceOfflineReceiver);
            forceOfflineReceiver = null;
        }
    }
}

package com.wmding.commonlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wmding.commonlib.utils.MyLog;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        MyLog.info("SecondActivity onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyLog.info("SecondActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyLog.info("SecondActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        MyLog.info("SecondActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyLog.info("SecondActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyLog.info("SecondActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyLog.info("SecondActivity onDestroy");
    }

}
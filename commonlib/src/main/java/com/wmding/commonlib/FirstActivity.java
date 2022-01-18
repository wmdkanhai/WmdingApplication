package com.wmding.commonlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wmding.commonlib.utils.MyLog;

import java.time.Instant;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        MyLog.info("FirstActivity onCreate");
    }


    @Override
    protected void onStart() {
        super.onStart();
        MyLog.info("FirstActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyLog.info("FirstActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        MyLog.info("FirstActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyLog.info("FirstActivity onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        MyLog.info("FirstActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyLog.info("FirstActivity onDestroy");
    }

    public void btn1(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void btn2(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }
}
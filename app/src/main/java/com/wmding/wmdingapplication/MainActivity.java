package com.wmding.wmdingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wmding.animationlib.AnimationActivity;
import com.wmding.broadcastreceiverlib.BroadcastReceiverActivity;
import com.wmding.commonlib.CommonActivity;
import com.wmding.myviewlib.MyViewActivity;
import com.wmding.networklib.NetworkActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    /**
     * 广播
     *
     * @param view
     */
    public void test5(View view) {
        Intent intent = new Intent(this, BroadcastReceiverActivity.class);
        startActivity(intent);
    }
}
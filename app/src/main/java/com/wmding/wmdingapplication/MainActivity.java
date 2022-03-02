package com.wmding.wmdingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wmding.animationlib.AnimationActivity;
import com.wmding.broadcastreceiverlib.ReceiverMenuActivity;
import com.wmding.commonlib.CommonActivity;
import com.wmding.commonlib.utils.MyLog;
import com.wmding.contentresolverlib.ContentResolverActivity;
import com.wmding.medialib.MediaActivity;
import com.wmding.myviewlib.MyViewActivity;
import com.wmding.networklib.NetworkActivity;

import static com.wmding.commonlib.utils.AndroidUtil.printSystemInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    private void initData() {
        String s = printSystemInfo();
        MyLog.error(s);
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
        Intent intent = new Intent(this, ReceiverMenuActivity.class);
        startActivity(intent);
    }

    /**
     * 内容提供者
     * @param view
     */
    public void test6(View view) {
        Intent intent = new Intent(this, ContentResolverActivity.class);
        startActivity(intent);
    }

    /**
     * 多媒体相关
     * @param view
     */
    public void test7(View view) {
        Intent intent = new Intent(this, MediaActivity.class);
        startActivity(intent);
    }
}
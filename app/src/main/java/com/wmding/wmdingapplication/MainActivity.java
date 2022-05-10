package com.wmding.wmdingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.facebook.device.yearclass.YearClass;
import com.wmding.animationlib.AnimationActivity;
import com.wmding.broadcastreceiverlib.ReceiverMenuActivity;
import com.wmding.commonlib.CommonActivity;
import com.wmding.commonlib.utils.MemoryUtil;
import com.wmding.commonlib.utils.MyLog;
import com.wmding.contentresolverlib.ContentResolverActivity;
import com.wmding.fragmentlib.FrMainActivity;
import com.wmding.medialib.MediaActivity;
import com.wmding.mykotlin.FirstActivity;
import com.wmding.myviewlib.MyViewActivity;
import com.wmding.networklib.NetworkActivity;
import com.wmding.servicelib.ServiceMainActivity;

import static com.wmding.commonlib.utils.AndroidUtil.printSystemInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        String systemInfo = printSystemInfo();
        MyLog.info("系统信息: " + systemInfo);

        MemoryUtil.printMemoryInfo(this.getApplication());

        int year = YearClass.get(this.getApplication());
        MyLog.info("year: " + year);
        if (year >= YearClass.CLASS_2016) {
            // 配置较高的手机可以 开启复杂的动画 或 "重功能"。
            // 通常来说，从 2016年开始 的手机配置就比较好了，
            // 我们统一按照这个模板使用即可。


        } else {
            // 低端机用户可以 关闭复杂的动画 或 "重功能"、在系统资源不够时我们应该主动去做降级处理。

        }

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
     *
     * @param view
     */
    public void test6(View view) {
        Intent intent = new Intent(this, ContentResolverActivity.class);
        startActivity(intent);
    }

    /**
     * 多媒体相关
     *
     * @param view
     */
    public void test7(View view) {
        Intent intent = new Intent(this, MediaActivity.class);
        startActivity(intent);
    }

    public void test8(View view) {
        Intent intent = new Intent(this, ServiceMainActivity.class);
        startActivity(intent);
    }

    public void kotlinTest(View view) {
        Intent intent = new Intent(this, com.wmding.mykotlin.FirstActivity.class);
        startActivity(intent);
    }

    public void fragmentTest(View view) {
        Intent intent = new Intent(this, FrMainActivity.class);
        startActivity(intent);
    }
}
package com.wmding.commonlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author wmding
 * @date 1/18/22
 * @describe
 * 1、Activity声明周期相关内容
 * 2、Activity启动方式
 * 3、
 */
public class CommonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
    }

    /**
     * 声明周期测试
     *
     * @param view
     */
    public void tes1(View view) {
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }

    public void handler(View view) {

    }

    public void imageView(View view) {
    }
}
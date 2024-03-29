package com.wmding.commonlib;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wmding.commonlib.inject.InjectUtils;
import com.wmding.commonlib.inject.InjectView;

import butterknife.BindView;

/**
 * @author wmding
 * @date 1/18/22
 * @describe
 * 1、Activity声明周期相关内容，2、Activity启动方式
 * 2、handler
 * 3、imageView
 */
public class CommonActivity extends AppCompatActivity {

    @InjectView(R2.id.tv1)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        InjectUtils.injectView(this);
        tv.setText("hjahahah");
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
        Intent intent = new Intent(this, HandlerTestActivity.class);
        startActivity(intent);
    }

    public void imageView(View view) {
        Intent intent = new Intent(this, ImageViewActivity.class);
        startActivity(intent);
    }

    public void fileTest(View view) {
        Intent intent = new Intent(this, FileTestActivity.class);
        startActivity(intent);
    }

    public void notificationTest(View view) {
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }
}
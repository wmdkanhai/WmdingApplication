package com.wmding.animationlib;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.Circle;

/**
 * @author wmding
 * @date 1/23/22 6:05 PM
 * @describe 第三方动画库 SpinKit 的使用
 * 地址：https://github.com/ybq/Android-SpinKit
 */
public class SpinKitViewActivity extends AppCompatActivity {

    private SpinKitView spinKit;

    private Circle mCircleDrawable;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_kit_view);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCircleDrawable.start();
    }

    private void initView() {
        spinKit = findViewById(R.id.spin_kit);


        //TextView
        textView = (TextView) findViewById(R.id.text);
        mCircleDrawable = new Circle();
        mCircleDrawable.setBounds(0, 0, 100, 100);
        mCircleDrawable.setColor(Color.WHITE);
        textView.setCompoundDrawables(null, null, mCircleDrawable, null);
        textView.setBackgroundColor(R.color.teal_200);
    }

    public void spinKitViewTest(View view) {
        boolean shown = spinKit.isShown();

        if (shown) {
            spinKit.setVisibility(View.INVISIBLE);
        } else {
            spinKit.setVisibility(View.VISIBLE);
        }
    }

    public void textViewTest(View view) {

        boolean shown = textView.isShown();
        if (shown) {
            textView.setVisibility(View.INVISIBLE);
        } else {
            textView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCircleDrawable.stop();
        spinKit.clearAnimation();
    }
}
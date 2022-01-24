package com.wmding.myviewlib;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.wmding.myviewlib.view.CustomTitleView;

public class MyView1Activity extends AppCompatActivity {

    private CustomTitleView mCustomTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view1);

        mCustomTitleView = findViewById(R.id.customview_title);
        mCustomTitleView.setTittle("This is Title");
        mCustomTitleView.setBackText("返回");
        mCustomTitleView.setBackColor(Color.BLUE);
        mCustomTitleView.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
package com.wmding.animationlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
    }

    //属性动画
    public void propertyAnimation(View view) {
        Intent intent = new Intent(this, PropertyAnimationActivity.class);
        startActivity(intent);
    }

    //View动画
    public void viewAnimation(View view) {
        Intent intent = new Intent(this, ViewAnimationActivity.class);
        startActivity(intent);
    }

    //帧动画
    public void fragmentAnimation(View view) {
        Intent intent = new Intent(this, FrameAnimationActivity.class);
        startActivity(intent);
    }
}
package com.wmding.animationlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


/**
 * @author wmding
 * @date 1/22/22
 * @describe View动画
 * View动画可实现基本的动画：
 * 1、平移
 * 2、缩放
 * 3、旋转
 * 4、透明度
 * <p>
 * 实现方式有2种：
 * 1、使用XML的方式
 * 2、使用Java代码的方式
 */

public class ViewAnimationActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        initView();
    }

    private void initView() {
        imageView = findViewById(R.id.image);
    }

    /**
     * 平移动画
     *
     * @param view
     */
    public void translate(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        imageView.startAnimation(animation);
    }

    /**
     * 缩放动画
     *
     * @param view
     */
    public void scale(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        imageView.startAnimation(animation);
    }

    /**
     * 旋转动画
     *
     * @param view
     */
    public void rotate(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView.startAnimation(animation);
    }


    /**
     * 透明度动画
     *
     * @param view
     */
    public void alpha(View view) {

        // 使用XML的形式，通过加载XML进行设置动画
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
//        imageView.startAnimation(animation);

        // 使用Java代码的形式设置动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(3000);
        imageView.startAnimation(alphaAnimation);
    }

    /**
     * 组合动画
     *
     * @param view
     */
    public void combination(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.combination);
        imageView.startAnimation(animation);
    }
}
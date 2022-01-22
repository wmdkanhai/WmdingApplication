package com.wmding.animationlib;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wmding.commonlib.utils.MyLog;

/**
 * @author wmding
 * @date 1/20/22
 * @describe 属性动画记录
 *
 * 属性动画，动态的改变对象的属性达到动画的效果
 * 参考链接：https://juejin.cn/post/6844903465211133959#heading-4
 */
public class PropertyAnimationActivity extends AppCompatActivity {

    private ImageView imageView;

    private TextView mTvAnswer;
    private boolean isClosed = true;
    private ImageView mIvIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);

        initView();
        initData();
    }

    private void initView() {
        imageView = findViewById(R.id.imageView);

        LinearLayout llQuestion = (LinearLayout) findViewById(R.id.ll_expanded_question);
        llQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();
            }
        });
        mTvAnswer = (TextView) findViewById(R.id.tv_expanded_answer);
        mIvIndicator = (ImageView) findViewById(R.id.iv_expanded_indicator);
    }

    private void initData() {

    }

    public void btn(View view) {
        startJavaPropertyAnimator();
    }


    /**
     * ObjectAnimator
     */
    private void startJavaPropertyAnimator() {
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(imageView, "rotationY", 0f, 360f);
        rotationY.setDuration(2000);
        rotationY.start();

        // 动画的监听事件
        rotationY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                MyLog.info("onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                MyLog.info("onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                MyLog.info("onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                MyLog.info("onAnimationRepeat");
            }
        });
    }

    // 通过AnimatorSet创建动画集
    private void startJavaPropertyAnimatorSet() {
        Animator scaleXAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 1, 0.5f);
        scaleXAnimator.setDuration(2000);
        Animator scaleYAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 1, 0.5f);
        scaleYAnimator.setDuration(2000);
        Animator rotationXAnimator = ObjectAnimator.ofFloat(imageView, "rotationX", 0, 360);
        rotationXAnimator.setDuration(2000);
        Animator rotationYAnimator = ObjectAnimator.ofFloat(imageView, "rotationY", 0, 360);
        rotationYAnimator.setDuration(2000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimator)
                .with(scaleYAnimator)
                .before(rotationXAnimator)
                .after(rotationYAnimator);
        animatorSet.start();
    }


    public void valueAnimator(View view) {
        startValueAnimator();
    }

    private void startValueAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(300);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 动画更新过程中的动画值，可以根据动画值的变化来关联对象的属性，实现属性动画
                float value = (float) animation.getAnimatedValue();
                MyLog.info("ValueAnimator，动画值：" + value);
            }
        });
    }

    private void anim() {
        // 箭头向下，不显示；

        mTvAnswer.setVisibility(View.VISIBLE);

        // 指示器旋转
//        ValueAnimator valueAnimator1 = isClosed
//                ? ValueAnimator.ofFloat(180, 0)
//                : ValueAnimator.ofFloat(0, 180);

        // 修改指示器箭头顺时针旋转
        ValueAnimator valueAnimator1 = isClosed ? ValueAnimator.ofFloat(0, 180) : ValueAnimator.ofFloat(-180, 0);
        valueAnimator1.setDuration(500);
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mIvIndicator.setRotation(value);
            }
        });
        valueAnimator1.start();


        // 打开关闭操作
        final int answerHeight = mTvAnswer.getMeasuredHeight();
        ValueAnimator valueAnimator2 = isClosed
                ? ValueAnimator.ofInt(-answerHeight, 0)
                : ValueAnimator.ofInt(0, -answerHeight);

        valueAnimator2.setDuration(500);
        final ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mTvAnswer.getLayoutParams();
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                params.bottomMargin = value;
                mTvAnswer.setLayoutParams(params);
            }
        });
        valueAnimator2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isClosed = !isClosed;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator2.start();
    }
}
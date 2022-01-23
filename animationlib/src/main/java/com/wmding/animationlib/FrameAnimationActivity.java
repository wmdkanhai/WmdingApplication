package com.wmding.animationlib;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wmding.commonlib.utils.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author wmding
 * @date 1/22/22 9:20 PM
 * @describe 帧动画：顺序播放一组预先定义好的图片，常用在 loading 动画上
 */
public class FrameAnimationActivity extends AppCompatActivity {

    protected Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);

        initView();
    }

    private void initView() {
        progressDialog = new Dialog(this, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.dialog);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = progressDialog.findViewById(R.id.id_tv_loadingmsg);
        msg.setText("卖力加载中");
    }

    public void btnStart(View view) {
        progressDialog.show();

        ThreadUtil.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        });
    }
}
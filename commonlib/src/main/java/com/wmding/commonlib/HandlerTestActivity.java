package com.wmding.commonlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wmding.commonlib.utils.ThreadUtil;

import java.lang.ref.WeakReference;

public class HandlerTestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button btnGetData;
    private Button btnGetData2;
    private static final int UPDATE_TEXT_VIEW = 0;
    private Handler handler;
    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);

        initView();
        initData();
    }

    private void initView() {
        textView = findViewById(R.id.textView);
        textView.append("内容如下：\n");
        btnGetData = findViewById(R.id.btn_get_data);
        btnGetData.setOnClickListener(this);


        btnGetData2 = findViewById(R.id.btn_get_data2);
        btnGetData2.setOnClickListener(this);
    }

    private void initData() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == UPDATE_TEXT_VIEW) {
                    textView.append(msg.obj + "\n");
                }
            }
        };

        myHandler = new MyHandler(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnGetData.getId()) {
            // 有内存泄漏
            getData();
        } else if (v.getId() == btnGetData2.getId()) {
            // 无内存泄漏
            getData2();
        }
    }


    private void getData() {
        ThreadUtil.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                // 模拟发送请求到服务端，获取数据
                String response = "hello wmding, welcome!!!";

                // 使用Handler进行更新UI
                Message message = new Message();
                message.what = UPDATE_TEXT_VIEW;
                message.obj = response;
                handler.sendMessageDelayed(message, 20000);
            }
        });
    }


    private void getData2() {
        ThreadUtil.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                // 模拟发送请求到服务端，获取数据
                String response = "hello wmding, welcome!!!";

                // 使用Handler进行更新UI
                Message message = new Message();
                message.what = UPDATE_TEXT_VIEW;
                message.obj = response;
                myHandler.sendMessageDelayed(message, 20000);
            }
        });
    }


    public static class MyHandler extends Handler {
        private WeakReference<HandlerTestActivity> mReference;

        public MyHandler(HandlerTestActivity activity) {
            mReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            //获取弱引用Activity对象
            HandlerTestActivity activity = mReference.get();

            switch (msg.what) {
                case UPDATE_TEXT_VIEW:
                    activity.textView.append(msg.obj + "\n");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //移除handler所有消息
        if (myHandler != null) {
            myHandler.removeCallbacksAndMessages(null);
        }
    }
}
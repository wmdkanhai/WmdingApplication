package com.wmding.networklib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wmding.commonlib.utils.GsonUtil;
import com.wmding.commonlib.utils.MyLog;
import com.wmding.commonlib.utils.ThreadUtil;
import com.wmding.networklib.bean.Result;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkHttpTestActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_test);

        initView();
    }

    private void initView() {
        textView = findViewById(R.id.text_view);
    }

    public void btnGet(View view) {
        ThreadUtil.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                String url = "https://www.baidu.com";
                String data = getDataSync(url);
                // 在主线程中更新UI
                ThreadUtil.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(data);
                    }
                });
            }
        });
    }

    public void btnGet2(View view) {
        String url = "https://www.baidu.com";
        getDataAsync(url, new com.wmding.networklib.Callback() {
            @Override
            public void onResult(Result result) {
                ThreadUtil.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        if ("0".equals(result.getCode())) {
                            textView.setText(result.getMsg());
                        }
                    }
                });
            }
        });
    }


    // 同步get请求
    private String getDataSync(String url) {
        //创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        //创建Request 对象
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response;
        String string = null;
        try {
            //得到Response 对象
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                string = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    // 异步get请求，异步请求不需要开启子线程，enqueue方法会自动将网络请求部分放入子线程中执行。
    public void getDataAsync(String url, com.wmding.networklib.Callback callback) {
        //创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        //创建Request 对象
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Result result = new Result("-1", "error msg: " + e.getMessage());
                callback.onResult(result);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    MyLog.info("string: " + string);
                    Result result = new Result("0", string);
                    callback.onResult(result);
                } else {
                    Result result = new Result("-1", "error msg: " + response.code());
                    callback.onResult(result);
                }
            }
        });
    }

    /**
     * 构建发送请求的Request
     *
     * @param url     服务地址
     * @param jsonStr json格式的报文
     * @return Request
     */
    private Request buildPostRequest(String url, String jsonStr) {
        RequestBody body = RequestBody.Companion.create(jsonStr, MediaType.get("application/json; charset=utf-8"));
        //创建Request 对象
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return request;
    }

    public String postDataSync(String url, String jsonStr) {
        //创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        Request request = buildPostRequest(url, jsonStr);
        String data = null;
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                data = response.body().string();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void postDataAsync(String url, String jsonStr, com.wmding.networklib.Callback callback) {
        //创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        Request request = buildPostRequest(url, jsonStr);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Result result = new Result("-1", "error msg: " + e.getMessage());
                callback.onResult(result);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    MyLog.info("string: " + string);
                    Result result = new Result("0", string);
                    callback.onResult(result);
                } else {
                    Result result = new Result("-1", "error msg: " + response.code());
                    callback.onResult(result);
                }
            }
        });
    }

    public void btnPost(View view) {
        ThreadUtil.getThreadPool().execute(() -> {
            String url = "https://www.wanandroid.com/user/login";
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("username", "username");
            hashMap.put("password", "password");
            String json = GsonUtil.getAllJson().toJson(hashMap);
            String data = postDataSync(url, json);
            MyLog.info(data);
            ThreadUtil.runOnMainThread(() -> textView.setText(data));
        });
    }

    public void btnPost2(View view) {
        String url = "https://www.wanandroid.com/user/login";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", "username");
        hashMap.put("password", "password");
        String json = GsonUtil.getAllJson().toJson(hashMap);
        postDataAsync(url, json, result -> ThreadUtil.runOnMainThread(() -> {
            if ("0".equals(result.getCode())) {
                textView.setText(result.getMsg());
            }
        }));
    }
}
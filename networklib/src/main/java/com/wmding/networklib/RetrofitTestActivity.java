package com.wmding.networklib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wmding.commonlib.utils.GsonUtil;
import com.wmding.commonlib.utils.MyLog;
import com.wmding.commonlib.utils.ThreadUtil;
import com.wmding.networklib.api.BaiDuService;
import com.wmding.networklib.api.WanAndroidService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wmding
 * @date 1/25/22 10:00 PM
 * @describe retrofit2 的基本使用
 * https://square.github.io/retrofit/
 */
public class RetrofitTestActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);

        initView();
    }

    private void initView() {
        textView = findViewById(R.id.text_view);
    }

    public void btnGet(View view) {
        String url = "https://www.baidu.com";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaiDuService service = retrofit.create(BaiDuService.class);
        Call<ResponseBody> call = service.getData();
        ThreadUtil.getThreadPool().execute(() -> {
            try {
                Response<ResponseBody> response;
                response = call.execute();

                if (response.isSuccessful()) {
                    String body = response.body().string();
                    MyLog.info(body);
                    ThreadUtil.runOnMainThread(() -> {
                        textView.setText(body);
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 异步get请求，发送请求和接收响应都不用切换线程
     *
     * @param view
     */
    public void btnGet2(View view) {

        String baseUrl = "https://wanandroid.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WanAndroidService wanAndroidService = retrofit.create(WanAndroidService.class);
        Call<HashMap> hashMapCall = wanAndroidService.getwxArticleData();
        hashMapCall.enqueue(new Callback<HashMap>() {
            @Override
            public void onResponse(@NotNull Call<HashMap> call, @NotNull Response<HashMap> response) {
                if (response.isSuccessful()) {
                    HashMap body = response.body();
                    String json = GsonUtil.getAllJson().toJson(body);
                    MyLog.info("json:" + json);
                    textView.setText(json);
                }
            }

            @Override
            public void onFailure(@NotNull Call<HashMap> call, @NotNull Throwable t) {
                String msg = "error:" + t.getMessage();
                MyLog.error(msg);
                textView.setText(msg);
            }
        });
    }

    public void btnPost(View view) throws IOException {
        String baseUrl = "https://wanandroid.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WanAndroidService wanAndroidService = retrofit.create(WanAndroidService.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", "username");
        hashMap.put("password", "password");
        Call<HashMap> loginCall = wanAndroidService.login(hashMap);
        ThreadUtil.getThreadPool().execute(() -> {
            try {
                Response<HashMap> response = loginCall.execute();
                HashMap body = response.body();
                String json = GsonUtil.getAllJson().toJson(body);

                MyLog.info("当前线程为：" + Thread.currentThread().getName());

                ThreadUtil.runOnMainThread(() -> {
                    MyLog.info("json:" + json);
                    textView.setText(json);
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 异步post请求，发送请求和接收响应都不用切换线程
     *
     * @param view
     */
    public void btnPost2(View view) {
        String baseUrl = "https://wanandroid.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WanAndroidService wanAndroidService = retrofit.create(WanAndroidService.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", "username");
        hashMap.put("password", "password");
        Call<HashMap> loginCall = wanAndroidService.login(hashMap);
        loginCall.enqueue(new Callback<HashMap>() {
            @Override
            public void onResponse(Call<HashMap> call, Response<HashMap> response) {
                if (response.isSuccessful()) {
                    MyLog.info("当前线程为：" + Thread.currentThread().getName());
                    HashMap body = response.body();
                    String json = GsonUtil.getAllJson().toJson(body);
                    MyLog.info("json:" + json);
                    textView.setText(json);
                }
            }

            @Override
            public void onFailure(Call<HashMap> call, Throwable t) {
                String msg = "error:" + t.getMessage();
                MyLog.error(msg);
                textView.setText(msg);
            }
        });
    }
}
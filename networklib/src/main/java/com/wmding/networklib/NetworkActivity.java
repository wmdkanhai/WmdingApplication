package com.wmding.networklib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
    }

    public void btnOkHttp(View view) {
        Intent intent = new Intent(this, OkHttpTestActivity.class);
        startActivity(intent);
    }

    public void btnRetrofit(View view) {
        Intent intent = new Intent(this, RetrofitTestActivity.class);
        startActivity(intent);
    }

    public void btnVolley(View view) {
        Intent intent = new Intent(this, VolleyActivity.class);
        startActivity(intent);
    }

    public void btnWebView(View view) {
        Intent intent = new Intent(this, WebViewActivity.class);
        startActivity(intent);
    }
}
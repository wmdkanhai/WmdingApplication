package com.wmding.broadcastreceiverlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ReceiverMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_menu);
    }

    public void test1(View view) {
        Intent intent = new Intent(this, BroadcastReceiverActivity.class);
        startActivity(intent);
    }

    /**
     * 广播的最佳实践-实现强制下线功能
     *
     * @param view
     */
    public void test2(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
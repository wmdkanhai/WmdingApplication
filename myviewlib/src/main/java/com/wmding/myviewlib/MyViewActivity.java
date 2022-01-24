package com.wmding.myviewlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view);
    }

    public void myView1(View view) {
        Intent intent = new Intent(this, MyView1Activity.class);
        startActivity(intent);
    }

    public void myView2(View view) {
        Intent intent = new Intent(this, MyView2Activity.class);
        startActivity(intent);
    }

    public void myView3(View view) {
        Intent intent = new Intent(this, MyView3Activity.class);
        startActivity(intent);
    }
}
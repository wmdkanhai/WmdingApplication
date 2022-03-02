package com.wmding.medialib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
    }

    public void cameraTest(View view) {
        Intent intent = new Intent(this, CameraTestActivity.class);
        startActivity(intent);

    }
}
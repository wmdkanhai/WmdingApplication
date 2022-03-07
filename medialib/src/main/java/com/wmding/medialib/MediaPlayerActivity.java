package com.wmding.medialib;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wmding.commonlib.utils.MyLog;
import com.wmding.commonlib.utils.StringUtil;

import java.io.IOException;

public class MediaPlayerActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 111;
    private String dataFile;
    private MediaPlayer mediaPlayer;
    private Button btnPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
    }

    public void test(View view) {
        //设置intent的属性为录音设置
        Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
        startActivityForResult(intent, REQUEST_CODE);

        initView();

        initMediaPlayer();
    }

    private void initView() {
        btnPause = findViewById(R.id.button3);
    }

    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //请求
            if (requestCode == REQUEST_CODE) {
                //得到录音的音频文件及路径，content://media/external/audio/media/176，使用该路径需要解析:Uri.parse(dataFile)
                dataFile = data.getDataString();
                MyLog.error("dataFile: " + dataFile);
            }
        }
    }


    public void play(View view) {
        if (StringUtil.isBlank(dataFile)) {
            Toast.makeText(this, "请先录音", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(this, Uri.parse(dataFile));
            mediaPlayer.prepare();

            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            btnPause.setText("播放");
        }else {
            mediaPlayer.start();
            btnPause.setText("暂停");
        }
    }

    public void reset(View view) {
        mediaPlayer.reset();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 对资源进行释放
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
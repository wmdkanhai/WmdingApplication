package com.wmding.commonlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.wmding.commonlib.utils.FileUtils;
import com.wmding.commonlib.utils.MyLog;

import java.io.File;

/**
 * @author wmding
 * @date 1/26/22 5:20 PM
 * @describe 文件操作
 */
public class FileTestActivity extends AppCompatActivity {

    private static final String fileName = "test.txt";

    private String getFilePath(String fileName) {
        // 文件路径/data/user/0/com.wmding.wmdingapplication/files/file/test.txt
        String path = getFilesDir().getAbsolutePath() + File.separator + "file" + File.separator + fileName;
        MyLog.info("path: " + path);
        return path;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_test);
    }

    public void btnRead(View view) {
        String filePath = getFilePath(fileName);
        StringBuilder stringBuilder = FileUtils.readFile(filePath, "utf-8");
        MyLog.info("btnRead: " + stringBuilder);

        File file = new File(filePath);

        String fileSize = FileUtils.getFileSize(file);
        MyLog.info("fileSize: " + fileSize);
    }

    public void btnWrite(View view) {
        String filePath = getFilePath(fileName);
        String content = "你好，wmding";
        boolean result = FileUtils.writeFile(filePath, content, true);
        MyLog.info("btnWrite: " + result);
    }

}
package com.wmding.medialib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wmding.commonlib.utils.MyLog;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        MediaActivityPermissionsDispatcher.checkPermissionsWithPermissionCheck(this);
    }

    public void cameraTest(View view) {
        Intent intent = new Intent(this, CameraTestActivity.class);
        startActivity(intent);
    }

    public void choosePhoto(View view) {
        Intent intent = new Intent(this, ChoosePhotoFromAlbumActivity.class);
        startActivity(intent);
    }

    public void mediaPlay1(View view) {
        Intent intent = new Intent(this, MediaPlayerActivity.class);
        startActivity(intent);
    }

    public void mediaPlay2(View view) {
        Intent intent = new Intent(this, MediaPlayer2Activity.class);
        startActivity(intent);
    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO})
    public void checkPermissions() {

    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO})
    public void showRationale(final PermissionRequest permissionRequest) {
        MyLog.error("showRationale");
        permissionRequest.proceed();
    }

    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO})
    public void neverAsk() {
        Toast.makeText(this, "您拒绝了权限，且不再询问，请前往设置中心授权", Toast.LENGTH_LONG).show();
    }

    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO})
    public void denied() {
        Toast.makeText(this, "您拒绝了权限，该功能不可用", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MediaActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
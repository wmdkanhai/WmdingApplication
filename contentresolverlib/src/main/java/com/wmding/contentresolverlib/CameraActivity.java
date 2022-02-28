package com.wmding.contentresolverlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
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
public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }

    public void camera(View view) {
        CameraActivityPermissionsDispatcher.showCameraWithPermissionCheck(this);
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    public void showCamera() {

    }

    @OnShowRationale(Manifest.permission.CAMERA)
    public void showRationale(final PermissionRequest permissionRequest) {
        MyLog.error("showRationale");
        permissionRequest.proceed();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    public void neverAsk() {
        Toast.makeText(this, "您拒绝了相机权限，且不再询问，请前往设置中心授权", Toast.LENGTH_LONG).show();
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    public void denied() {
        Toast.makeText(this, "您拒绝了相机权限，该功能不可用", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        CameraActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
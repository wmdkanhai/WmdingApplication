package com.wmding.medialib;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wmding.commonlib.utils.MyLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class CameraTestActivity extends AppCompatActivity {

    private static final int TAKE_CAMERA = 1;
    private Uri imageUri;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test);

        initView();
    }

    private void initView() {
        imageView = findViewById(R.id.image);
    }

    public void cameraTest(View view) {
        CameraTestActivityPermissionsDispatcher.takeCameraWithPermissionCheck(this);
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    public void takeCamera(){
        File file = new File(getExternalCacheDir(), "image.jpg");
        try {
            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= 24) {

            imageUri = FileProvider.getUriForFile(this, "com.wmding.medialib.fileprovider", file);
        } else {
            imageUri = Uri.fromFile(file);
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_CAMERA);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case TAKE_CAMERA:

                setImageView();
                break;

            default:
                break;
        }

    }

    private void setImageView() {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
            imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
        CameraTestActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
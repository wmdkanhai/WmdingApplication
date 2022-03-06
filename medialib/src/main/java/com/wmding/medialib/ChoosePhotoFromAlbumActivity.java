package com.wmding.medialib;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wmding.commonlib.utils.MyLog;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * @author wmding
 * @date 3/6/22 9:51 PM
 * @describe 从系统相册中获取照片
 */
@RuntimePermissions
public class ChoosePhotoFromAlbumActivity extends AppCompatActivity {

    public static final int SELECT_GALLERY_IMAGE_CODE = 101;

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo_from_album);

        initView();
    }

    private void initView() {
        image = findViewById(R.id.image);
    }

    public void choosePhoto(View view) {

        View chooseTypeView = LayoutInflater.from(this).inflate(R.layout.dialog_choose_pic_type, null);

        AlertDialog selectDialog = new AlertDialog.Builder(this).setView(chooseTypeView).setCancelable(false).create();
        selectDialog.show();

        TextView choosePicCamera = chooseTypeView.findViewById(R.id.tv_choose_pic_camera);
        choosePicCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChoosePhotoFromAlbumActivity.this, "choose pic form camera", Toast.LENGTH_SHORT).show();
                selectDialog.dismiss();
            }
        });

        TextView choosePicGallery = chooseTypeView.findViewById(R.id.tv_choose_pic_gallery);
        choosePicGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChoosePhotoFromAlbumActivity.this, "choose pic form gallery", Toast.LENGTH_SHORT).show();
                ChoosePhotoFromAlbumActivityPermissionsDispatcher.chooseWithPermissionCheck(ChoosePhotoFromAlbumActivity.this);
                selectDialog.dismiss();
            }
        });


        TextView textViewCancel = chooseTypeView.findViewById(R.id.tv_choose_pic_cancel);
        textViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDialog.dismiss();
            }
        });
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void choose() {
        Intent gallery = new Intent(Intent.ACTION_PICK);
        gallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(gallery, SELECT_GALLERY_IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (SELECT_GALLERY_IMAGE_CODE == requestCode) {
            if (null != data) {
                Glide.with(this)
                        .load(data.getData())
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(image);
            } else {
                Toast.makeText(this, "请选择照片", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void showRationale(final PermissionRequest permissionRequest) {
        MyLog.error("showRationale");
        permissionRequest.proceed();
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void neverAsk() {
        Toast.makeText(this, "您拒绝了读取权限，且不再询问，请前往设置中心授权", Toast.LENGTH_SHORT).show();
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void denied() {
        Toast.makeText(this, "您拒绝了读取权限，该功能不可用", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ChoosePhotoFromAlbumActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
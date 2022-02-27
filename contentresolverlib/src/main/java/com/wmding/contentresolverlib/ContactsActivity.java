package com.wmding.contentresolverlib;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
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
public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
    }


    /**
     * 注解在需要调用运行时权限的方法上，当用户给予权限时会执行该方法
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @NeedsPermission(Manifest.permission.READ_CONTACTS)
    public void readContacts() {
        Cursor cursor = null;

        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    MyLog.error("displayName: " + displayName);
                    MyLog.error("number: " + number);
                }

            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


    }


    /**
     * 注解在用于向用户解释为什么需要调用该权限的方法上，只有当第一次请求权限被用户拒绝，下次请求权限之前会调用
     *
     * @param request
     */
    @OnShowRationale(Manifest.permission.READ_CONTACTS)
    public void showRationaleForContacts(PermissionRequest request) {
        showRationaleDialog("需要读取联系人权限", request);
    }

    /**
     * 注解在当用户拒绝了权限请求时需要调用的方法上
     */
    @OnPermissionDenied(Manifest.permission.READ_CONTACTS)
    public void onContactsReadDenied() {
        // NOTE: Deal with a denied permission, e.g. by showing specific UI
        // or disabling certain functionality
        Toast.makeText(this, "1111", Toast.LENGTH_SHORT).show();
    }

    /**
     * 注解在当用户选中了授权窗口中的不再询问复选框后并拒绝了权限请求时需要调用的方法，一般可以向用户解释为何申请此权限，并根据实际需求决定是否再次弹出权限请求对话框
     */
    @OnNeverAskAgain(Manifest.permission.READ_CONTACTS)
    public void onContactsReadNeverAskAgain() {
        Toast.makeText(this, "2222", Toast.LENGTH_SHORT).show();
    }


    private void showRationaleDialog(String message, PermissionRequest request) {
        new AlertDialog.Builder(this).setPositiveButton("确定", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                request.proceed();
            }
        }).setNegativeButton("拒绝", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).setMessage(message)
                .setCancelable(false)
                .show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getContacts(View view) {
        ContactsActivityPermissionsDispatcher.readContactsWithPermissionCheck(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        ContactsActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
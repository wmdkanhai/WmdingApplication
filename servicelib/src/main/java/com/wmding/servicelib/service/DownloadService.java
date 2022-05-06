package com.wmding.servicelib.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.wmding.commonlib.NotificationActivity;
import com.wmding.servicelib.DownloadListener;
import com.wmding.servicelib.DownloadTask;
import com.wmding.servicelib.R;

public class DownloadService extends Service {

    private DownloadTask downloadTask;

    private String DownloadUrl;

    private DownloadListener downloadListener = new DownloadListener() {

        @Override
        public void onProgress(int progress) {
            notification("Downloading...", progress);
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            // 下载成功是将前台服务通知关闭，并创建下一个下载成功的通知
            stopForeground(true);
            notification("Download success", -1);
            Toast.makeText(DownloadService.this, "Download success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            // 下载成功是将前台服务通知关闭，并创建下一个下载成功的通知
            stopForeground(true);
            notification("Download Failed", -1);
            Toast.makeText(DownloadService.this, "Download Failed", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "Download Paused", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "Download Canceled", Toast.LENGTH_SHORT).show();
        }
    };

    public DownloadService() {
    }


    private DownloadBinder downloadBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return downloadBinder;
    }


    class DownloadBinder extends Binder{

        


    }


    private void notification(String title, int progress) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        String id = "MyNotification_channel";
        String name = "MyNotification";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        // 添加跳转页面
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        String contentText = "this is content text this is content text this is content text this is content text this is content textthis is content text";
        Notification notification = new NotificationCompat.Builder(this, id)
                .setContentTitle(title)
//                .setContentText(contentText)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .setProgress(100, progress, false)

                // 设置通知的重要程度
                .setPriority(NotificationManagerCompat.IMPORTANCE_MAX)
                .setAutoCancel(true)

                // 设置长文本样式
                .setStyle(new NotificationCompat.BigTextStyle().bigText(contentText))
                .build();

        notificationManager.notify(1, notification);
    }
}
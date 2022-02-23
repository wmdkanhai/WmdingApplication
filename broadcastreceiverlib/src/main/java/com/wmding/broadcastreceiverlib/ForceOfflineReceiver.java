package com.wmding.broadcastreceiverlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

import com.wmding.commonlib.utils.ActivityCollector;

/**
 * @author wmding
 * @date 2/23/22 8:50 PM
 * @describe
 */
public class ForceOfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Waring");
        builder.setMessage("强制下线，请重新登录");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.finishAll();
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });

        builder.show();
    }
}

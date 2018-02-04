package com.xinyujiang.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by XinyuJiang on 2018/2/4.
 */

public class MyBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
        abortBroadcast();//表示将这条广播截断，后面的广播接收器将无法再接收到这条广播
    }
}

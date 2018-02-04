package com.xinyujiang.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;

    private LocalReceiver localReciver;

    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        * 本地广播的特殊之处在于通过LocalBroadcastManager的实例进行操作。
        * 另外本地广播无法通过静态注册的方式来接收
        * */
        localBroadcastManager = LocalBroadcastManager.getInstance(this);//获取实例
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.xinyujiang.broadcasttest.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);//发送本地广播
            }
        });
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.xinyujiang.broadcasttest.LOCAL_BROADCAST");//我们的广播接收器想要监听什么广播，就在这里添加相应的action
        localReciver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReciver, intentFilter);//注册本地广播监听器
    }

    @Override
    protected void onDestroy() {//动态注册的广播接收器一定要取消注册
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReciver);
    }

    class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}

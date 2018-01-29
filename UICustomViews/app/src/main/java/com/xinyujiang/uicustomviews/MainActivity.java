package com.xinyujiang.uicustomviews;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();//调用getSupportActionBar()方法来获得ActionBar的实例，然后再调用ActionBar的hide()方法将标题栏隐藏起来
        if (actionBar != null)
            actionBar.hide();
    }
}

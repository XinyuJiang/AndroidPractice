package com.xinyujiang.uicustomviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.AttributedCharacterIterator;

/**
 * Created by apple on 2018/1/29.
 */

public class TitleLayout extends LinearLayout {

    public TitleLayout (Context context, AttributeSet attrs){//首先重写了LinearLayout中带两个参数的构造函数
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);//通过LayoutInflater的from()方法可以构建出一个LayoutInflater对象，然后调用Inflate()方法就可以动态加载一个布局文件
    //inflate()接收两个参数，第一个参数是要加载的布局文件的id，这里我们传入R.layout.title,第二个参数是给加载好的布局再添加一个父布局，这里我们想要指定为TitleLayout，于是直接传入this
        Button titleBack = (Button) findViewById(R.id.title_back);
        Button titleEdit = (Button) findViewById(R.id.title_edit);
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
        titleEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"You clicked Edit button",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}

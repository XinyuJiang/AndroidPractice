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
 * Created by XinyuJiang on 2018/1/29.
 */

/*
* 自定义了一个控件titlelayout来实现在所有活动中都实现标题栏，不用重复造轮子。
* 大致过程：之前新建了一个布局title，修改activity_main.xml的代码，include title这个布局后就引入了布局栏；
* 再在mainactivity中将系统自带的标题栏隐藏掉，这时app如果有多个页面，每个都隐藏后就可以实现每个页面都有标题栏。
* 但是问题来了：我们虽然实现了表面功能但是事实上没有为活动注册事件，那么每一个活动都要重新注册很麻烦。
* 其实标题栏中的返回和编辑按钮在每个页面中的功能都差不多，没有必要重复造轮子。
* 因此新建了这个类TitleLayout继承自LinearLayout，首先在这个类里重写了LinearLayout带两个参数的构造函数，
* 用inflate实现动态加载布局文件。这里的第一个参数实现了以title.xml作为加载布局。
* 现在自定义控件已经创建好了，我们再在activity_main.xml中添加这个自定义控件即可。
* 到目前为止，我们实现的效果和使用引入布局方式的效果是一样的。但事实上，我们将整个标题栏不再是用一个布局的形式引入，而是以控件的形式引入。这里的理解很重要。
* 那么多做了这么多步，最重要的事情就在接下来——
* 修改TitleLayout中的代码，为titleBack和titleEdit注册监听器，实现逻辑功能。
* 至此实现了批量建造标题栏，并且为按钮注册逻辑功能
*
* */
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

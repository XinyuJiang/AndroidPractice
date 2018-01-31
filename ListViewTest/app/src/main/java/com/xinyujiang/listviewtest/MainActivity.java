package com.xinyujiang.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] data = { "Apple", "Banana","Orange","Watermelon",
            "Pear","Grape","Pineapple","Strawberry","Cherry","Mango",
            "Apple", "Banana","Orange","Watermelon",
            "Pear","Grape","Pineapple","Strawberry","Cherry","Mango"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用ArrayAdapter作为适配器的实现类来传递数据
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                /*在ArrayAdapter的构造函数中依次传入上下文，ListView 子项布局的id，以及要适配的数据
                *注意这里用android.R.layout.simple_list_item_1 作为ListView子项布局的id，这是一个Android内置的布局文件，里面只有TextView，可用于简单地显示一段文本
                */
                MainActivity.this, android.R.layout.simple_expandable_list_item_1,data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);//将构建好的适配器对象传递进去
    }
}

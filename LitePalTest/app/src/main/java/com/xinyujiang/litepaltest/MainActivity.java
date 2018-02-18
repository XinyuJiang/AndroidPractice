package com.xinyujiang.litepaltest;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//使用新建实例的方式向表中添加数据
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();//save()继承自DataSupport类
            }
        });
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update方法1，但是只能对已存储的对象进行操作，限制性比较大
                /*Book book = new Book();
                book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(19.95);
                book.setPress("Unknow");
                book.save();//save()继承自DataSupport类
                book.setPrice(10.99);
                book.save();*/
                //update方法2
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?","The Lost Symbol","Dan Brown");//相当于SQL中where的用法
                /*如果想要update到初始值那么必须要使用setToDefault()
                Boook book = new Book();
                book.setToDefault("pages");
                book.updateAll();
                */
            }
        });
        Button deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class,"price< ?","15");//第一个参数用于指定删除哪张表中的数据，后面的参数表示约束条件；该行表示删除Book表中价格低于15的书
            }
        });
        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = DataSupport.findAll(Book.class);
                //这里也支持用DataSupport.findBySQL()进行原生查询
                for(Book book :books){
                    Log.d("MainActivity","book name is "+ book.getName());
                    Log.d("MainActivity","book author is "+ book.getAuthor());
                    Log.d("MainActivity","book pages is "+ book.getPages());
                    Log.d("MainActivity","book price is "+ book.getPrice());
                    Log.d("MainActivity","book press is "+ book.getPress());
                }
            }
        });
    }
}

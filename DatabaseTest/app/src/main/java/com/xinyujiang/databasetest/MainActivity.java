package com.xinyujiang.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db",null, 2);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //开始组装第一条数据
                values.put("name","The Da Vinci Code");
                values.put("author","Dan Brown");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("Book",null,values);//插入第一条数据；第一个参数是表的名字，第二个参数用于在未指定添加数据的情况下给某些可为空的列自动赋值NULL,第三个参数提供了一系列的put()方法重载
                //开始组装第二条数据
                values.put("name","The Lost Symbol");
                values.put("author","Dan Brown");
                values.put("pages",510);
                values.put("price",19.95);
                db.insert("Book",null,values);//插入第二条数据
            }
        });
        Button updateData = (Button) findViewById(R.id.update_data);
        addData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price","10.99");
                db.update("Book",values,"name = ?",new String[]{"The Da Vinci Code"//第三个参数对应的是SQL语句的where部分，第四个参数提供的一个字符串数组为第三个参数中的每个占位符指定相应的内容
                });
            }
        });
        Button deleteButton = (Button) findViewById(R.id.delete_data);
        addData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book","pages > ?",new String[]{"500"//第三个参数对应的是SQL语句的where部分，第四个参数提供的一个字符串数组为第三个参数中的每个占位符指定相应的内容
                });
            }
        });
        Button queryButton = (Button) findViewById(R.id.query_data);
        addData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //查询Book表中所有的数据
                Cursor cursor = db.query("Book",null,null,null,null,null,null);//参数：1:表名；2：查询哪几列；3，4：约束查询哪些行；5：group by；6：having 7：order by
                if(cursor.moveToFirst()){
                    do{
                        //遍历Cursor对象，取出数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity","book name is "+ name);
                        Log.d("MainActivity","book author is "+ author);
                        Log.d("MainActivity","book pages is "+ pages);
                        Log.d("MainActivity","book price is "+ price);
                    }while(cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}

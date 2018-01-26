package com.xinyujiang.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//requestcode表示启动活动时候的请求码，startactivity启动活动没有这个，但是startactivityforresult则会设定请求码；第二个参数即我们在返回数据时传入的处理结果
        switch(requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("FirstActivity",returnedData);
                }
                break;
            default:
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//定义菜单响应事件
        switch(item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//为当前活动创建对象
        getMenuInflater().inflate(R.menu.main,menu);//第一个参数指定通过哪一个资源文件来创建菜单，第二个参数指定菜单项将添加到哪一个Menu对象中
        return true;//return true表示允许创建的菜单显示出来
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);//在活动中加载这个布局
        Button button1 = (Button) findViewById(R.id.button_1); //获取在布局文件中定义的元素，返回View对象
        button1.setOnClickListener(new View.OnClickListener(){  //为按钮注册一个监听器
            @Override
            public void onClick(View v){
                Toast.makeText(FirstActivity.this,"You clicked Button 1",//第一个参数是Context，即Toast要求的上下文,第二个是显示的文本内容
                        // 第三个是显示的时长
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (FirstActivity.this,SecondActivity.class);//显式Intent;实现启动活动（以first作为上下文得到second的句柄，在下一行代码中启动目标活动second）
                /*Intent intent = new Intent("com.xinyujiang.activitytest.ACTION_START");//隐式Intent;直接将action的字符串传了进去，表明我们想要启动能够响应表示该字符串的action的活动。这里category采用了默认配置。
                intent.addCategory("com.xinyujiang.activitytest.MY_CATEGORY");//添加一个category*/
                startActivityForResult(intent,1);

                //String data = "Hello SecondActivity";
                //Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                //intent.putExtra("extra_data",data);

                //Intent intent = new Intent(Intent.ACTION_DIAL);//首先指定Intent的Action是Intent.ACTION_DIAL 这是Android内置的动作
                //intent.setData(Uri.parse("tel:10086"));

                //Intent intent = new Intent(Intent.ACTION_VIEW);//首先指定Intent的Action是Intent.ACTION_VIEW 这是Android内置的动作
                //intent.setData(Uri.parse("http://www.baidu.com"));//将网址字符串解析为Uri对象再调用setData将这个对象传递进去
                //startActivity(intent);//如果startactivity和startactivityforresult同时存在的话，跳转到secondactivity后要点两次按钮才会返回firstactivity，原因未知
            };
            /*@Override
            public void onClick(View v){
                finish();//销毁一个活动
            }*/

        });
    }
}

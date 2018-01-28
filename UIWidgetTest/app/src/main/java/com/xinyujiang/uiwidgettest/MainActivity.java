package com.xinyujiang.uiwidgettest;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    private ImageView imageView;

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.edit_text);
        imageView = (ImageView) findViewById(R.id.image_view);
        progressBar = (ProgressBar) findViewById(R.id.progree_bar);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button:
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);  //首先通过AlertDialog.Builder创建一个AlertDialog的实例

                        dialog.setTitle("This is Dialog");//为这个对话框设置标题，内容，可否用Back键关闭对话框等属性
                        dialog.setMessage("Something important.");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {//为对话框设置确定按钮的点击事件
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {//设置取消按钮的点击事件
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.show();//调用show()方法把对话框显示出来
                        break;

                        /*int progress = progressBar.getProgress();
                        progress = progress + 10;
                        progressBar.setProgress(progress);
                        break;*/

                        /*
                        * android的控件都有一个可见属性。可以通过android:visibility进行指定，有visible,invisible和gone三种可选值。
                        * visible表示控件是可见的，这个值是默认值
                        * invisible表示控件不可见，但是它仍然占据着原来的位置和大小，可以理解成控件变成透明状态了
                        * gone表示控件不仅不可见，客人切不再占用任何屏幕空间
                        * */
                        /*if(progressBar.getVisibility() == View.GONE) {
                            progressBar.setVisibility(View.VISIBLE);
                        }else {
                            progressBar.setVisibility(View.GONE);
                        }break;*/
                    default:
                        break;
                }
            }
        });
    }
}

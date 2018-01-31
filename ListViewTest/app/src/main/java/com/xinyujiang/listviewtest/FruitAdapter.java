package com.xinyujiang.listviewtest;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by XinyuJiang on 2018/1/31.
 */

public class FruitAdapter extends ArrayAdapter<Fruit>{

    private int resourceId;

    public FruitAdapter(Context context, int textViewResourceId,
                        List<Fruit> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
/*
* 做出两点优化：
* 利用convertview的缓存功能使得不用每次都重新加载view；
* 使用内部类ViewHolder对控件的实例进行缓存。使得不用每次都找Id
* */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//convert可以将之前加载好的布局缓存，以便之后可以重用
        Fruit fruit = getItem(position);//获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);//为子项加载我们传入的布局；第三个参数为false表示只让我们在父布局中声明的Layout有效但不会为这个view添加父布局
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);//利用settag()将viewHolder存储在view中(一个view有一个viewHolder)
        }else {
            view = convertView;//重用之前缓存好的布局
            viewHolder = (ViewHolder) view.getTag();//取出viewHolder
        }
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }
    class ViewHolder {
        ImageView fruitImage;

        TextView fruitName;
    }
}

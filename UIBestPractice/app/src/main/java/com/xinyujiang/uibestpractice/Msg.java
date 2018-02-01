package com.xinyujiang.uibestpractice;

/**
 * Created by XinyuJiang on 2018/2/1.
 */

public class Msg {

    public static final int TYPE_RECEIVED = 0;//这是一条发出的消息

    public static final int TYPE_SENT = 1;//这是一条收到的消息

    private String content;

    private int type;

    public Msg(String content, int type){
        this.content = content;
        this.type = type;
    }

    public String getContent(){
        return content;
    }

    public int getType(){
        return type;
    }
}

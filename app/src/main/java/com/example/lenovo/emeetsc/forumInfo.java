package com.example.lenovo.emeetsc;

import android.util.Log;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2017/3/25.
 */

public class forumInfo{
    private String name;
    private Date time;
    private String Title;
    private String content;
    private int imgId;

    public forumInfo(String name,String Title,String content,int imgId){
        this.name=name;
        this.Title=Title;
        this.content=content;
        this.imgId=imgId;
//        this.time=time;
        //this.imgId=imgId;
    }

    public int getImgId(){
        return  imgId;
    }
    public void setImgId(int imgId){
        this.imgId=imgId;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

//   public String getTime(){
//       long time=System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
//       SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//       Date d1=new Date(time);
//       String t1=format.format(d1);
//       return t1;
//   }
//    public void setTime(Date time){
//        this.time=time;
//    }

    public String getTitle(){
        return Title;
    }
    public void setTitle(String Title){
        this.Title=Title;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content=content;
    }
//    public int getImgId(){
//        return imgId;
//    }
//    public void setImgId(int imgId){
//        this.imgId=imgId;
//    }


}
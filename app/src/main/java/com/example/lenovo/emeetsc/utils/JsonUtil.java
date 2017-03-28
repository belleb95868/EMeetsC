package com.example.lenovo.emeetsc.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;


/**
 * Created by ydc on 17/3/16.
 */

public class JsonUtil {
    public static <T> String map2Json(Map<String, T> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        return jsonStr;
    }

   /**json字符串转成对象
    * @param str
    * @param类型
    * @return
    * */
    public static  <T> T fromJson(String str,Type type){
        Gson gson =  new  Gson();
        return  gson.fromJson(str,type);
    }
}

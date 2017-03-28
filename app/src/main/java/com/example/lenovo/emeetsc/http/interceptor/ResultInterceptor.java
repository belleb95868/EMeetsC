package com.example.lenovo.emeetsc.http.interceptor;


import com.example.lenovo.emeetsc.http.HttpInfo;

/**
 * 请求结果拦截器
 * @author ydc
 */
public interface ResultInterceptor {

    HttpInfo intercept(HttpInfo info) throws Exception;

}

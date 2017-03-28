package com.example.lenovo.emeetsc.http.interceptor;


import com.example.lenovo.emeetsc.http.HttpInfo;

/**
 * 请求链路异常（非业务逻辑）拦截器
 * @author ydc
 */
public interface ExceptionInterceptor {

    HttpInfo intercept(HttpInfo info) throws Exception;

}

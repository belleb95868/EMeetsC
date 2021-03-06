package com.example.lenovo.emeetsc.http;

import com.example.lenovo.emeetsc.http.callback.CallbackOk;

import okhttp3.OkHttpClient;

/**
 * 网络请求工具接口
 * @author ydc
 */
public interface OkHttpUtilInterface {


    /**
     * 同步Post请求
     * @param info 请求信息体
     * @return HttpInfo
     */
    HttpInfo doPostSync(HttpInfo info);

    /**
     * 异步Post请求
     * @param info 请求信息体
     * @param callback 回调接口
     */
    void doPostAsync(HttpInfo info, CallbackOk callback);

    /**
     * 同步Get请求
     * @param info 请求信息体
     * @return HttpInfo
     */
    HttpInfo doGetSync(HttpInfo info);

    /**
     * 异步Get请求
     * @param info 请求信息体
     * @param callback 回调接口
     */
    void doGetAsync(HttpInfo info, CallbackOk callback);

    /**
     * 取消请求
     * @param requestTag 请求标识
     */
    void cancelRequest(Object requestTag);


    /**
     * 获取默认的HttpClient
     */
    OkHttpClient getDefaultClient();

}

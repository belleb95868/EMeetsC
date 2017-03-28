package com.example.lenovo.emeetsc.http.callback;


import com.example.lenovo.emeetsc.http.HttpInfo;
import com.example.lenovo.emeetsc.http.bean.OkMessage;

/**
 * 响应回调信息体
 * @author ydc
 */
public class CallbackMessage extends OkMessage {

    public CallbackOk callback;
    public HttpInfo info;

    public CallbackMessage(int what, CallbackOk callback, HttpInfo info) {
        this.what = what;
        this.callback = callback;
        this.info = info;
    }


}
package com.example.lenovo.emeetsc.http.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.lenovo.emeetsc.http.callback.CallbackMessage;


/**
 * 主线程Handler
 * @author ydc
 */
public class OkMainHandler extends Handler {

    private static OkMainHandler singleton;

    public static OkMainHandler getInstance(){
        if(null == singleton){
            synchronized (OkMainHandler.class){
                if(null == singleton)
                    singleton = new OkMainHandler();
            }
        }
        return singleton;
    }

    public OkMainHandler() {
        super(Looper.getMainLooper());
    }

    /**
     * 网络请求回调标识
     */
    public static final int RESPONSE_CALLBACK = 0x01;


    @Override
    public void handleMessage(Message msg) {
        final int what = msg.what;
        try {
            switch (what){
                case RESPONSE_CALLBACK:
                    CallbackMessage callMsg = (CallbackMessage) msg.obj;
                    if(null != callMsg.callback)
                        callMsg.callback.onResponse(callMsg.info);
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }catch (Exception e){

        }
    }



}

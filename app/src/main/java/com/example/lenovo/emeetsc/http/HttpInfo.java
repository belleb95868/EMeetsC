package com.example.lenovo.emeetsc.http;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * Http请求实体类
 * @author ydc
 */
public class HttpInfo {

    //**请求参数定义**/
    private String url;//请求地址
    private Map<String, String> params;//请求参数
    private Map<String, String> heads;//请求头参数http head
    private String jsonStr;

    //**响应返回参数定义**/
    private int retCode;//返回码
    private String retDetail;//返回结果
    private int netCode;//网络返回码

    public HttpInfo(Builder builder) {
        this.url = builder.url;
        this.params = builder.params;
        this.heads = builder.heads;
        this.jsonStr=builder.jsonStr;
    }

    public static Builder Builder() {
        return new Builder();
    }


    public static final class Builder {

        private String url;
        private Map<String, String> params;
        private Map<String, String> heads;
        private String jsonStr;


        public Builder() {
        }

        public HttpInfo build() {
            return new HttpInfo(this);
        }


        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }
        public Builder setJsonStr(String jsonStr){
            this.jsonStr=jsonStr;
            return this;
        }
        public String getJsonStr(){
            return jsonStr;
        }

        /**
         * 添加接口参数
         *
         * @param params 参数集合
         */
        public Builder addParams(Map<String, String> params) {
            if (null == params)
                return this;
            if (null == this.params) {
                this.params = params;
            } else {
                this.params.putAll(params);
            }
            return this;
        }

        /**
         * 添加接口参数
         *
         * @param key   参数名
         * @param value 参数值
         */
        public Builder addParam(String key, String value) {
            if (null == this.params)
                this.params = new HashMap<>();
            if (!TextUtils.isEmpty(key)) {
                value = value == null ? "" : value;
                this.params.put(key, value);
            }
            return this;
        }

        /**
         * 添加协议头参数
         *
         * @param heads 头参数集合
         */
        public Builder addHeads(Map<String, String> heads) {
            if (null == heads)
                return this;
            if (null == this.heads) {
                this.heads = heads;
            } else {
                this.heads.putAll(heads);
            }
            return this;
        }

        /**
         * 添加协议头参数
         *
         * @param key   头参数名
         * @param value 头参数值
         */
        public Builder addHead(String key, String value) {
            if (null == this.heads)
                this.heads = new HashMap<>();
            if (!TextUtils.isEmpty(key)) {
                value = value == null ? "" : value;
                this.heads.put(key, value);
            }
            return this;
        }
    }

        //**请求返回常量定义**/
        public final static int SUCCESS = 1;
        public final static int NonNetwork = 2;
        public final static int ProtocolException = 3;
        public final static int NoResult = 4;
        public final static int CheckURL = 5;
        public final static int CheckNet = 6;
        public final static int ConnectionTimeOut = 7;
        public final static int WriteAndReadTimeOut = 8;
        public final static int ConnectionInterruption = 9;
        public final static int NetworkOnMainThreadException = 10;
        public final static int Message = 11;

        public HttpInfo packInfo(int netCode, int retCode, String retDetail) {
            this.netCode = netCode;
            this.retCode = retCode;
            switch (retCode) {
                case NonNetwork:
                    this.retDetail = "网络中断";
                    break;
                case SUCCESS:
                    this.retDetail = "发送请求成功";
                    break;
                case ProtocolException:
                    this.retDetail = "请检查协议类型是否正确";
                    break;
                case NoResult:
                    this.retDetail = "无法获取返回信息(服务器内部错误)";
                    break;
                case CheckURL:
                    this.retDetail = "请检查请求地址是否正确";
                    break;
                case CheckNet:
                    this.retDetail = "请检查网络连接是否正常";
                    break;
                case ConnectionTimeOut:
                    this.retDetail = "连接超时";
                    break;
                case WriteAndReadTimeOut:
                    this.retDetail = "读写超时";
                    break;
                case ConnectionInterruption:
                    this.retDetail = "连接中断";
                    break;
                case NetworkOnMainThreadException:
                    this.retDetail = "不允许在UI线程中进行网络操作";
                    break;
                case Message:
                    this.retDetail = "";
                    break;
            }
            if (!TextUtils.isEmpty(retDetail)) {
                this.retDetail = retDetail;
            }
            return this;
        }

        public int getRetCode() {
            return retCode;
        }

        public boolean isSuccessful() {
            return this.retCode == SUCCESS;
        }

        public String getUrl() {
            return url;
        }

        public String getRetDetail() {
            return retDetail;
        }

        public void setRetDetail(String retDetail) {
            this.retDetail = retDetail;
        }

        public Map<String, String> getParams() {
            return params;
        }

        public Map<String, String> getHeads() {
            return heads;
        }

        public int getNetCode() {
            return netCode;
        }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}


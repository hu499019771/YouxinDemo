package com.chinabluedon.youxindemo.http;

import java.util.Map;

/**
 * @author 胡腾
 * @time 2017/8/23  22:54
 * @desc 请求参数封装bean
 */
public class Requset {

    /**
     * 将请求方式封装在枚举中
     */
    public enum method {GET, POST, PUT, DELETE}

    private String url;
    private String content;
    private Map<String, String> headers;
    private method method;
    private ICallBack mCallBack;



    public Requset() {
    }


    public Requset(String url, Requset.method method) {
        this.url = url;
        this.method = method;
    }

    public ICallBack getCallBack() {
        return mCallBack;
    }

    public void setCallBack(ICallBack callBack) {
        mCallBack = callBack;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Requset.method getMethod() {
        return method;
    }

    public void setMethod(Requset.method method) {
        this.method = method;
    }
}

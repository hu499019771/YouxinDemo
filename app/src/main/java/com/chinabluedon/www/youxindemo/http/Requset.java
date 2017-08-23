package com.chinabluedon.www.youxindemo.http;

import java.util.Map;

/**
 * @author 胡腾
 * @time 2017/8/23  22:54
 * @desc ${TODD}
 */
public class Requset {

    private String url;
    private String content;
    private Map<String, String> headers;

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
}

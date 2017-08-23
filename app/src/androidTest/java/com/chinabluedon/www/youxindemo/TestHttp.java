package com.chinabluedon.www.youxindemo;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.chinabluedon.www.youxindemo.http.HttpUrlConnectionUtils;
import com.chinabluedon.www.youxindemo.http.Requset;

import java.io.IOException;

/**
 * @author 胡腾
 * @time 2017/8/23  21:49
 * @desc ${TODD}
 */
public class TestHttp extends InstrumentationTestCase {

    private static final String TAG="TestHttp";

    /**
     * 测试get请求
     */
    public void testHttpGet() throws IOException {
        String url = "http://api.stay4it.com";
        Requset requset = new Requset(url, Requset.method.GET);
        String result = HttpUrlConnectionUtils.excute(requset);
        Log.e(TAG,result);
    }

    /**
     * 测试post请求
     */
    public void testHttpPost() throws IOException {
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        Requset requset=new Requset(url, Requset.method.POST);
        requset.setContent(content);
        String result = HttpUrlConnectionUtils.excute(requset);
        Log.e(TAG,result);
    }
}

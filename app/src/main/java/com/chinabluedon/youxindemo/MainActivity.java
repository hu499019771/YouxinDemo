package com.chinabluedon.youxindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chinabluedon.youxindemo.http.ICallBack;
import com.chinabluedon.youxindemo.http.RequestTask;
import com.chinabluedon.youxindemo.http.Requset;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testHtttpGetOnSubThread();



    }

    private void testHtttpGetOnSubThread () {
        String url = "http://api.stay4it.com";
        Requset requset = new Requset(url, Requset.method.GET);
        requset.setCallBack(new ICallBack() {
            @Override
            public void onSucceed (String result) {
                Log.e(TAG,result);
            }

            @Override
            public void onFailed (Exception e) {
                e.printStackTrace();
            }
        });
        new RequestTask().execute(requset);
    }

    private void testHttpPostOnSubThread () {
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        Requset requset=new Requset(url, Requset.method.POST);
        requset.setContent(content);
        requset.setCallBack(new ICallBack() {
            @Override
            public void onSucceed(String result) {
                Log.e(TAG,result);
            }

            @Override
            public void onFailed(Exception e) {
                e.printStackTrace();
            }
        });
        new RequestTask().execute(requset);
    }
}

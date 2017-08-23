package com.chinabluedon.www.youxindemo.http;

/**
 * @author 胡腾
 * @time 2017/8/24  0:01
 * @desc ${TODD}
 */
public interface ICallBack {

    void onSucceed(String result);

    void onFailed(Exception e);
}

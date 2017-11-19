package com.chinabluedon.youxindemo.http;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * @author 胡腾
 * @time 2017/8/23  23:31
 * @desc ${TODD}
 */
public class RequestTask extends AsyncTask<com.chinabluedon.youxindemo.http.Requset, Integer, Object> {

    private ICallBack mCallBack;

    /**
     * 在子线程方法执行之前,会在主线程执行这个方法
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 子线程执行的操作,返回值可用于阶段性显示进度和执行完成后回调过来
     *
     * @param params
     * @return
     */
    @Override
    protected Object doInBackground(com.chinabluedon.youxindemo.http.Requset... params) {
        try {
            if (params != null && params.length > 0) {
                mCallBack = params[0].getCallBack();
                return HttpUrlConnectionUtils.excute(params[0]);
            }
            return null;
        } catch (IOException e) {
            return e;
        }
    }

    /**
     * 在doInBackground方法中调用publishProgress(int)后,会将int值通过这个方法回调过来,在主线程中执行,一般用来显示进度
     *
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    /**
     * doInBackground方法执行完成后,在主线程中将doInBackground方法的返回值通过这个方法回调过来
     *
     * @param o
     */
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof Exception) {
            if (mCallBack != null) {
                mCallBack.onFailed((Exception) o);
            }
        } else {
            if (mCallBack != null) {
                mCallBack.onSucceed((String) o);
            }
        }
    }
}

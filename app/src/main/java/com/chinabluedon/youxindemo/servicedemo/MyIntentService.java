package com.chinabluedon.youxindemo.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author ht
 * @time 2017/10/19  14:40
 * @desc ${TODD}
 */
public class MyIntentService extends IntentService {

    private DownloadBinder mDownloadBinder = new DownloadBinder();

    private final static String TAG = "MyIntentService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MyIntentService () {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent (@Nullable Intent intent) {
        Log.e(TAG, "---MyIntentService---onHandleIntent");
        Log.e(TAG,"---线程名称---"+Thread.currentThread().getName());
    }

    @Override
    public void onCreate () {
        super.onCreate();
        Log.e(TAG, "---MyIntentService---onCreate");
    }

    @Override
    public int onStartCommand (@Nullable Intent intent, int flags, int startId) {
        Log.e(TAG, "---MyIntentService---onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind (Intent intent) {
        Log.e(TAG, "---MyIntentService---onBind");
        return mDownloadBinder;
    }

    @Override
    public boolean onUnbind (Intent intent) {
        Log.e(TAG, "---MyIntentService---onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
        Log.e(TAG, "---MyIntentService---onDestroy");
    }

    class DownloadBinder extends Binder {

        public void startDownload () {
            Log.e(TAG, "----MyIntentService---startDownload");
        }

        public int getProcess () {
            Log.e(TAG, "----MyIntentService---getProcess");
            return 0;
        }

    }
}

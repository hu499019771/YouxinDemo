package com.chinabluedon.www.youxindemo.servicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.chinabluedon.www.youxindemo.R;

/**
 * @author ht
 * @time 2017/10/19  11:21
 * @desc ${TODD}
 */
public class MyService extends Service {

    private DownloadBinder mBinder=new DownloadBinder();

    private final static String TAG = "MyService";


    //服务创建时回调
    @Override
    public void onCreate () {
        super.onCreate();
        Log.e(TAG, "----MyService---onCreate");
        Intent intent = new Intent(this, ServiceActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification=new NotificationCompat.Builder(this)
                .setContentTitle("这是标题")
                .setContentText("这是内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.music))
                .setContentIntent(pi)
                .setTicker("你有一条新消息")
                .build();
        startForeground(1,notification);
    }

    //服务启动时回调
    @Override
    public int onStartCommand (Intent intent, int flags, int startId) {
        Log.e(TAG, "----MyService---onStartCommand");
        new Thread(new Runnable() {
            @Override
            public void run () {
                Log.e(TAG, "线程id为----" + Thread.currentThread().getName());
                stopSelf();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    //服务销毁时回调
    @Override
    public void onDestroy () {
        super.onDestroy();
        Log.e(TAG, "----MyService---onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind (Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind (Intent intent) {
        return super.onUnbind(intent);
    }

    class DownloadBinder extends Binder {

        public void startDownload(){
            Log.e(TAG, "----MyService---startDownload");
        }

        public int getProcess(){
            Log.e(TAG, "----MyService---getProcess");
            return 0;
        }

    }
}

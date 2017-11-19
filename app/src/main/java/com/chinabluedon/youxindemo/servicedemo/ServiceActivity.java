package com.chinabluedon.youxindemo.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.chinabluedon.youxindemo.R;


/**
 * @author ht
 * @time 2017/10/19  11:30
 * @desc ${TODD}
 */
public class ServiceActivity extends AppCompatActivity {

    private static final String TAG = "ServiceActivity";
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected (ComponentName name, IBinder service) {
            MyService.DownloadBinder downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProcess();
        }

        @Override
        public void onServiceDisconnected (ComponentName name) {

        }
    };

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    public void startService (View view) {
        startService(new Intent(this, MyIntentService.class));
        Log.e(TAG, "线程id为----" + Thread.currentThread().getName());
    }

    public void stopService (View view) {
        stopService(new Intent(this, MyIntentService.class));
    }

    public void bindService (View view) {
        bindService(new Intent(this, MyIntentService.class), mServiceConnection, BIND_AUTO_CREATE);
    }

    public void unbindService (View view) {
        unbindService(mServiceConnection);
    }
}

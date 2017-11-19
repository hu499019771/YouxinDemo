package com.chinabluedon.youxindemo.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chinabluedon.youxindemo.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author 胡腾
 * @time 2017/11/7  22:32
 * @desc ${TODD}
 */
public class CreateActivity extends Activity {

    private static final String TAG ="CreateActivity" ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        testTimer();
    }

    private void testTimer(){
        Observable.just("1")
                .delay(10, TimeUnit.SECONDS)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG,s);
                    }
                });
    }
}

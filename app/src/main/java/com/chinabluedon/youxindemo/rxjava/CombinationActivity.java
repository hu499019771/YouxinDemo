package com.chinabluedon.youxindemo.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chinabluedon.youxindemo.R;

import java.io.Serializable;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

/**
 * @author 胡腾
 * @time 2017/11/5  11:01
 * @desc ${TODD}
 */
public class CombinationActivity extends Activity {

    private static final String TAG = "CombinationActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combination);
        //        testZip();
        testMerge();
    }

    private void testMerge() {
        Observable<Integer> observable1 = Observable.just(1, 2, 3);
        Observable<String> observable2 = Observable.just("4", "5", "6", "7");
        Observable.merge(observable2, observable1).subscribe(new Consumer<Serializable>() {
            @Override
            public void accept(Serializable serializable) throws Exception {
                if(serializable instanceof Integer) {
                    Log.i(TAG,(Integer)serializable+"");
                }
                if(serializable instanceof String) {
                    Log.i(TAG,(String) serializable);
                }
            }
        });
    }

    //将多个被观察者对象合并成一个来发射数据,发射的数据规则通过函数来自定义,如果有一个被观察者发射完或者错误,
    //那么就终止发射数据
    private void testZip() {
        Observable<Integer> observable1 = Observable.just(1, 2, 3);
        Observable<String> observable2 = Observable.just("4", "5", "6", "7");
        Observable<String> observable3 = Observable.just("8", "9", "10", "11", "12");
        Observable.zip(observable1, observable2, observable3, new Function3<Integer, String, String, String>() {
            @Override
            public String apply(Integer integer, String s, String s2) throws Exception {
                return integer + s + s2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, s);
            }
        });
    }


}

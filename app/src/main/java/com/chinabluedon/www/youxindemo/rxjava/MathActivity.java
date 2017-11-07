package com.chinabluedon.www.youxindemo.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chinabluedon.www.youxindemo.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author 胡腾
 * @time 2017/11/7  23:09
 * @desc ${TODD}
 */
public class MathActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
        averageInteger();
    }

    private void averageInteger() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Observable<Integer> iterable = Observable.fromIterable(list);
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5);
        just.count().subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {

            }
        });
    }


}

package com.chinabluedon.youxindemo.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chinabluedon.youxindemo.R;


/**
 * @author ht
 * @time 2017/10/17  17:27
 * @desc ${TODD}
 */
public class FragmentActivity extends android.support.v4.app.FragmentActivity {

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}

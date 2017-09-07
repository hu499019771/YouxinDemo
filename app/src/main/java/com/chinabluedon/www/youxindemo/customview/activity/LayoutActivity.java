package com.chinabluedon.www.youxindemo.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chinabluedon.www.youxindemo.R;

/**
 * @author ht
 * @time 2017/9/7  20:27
 * @desc ${TODD}
 */
public class LayoutActivity extends Activity {

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_layout);
    }
}

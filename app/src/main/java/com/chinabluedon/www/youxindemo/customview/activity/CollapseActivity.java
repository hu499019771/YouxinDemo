package com.chinabluedon.www.youxindemo.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chinabluedon.www.youxindemo.R;
import com.chinabluedon.www.youxindemo.customview.view.CollapseView;

/**
 * @author ht
 * @time 2017/9/6  17:20
 * @desc ${TODD}
 */
public class CollapseActivity extends Activity {

    private CollapseView mCollapseView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse);
        mCollapseView = (CollapseView) findViewById(R.id.collapse);
        mCollapseView.addToContainer(R.layout.layout_imageview);
    }
}

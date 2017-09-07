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

    private CollapseView mCollapseView1;
    private CollapseView mCollapseView2;
    private CollapseView mCollapseView3;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse);
        mCollapseView1 = (CollapseView) findViewById(R.id.collapse1);
        mCollapseView1.addToContainer(R.layout.layout_imageview);
        mCollapseView1.setNumber("1");
        mCollapseView1.setContent("小猪1");

        mCollapseView2 = (CollapseView) findViewById(R.id.collapse2);
        mCollapseView2.addToContainer(R.layout.layout_imageview);
        mCollapseView2.setNumber("2");
        mCollapseView2.setContent("小猪2");

        mCollapseView3 = (CollapseView) findViewById(R.id.collapse3);
        mCollapseView3.addToContainer(R.layout.layout_imageview);
        mCollapseView3.setNumber("3");
        mCollapseView3.setContent("小猪3");
    }
}

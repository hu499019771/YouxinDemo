package com.chinabluedon.youxindemo.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chinabluedon.youxindemo.R;

/**
 * @author ht
 * @time 2017/9/11  15:17
 * @desc ${TODD}
 */
public class ScrollerActivity extends Activity implements View.OnClickListener {

    private com.chinabluedon.youxindemo.customview.view.ScrollLinearLayout mScrollLinearLayout;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);
        findViewById(R.id.button).setOnClickListener(this);
        mScrollLinearLayout = (com.chinabluedon.youxindemo.customview.view.ScrollLinearLayout) findViewById(R.id.scrollLinearLayout);
    }

    @Override
    public void onClick (View v) {
        mScrollLinearLayout.beginScroll();
    }
}

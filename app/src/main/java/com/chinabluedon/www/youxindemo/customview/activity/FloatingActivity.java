package com.chinabluedon.www.youxindemo.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chinabluedon.www.youxindemo.R;
import com.chinabluedon.www.youxindemo.customview.view.FloatingButton;

/**
 * @author ht
 * @time 2017/9/18  14:19
 * @desc ${TODD}
 */
public class FloatingActivity extends Activity implements View.OnClickListener {

    private FloatingButton mFloatingButton;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating);
        mFloatingButton = (FloatingButton) findViewById(R.id.floatingButton);
        mFloatingButton.setOnClickListener(this);
    }

    @Override
    public void onClick (View v) {
        mFloatingButton.startScroll();
    }
}

package com.chinabluedon.www.youxindemo.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chinabluedon.www.youxindemo.R;

/**
 * @author ht
 * @time 2017/9/19  10:54
 * @desc ${TODD}
 */
public class TimeLineActivity extends Activity {

    private View mView;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        mView = findViewById(R.id.timeLineView);

    }

    @Override
    protected void onStart () {
        super.onStart();


    }

    @Override
    protected void onResume () {
        super.onResume();

    }

}

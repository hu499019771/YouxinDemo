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
        mView = findViewById(R.id.view_test);
        mView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange (View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                mView.removeOnLayoutChangeListener(this);
                int width = mView.getWidth();
                int height = mView.getHeight();
                int paddingLeft = mView.getPaddingLeft();
                int paddingRight = mView.getPaddingRight();
                System.out.println("");
            }
        });
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

package com.chinabluedon.www.youxindemo.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;

import com.chinabluedon.www.youxindemo.R;

/**
 * @author ht
 * @time 2017/9/12  9:45
 * @desc ${TODD}
 */
public class ViewGroupActivity extends Activity {

    private final static String TAG = "ViewGroupActivity";

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewgroup);
    }

    @Override
    public boolean onTouchEvent (MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent--MOVE");
                break;
        }
        return super.onTouchEvent(e);
    }
}

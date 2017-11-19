package com.chinabluedon.youxindemo.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import com.chinabluedon.youxindemo.R;


/**
 * @author ht
 * @time 2017/8/30  8:46
 * @desc ${TODD}
 */
public class CustomViewActivity extends Activity {

    private final static String TAG = "CustomViewActivity";

    private GestureDetector mGestureDetector;
    private VelocityTracker mVelocityTracker;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        gestureDetector();
        velocityTracker();
    }


    private void velocityTracker () {
        //2.1开始速度追踪
        startTracker(null);
    }

    private void startTracker (MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private void gestureDetector () {
        //1.1创建GestureDetector
        mGestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown (MotionEvent e) {
                Log.e(TAG, "手势-->onDown");
                return false;
            }

            @Override
            public void onShowPress (MotionEvent e) {
                Log.e(TAG, "手势-->onShowPress");
            }

            @Override
            public boolean onSingleTapUp (MotionEvent e) {
                Log.e(TAG, "手势-->onSingleTapUp");
                return false;
            }

            @Override
            public boolean onScroll (MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.e(TAG, "手势-->onScroll");
                return false;
            }

            @Override
            public void onLongPress (MotionEvent e) {
                Log.e(TAG, "手势-->onLongPress");
            }

            @Override
            public boolean onFling (MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.e(TAG, "手势-->onFling");
                return false;
            }
        });

        //1.2将需要监听手势的View的onTouch时间交给GestureDetector处理
        findViewById(R.id.button).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                return mGestureDetector.onTouchEvent(event);
            }
        });
    }
}

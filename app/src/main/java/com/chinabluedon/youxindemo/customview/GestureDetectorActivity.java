package com.chinabluedon.youxindemo.customview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.chinabluedon.youxindemo.R;


/**
 * @author 胡腾
 * @time 2017/8/29  23:16
 * @desc ${TODD}
 */
public class GestureDetectorActivity extends Activity {

    private static final String TAG = "GestureDetectorActivity";
    private GestureDetector mMGestureDetector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMGestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {//触摸屏幕回调该方法
                Log.e(TAG,"手势监听-->onDown");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                Log.e(TAG,"手势监听-->onShowPress");
            }

            //监听单击
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.e(TAG,"手势监听-->onSingleTapUp");
                return false;
            }

            //监听滑动
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.e(TAG,"手势监听-->onScroll");
                return false;
            }

            //监听长按
            @Override
            public void onLongPress(MotionEvent e) {
                Log.e(TAG,"手势监听-->onLongPress");
            }

            //监听手指在屏幕拖动
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.e(TAG,"手势监听-->onFling");
                return false;
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将onTouchEvent交给手势监听器处理
        return mMGestureDetector.onTouchEvent(event);
    }
}

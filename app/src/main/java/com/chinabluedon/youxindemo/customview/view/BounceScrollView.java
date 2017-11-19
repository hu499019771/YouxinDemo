package com.chinabluedon.youxindemo.customview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * @author ht
 * @time 2017/9/11  17:35
 * @desc ${TODD}
 */
public class BounceScrollView extends LinearLayout {

    private final static String TAG = "BounceScrollView";

    //新建手势识别器
    //除了down事件,其余事件通过手势识别器来控制
    //手势识别器的onScroll回到中调用View滑动方法来进行移动
    //手势识别器中的up动作识别方法中,进行view的复位

    private Scroller mScroller;
    private final GestureDetector mGestureDetector;
    private float totalY;

    public BounceScrollView (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        mGestureDetector = new GestureDetector(context, new GestureDetectorImpl());
        setClickable(true);
        setLongClickable(true);

    }

    @Override
    public boolean onTouchEvent (MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                try {
                    Thread.sleep(2000);
                    totalY = 0;
                    reset();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                break;
            default:
                mGestureDetector.onTouchEvent(e);
                break;
        }
        return super.onTouchEvent(e);
    }

    private void reset () {
        beginScroll(0 - mScroller.getFinalX(), 0 - mScroller.getFinalY());
    }

    public class GestureDetectorImpl implements GestureDetector.OnGestureListener {



        @Override
        public boolean onDown (MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress (MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp (MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll (MotionEvent e1, MotionEvent e2, float x, float y) {
            Log.e(TAG, y + "");
            totalY += y;
            if (totalY < 0 && Math.abs(totalY) < 500) {
                beginScroll(0, (int) (y / 2));
            }
            return false;
        }

        @Override
        public void onLongPress (MotionEvent e) {

        }

        @Override
        public boolean onFling (MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

    @Override
    public void computeScroll () {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    /**
     * 滑动View
     *
     * @param x 需要滑动的X轴距离
     * @param y 需要滑动的Y轴距离
     */
    private void beginScroll (int x, int y) {
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), x, y);
        invalidate();
    }


}

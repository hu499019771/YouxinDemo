package com.chinabluedon.www.youxindemo.customview.art.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * @author ht
 * @time 2017/9/22  10:51
 * @desc ${TODD}
 */
public class HorizontalScrollViewEx extends ViewGroup {

    private Scroller mScroller;
    private VelocityTracker mTracker;

    private int mChildCount;
    private int mChildIndex;
    private int mChildWidth;

    private int mLastX;
    private int mLastY;
    private int mInterceptX;
    private int mInterceptY;

    public HorizontalScrollViewEx (Context context) {
        super(context);
        init();
    }

    public HorizontalScrollViewEx (Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init () {
        if (mScroller == null) {
            mScroller = new Scroller(getContext());
            mTracker = VelocityTracker.obtain();
        }
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //onMeasure需要处理wrap_content的情况
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();
        mChildCount = childCount;
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width, height;
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else {
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                width = getChildAt(0).getMeasuredWidth() * childCount;
                height = getChildAt(0).getMeasuredHeight();
                setMeasuredDimension(width, height);
            } else if (widthMode == MeasureSpec.AT_MOST) {
                width = getChildAt(0).getMeasuredWidth() * childCount;
                setMeasuredDimension(width, heightSize);
            } else if (heightMode == MeasureSpec.AT_MOST) {
                height = getChildAt(0).getMeasuredHeight();
                setMeasuredDimension(widthSize, height);
            }
        }
    }

    @Override
    protected void onLayout (boolean changed, int l, int t, int r, int b) {
        //在onLayout中放置子View
        int childCount = getChildCount();

        int childLeft = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                mChildWidth = child.getMeasuredWidth();
                child.layout(childLeft, 0, childLeft + child.getMeasuredWidth(), child.getMeasuredHeight());
                childLeft += child.getMeasuredWidth();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent (MotionEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();

        boolean intercept = false;

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                //如果还在移动中,那么需要拦截此事件
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mInterceptX;
                int deltaY = y - mInterceptY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercept = true;
                } else {
                    intercept = false;
                }

                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
            default:
                break;
        }

        mLastX = x;
        mLastY = y;

        mInterceptX = x;
        mInterceptY = y;
        return intercept;
    }

    @Override
    public boolean onTouchEvent (MotionEvent e) {
        mTracker.addMovement(e);
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                scrollBy(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();

                mTracker.computeCurrentVelocity(1000);
                float xVelocity = mTracker.getXVelocity();
                if (Math.abs(xVelocity) > 50) {//当速度大于50时
                    mChildIndex = xVelocity > 0 ? mChildIndex - 1 : mChildIndex + 1;
                } else {//当速度小于50时
                    mChildIndex = (scrollX + mChildWidth / 2) / mChildWidth;
                }
                //确定滑到的页数
                mChildIndex = Math.max(0, Math.min(mChildIndex, mChildCount - 1));
                int distanceX = mChildIndex * mChildWidth - scrollX;
                smoothScroll(distanceX);
                mTracker.clear();
                break;
            default:
                break;
        }
        mLastX = x;
        return true;
    }

    private void smoothScroll (int dx) {
        mScroller.startScroll(getScrollX(), 0, dx, 0,500);
        invalidate();
    }


    @Override
    public void computeScroll () {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    protected void onDetachedFromWindow () {
        mTracker.recycle();
        super.onDetachedFromWindow();
    }
}

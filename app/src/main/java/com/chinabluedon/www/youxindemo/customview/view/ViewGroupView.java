package com.chinabluedon.www.youxindemo.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/**
 * @author ht
 * @time 2017/9/12  9:44
 * @desc ${TODD}
 */

public class ViewGroupView extends ViewGroup {
    //1.测量
    //2.摆放
    //3.处理事件拦截,当手势滑动有效的事件,由自己来消费事件
    //4.处理触摸事件,移动多少,就滑动多少,处理左边和右边越界
    //5.在moveup动作中,处理屏幕滑动距离是否超过屏幕一半决定显示在哪边

    private int leftLimit;//左边界限制的坐标
    private int rightLimit;//右边界限制的坐标
    private final int mScreenWidth;//屏幕宽度
    private final int mTouchSlop;//最短滑动距离
    private float startX;//起始滑动式X坐标
    private float totalX;

    public ViewGroupView (Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();

    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量完自己接着测量子View
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout (boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                int measuredWidth = child.getMeasuredWidth();
                int measuredHeight = child.getMeasuredHeight();
                child.layout(measuredWidth * i, 0, measuredWidth * (i + 1), measuredHeight);
            }
            //得到左边和右边的界面限制
            leftLimit = getChildAt(0).getLeft();
            rightLimit = getChildAt(childCount - 1).getRight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent (MotionEvent e) {
        switch (e.getAction()) {

            case MotionEvent.ACTION_DOWN:
                startX = e.getX();
                totalX = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                //当手势移动达到要求时,自己处理拦截事件
                float currX = e.getX();
                totalX += (currX - startX);
                startX = currX;
                if (Math.abs(totalX) > mTouchSlop) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent (MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //手势移动多少,
                break;

            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(e);
    }
}

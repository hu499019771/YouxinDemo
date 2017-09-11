package com.chinabluedon.www.youxindemo.customview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * @author ht
 * @time 2017/9/11  15:31
 * @desc ${TODD}
 */
public class ScrollLinearLayout extends LinearLayout {

    private Scroller mScroller;
    private final static int DURATION = 10000;
    private int offsetY;
    private boolean flag = true;

    //    1.初始化Scroller
    //    2.调用startScroll()开始滚动
    //    3.执行invalidate()刷新界面
    //    4.重写View的computeScroll()并在其内部实现与滚动相关的业务逻辑
    //    5.再次执行invalidate()刷新界面

    public ScrollLinearLayout (Context context) {
        super(context);
    }

    public ScrollLinearLayout (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    //每次调用draw方法时,就会调用到计算滑动的这个方法
    @Override
    public void computeScroll () {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    public void beginScroll(){
        if (flag) {
            int startX = (int) getX();
            int startY = (int) getY();
            int dx = -400;
            int dy = 0;
            mScroller.startScroll(startX, startY, dx, dy, DURATION);
            flag = false;
        } else {
            int startX = mScroller.getCurrX();
            int startY = mScroller.getCurrY();
            int dx = 400;
            int dy = 0;
            mScroller.startScroll(startX, startY, dx, dy, DURATION);
            flag = true;
        }
        invalidate();
    }
}

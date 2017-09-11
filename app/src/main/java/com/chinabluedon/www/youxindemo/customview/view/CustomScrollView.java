package com.chinabluedon.www.youxindemo.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @author ht
 * @time 2017/9/11  8:42
 * @desc ${TODD}
 */
public class CustomScrollView extends ScrollView {

    private float mStartX;
    private float mStartY;
    private float mDistanceX;
    private float mDistanceY;

    public CustomScrollView (Context context) {
        super(context);
    }

    public CustomScrollView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent (MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                //获取按下时坐标
                mStartX = e.getX();
                mStartY = e.getY();

                break;
            case MotionEvent.ACTION_MOVE:

                //获取终点坐标
                float endX = e.getX();
                float endY = e.getY();

                //计算X和Y方向距离
                mDistanceX += Math.abs(mStartX - endX);
                mDistanceY += Math.abs(mStartY - endY);

                //设定终点坐标为下次开始坐标
                mStartX = endX;
                mStartY = endY;

                //对水平方向的move事件不拦截
                if (mDistanceX - mDistanceY < 0) {
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onInterceptTouchEvent(e);
    }
}

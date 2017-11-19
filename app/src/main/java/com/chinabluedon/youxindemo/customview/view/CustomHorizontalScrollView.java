package com.chinabluedon.youxindemo.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * @author ht
 * @time 2017/9/11  9:02
 * @desc ${TODD}
 */
public class CustomHorizontalScrollView extends HorizontalScrollView {

    private float mStartX;
    private float mStartY;

    private float mDistanceX;
    private float mDistanceY;

    public CustomHorizontalScrollView (Context context) {
        super(context);
    }

    public CustomHorizontalScrollView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent (MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                mStartX = e.getX();
                mStartY = e.getY();
                break;

            case MotionEvent.ACTION_MOVE:

                float endX = e.getX();
                float endY = e.getY();

                mDistanceX += Math.abs(endX - mStartX);
                mDistanceY += Math.abs(endY - mStartY);

                mStartX = endX;
                mStartY = endY;

                if (mDistanceX - mDistanceY > 0) {
                    return false;
                }

                break;

            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onInterceptTouchEvent(e);
    }
}

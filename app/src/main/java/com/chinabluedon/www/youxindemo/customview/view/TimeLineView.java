package com.chinabluedon.www.youxindemo.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.chinabluedon.www.youxindemo.R;
import com.chinabluedon.www.youxindemo.utils.ResourceUtils;

/**
 * @author ht
 * @time 2017/9/19  10:53
 * @desc 时间轴自定义控件
 */
public class TimeLineView extends View {

    private int d = 24;//圆形图标的直径
    private Drawable mCircleBac;
    private int mLineSize = 2;
    private Drawable mStartLineBac;
    private Drawable mEndLineBac;
    private boolean mOrientation;//是否横向,false为纵向(默认),true为横向

    //1.获取自定义属性
    //1.1有以下属性:圆形的背景图或颜色/圆形大小/上面(左边线条)的粗度/下面(右边线条)的粗度/两条线条颜色或者背景图/方向
    //2.处理当layoutParams为wrap_content时的测量宽高
    //3.在onSizeChanged中确定两条直线和一个圆圈的绘制的矩形位置
    //4.在onDraw方法中绘制三个drawable
    //5.提供对外设置的自定义属性的方法

    public TimeLineView (Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init (Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TimeLineView);

        d = a.getDimensionPixelSize(R.styleable.TimeLineView_d, d);

        mCircleBac = a.getDrawable(R.styleable.TimeLineView_circleBac);

        mLineSize = a.getDimensionPixelSize(R.styleable.TimeLineView_lineSize, mLineSize);

        mStartLineBac = a.getDrawable(R.styleable.TimeLineView_startLineBac);

        mEndLineBac = a.getDrawable(R.styleable.TimeLineView_endLineBac);

        mOrientation = a.getBoolean(R.styleable.TimeLineView_orientation, mOrientation);

        if (mCircleBac != null) {
            mCircleBac.setCallback(this);
        }
        if (mStartLineBac != null) {
            mStartLineBac.setCallback(this);
        }
        if (mEndLineBac != null) {
            mEndLineBac.setCallback(this);
        }
        a.recycle();
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            if (mOrientation) {//竖向
                widthSize = 80;
                heightSize = 120;
            } else {//横向
                widthSize = 120;
                heightSize = 80;
            }
            setMeasuredDimension(widthSize, heightSize);
        } else if (widthMode != MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            if (mOrientation) {//竖向
                heightSize = 120;
            } else {//横向
                heightSize = 80;
            }
            setMeasuredDimension(widthSize, heightSize);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode != MeasureSpec.AT_MOST) {
            if (mOrientation) {//竖向
                widthSize = 80;
            } else {//横向
                widthSize = 120;
            }
            setMeasuredDimension(widthSize, heightSize);
        }

    }

    @Override
    protected void onSizeChanged (int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initDrawablePosition();
    }

    /**
     * 初始化三个drawable的位置
     */
    private void initDrawablePosition () {

        //1.获取四个方向的padding和宽高
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int width = getWidth();
        int height = getHeight();

        //2.计算出宽和高方向可用空间
        int widthCanUse = width - paddingLeft - paddingRight;
        int heightCanUse = height - paddingTop - paddingBottom;

        int x, y, r;//圆心坐标及半径

        Rect rect = new Rect();

        d = ResourceUtils.dp2px(getContext(), d);
        mLineSize = ResourceUtils.dp2px(getContext(), mLineSize);

        mLineSize = Math.min(widthCanUse, mLineSize);

        //3.分别考虑竖向和横向两种情况
        if (mOrientation) {//横向
            Rect circleRect = new Rect();
            d = Math.min(heightCanUse, d);
            r = d / 2;

            x = width / 2 + paddingLeft;
            y = paddingTop + r;

            if (mCircleBac != null) {
                circleRect.set(x - r, paddingTop, x + r, paddingTop + d);
            } else {
                circleRect.set(x, y, x, y);
            }
            mCircleBac.setBounds(circleRect);

            if (mStartLineBac != null) {
                rect.set(0, y - mLineSize / 2, circleRect.left, y + mLineSize / 2);
                mStartLineBac.setBounds(rect);
            }

            if (mEndLineBac != null) {
                rect.set(circleRect.right, y - mLineSize / 2, width, y + mLineSize / 2);
                mEndLineBac.setBounds(rect);
            }

        } else {//竖向
            //4.竖向
            //4.1确定圆形的绘制矩形范围
            Rect circleRect = new Rect();
            d = Math.min(widthCanUse, d);
            r = d / 2;

            x = paddingLeft + r;
            y = paddingTop + height / 2;

            if (mCircleBac != null) {
                circleRect.set(paddingLeft, y - r, paddingLeft + d, y + r);
            } else {
                circleRect.set(x, y, x, y);
            }
            mCircleBac.setBounds(circleRect);

            //4.2确定上面直线的绘制矩形
            if (mStartLineBac != null) {
                rect.set(x - mLineSize / 2, 0, x + mLineSize / 2, circleRect.top);
                mStartLineBac.setBounds(rect);
            }

            //4.3确定下面直线的绘制矩形
            if (mEndLineBac != null) {
                rect.set(x - mLineSize / 2, circleRect.bottom, x + mLineSize / 2, height);
                mEndLineBac.setBounds(rect);
            }
        }

    }

    @Override
    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        if (mCircleBac != null) {
            mCircleBac.draw(canvas);
        }
        if (mStartLineBac != null) {
            mStartLineBac.draw(canvas);
        }
        if (mEndLineBac != null) {
            mEndLineBac.draw(canvas);
        }
    }

    public void setDiameter (int d) {
        if (this.d != d) {
            this.d = d;
            initDrawablePosition();
            invalidate();
        }
    }

    public void setLineSize (int lineSize) {
        if (mLineSize != lineSize) {
            mLineSize = lineSize;
            initDrawablePosition();
            invalidate();
        }
    }

    public void setOrientation (boolean orientation) {
        if (mOrientation != orientation) {
            mOrientation = orientation;
            initDrawablePosition();
            invalidate();
        }
    }

    public void setCircleBac (Drawable circleBac) {
        if (mCircleBac != circleBac && circleBac != null) {
            mCircleBac = circleBac;
            mCircleBac.setCallback(this);
            initDrawablePosition();
            invalidate();
        }
    }

    public void setStartLineBac (Drawable startLineBac) {
        if (mStartLineBac != startLineBac && startLineBac != null) {
            mStartLineBac = startLineBac;
            mStartLineBac.setCallback(this);
            initDrawablePosition();
            invalidate();
        }
    }

    public void setEndLineBac (Drawable endLineBac) {
        if (mEndLineBac != endLineBac && endLineBac != null) {
            mEndLineBac = endLineBac;
            mEndLineBac.setCallback(this);
            initDrawablePosition();
            invalidate();
        }
    }


}

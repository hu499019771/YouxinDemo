package com.chinabluedon.youxindemo.customview.art.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.chinabluedon.youxindemo.R;
import com.chinabluedon.youxindemo.utils.ResourceUtils;


/**
 * @author ht
 * @time 2017/9/22  10:19
 * @desc ${TODD}
 */
public class CircleView extends View {

    private Paint mPaint;
    private final int mColor;

    public CircleView (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = a.getColor(R.styleable.CircleView_color, Color.RED);
        a.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //继承自View或ViewGroup的控件,需要处理warp_content的情况
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(ResourceUtils.dp2px(getContext(), 100), ResourceUtils.dp2px(getContext(), 100));
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(ResourceUtils.dp2px(getContext(), 100), heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, ResourceUtils.dp2px(getContext(), 100));
        }
    }

    @Override
    protected void onDraw (Canvas canvas) {
        //需要考虑到padding的情况,padding不影响整个view的大小,但是会影响到View内容的排放
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int width = getWidth();
        int height = getHeight();
        int widthCanUse = width - paddingLeft - paddingRight;
        int heightCanUse = height - paddingTop - paddingBottom;
        int radius = Math.min(widthCanUse, heightCanUse) / 2;
        int x = paddingLeft + radius;
        int y = paddingTop + radius;
        canvas.drawCircle(x, y, radius, mPaint);
    }
}

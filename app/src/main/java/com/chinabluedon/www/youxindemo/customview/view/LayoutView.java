package com.chinabluedon.www.youxindemo.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author ht
 * @time 2017/9/7  20:28
 * @desc ${TODD}
 */
public class LayoutView extends ViewGroup {

    public LayoutView (Context context) {
        super(context);
    }

    public LayoutView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {

        //获取测量规格中的宽度,为view的最终宽度
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int heithSize = paddingTop + paddingBottom;

        int maxHeightThisLine = 0;//当前行的高度
        int widthThisLineUsed = paddingLeft + paddingRight;//当前行已经被使用了的宽度

        //遍历子View
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            //测量子View的高度
            child.measure(widthMeasureSpec, heightMeasureSpec);

            //获取子View可以使用的宽度和高度
            int childSpecWidth = child.getMeasuredWidth();
            int childSpecHeight = child.getMeasuredHeight();

            //获取子View的margin,用来计算子View使用了的控件
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int childWidthUsed = params.leftMargin + params.rightMargin + childSpecWidth;
            int childHeightUsed = params.topMargin + params.bottomMargin + childSpecHeight;

            if (widthThisLineUsed + childWidthUsed < widthSpecSize) {

                //1.不换行时
                //1.1累加每一行已经可以使用的宽度
                widthThisLineUsed += childWidthUsed;

                //1.2通过一个变量将每一行的最大高度存起来
                if (maxHeightThisLine < childHeightUsed) {
                    maxHeightThisLine = childHeightUsed;
                }

            } else {

                //2.换行时
                //2.1重置每一行已经使用了的宽度
                widthThisLineUsed = paddingLeft + paddingRight + childWidthUsed;

                //2.2累加view的高度
                heithSize += maxHeightThisLine + 20;

                //2.3重置每行的最大高度
                maxHeightThisLine = childHeightUsed;
            }

        }

        //最后一行不换行,需要再加上最后一行的高度
        heithSize += maxHeightThisLine;
        setMeasuredDimension(widthSpecSize, heithSize);


    }

    @Override
    protected void onLayout (boolean changed, int l, int t, int r, int b) {

    }
}

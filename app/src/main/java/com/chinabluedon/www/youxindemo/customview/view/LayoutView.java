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

    private final static int SPACE = 20;

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

        //记录最后一次遍历的子View是否换行
        boolean isChangeLine = false;

        //遍历子View
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            //测量子View的高度
//            child.measure(widthMeasureSpec, heightMeasureSpec);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);

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
                heithSize += maxHeightThisLine + SPACE;

                //2.3重置每行的最大高度
                maxHeightThisLine = childHeightUsed;
            }

        }

        //最后一个View不换行时,需要再加上最后一行的高度
        heithSize += maxHeightThisLine;
        setMeasuredDimension(widthSpecSize, heithSize);


    }

    @Override
    protected void onLayout (boolean changed, int l, int t, int r, int b) {

        //获取parent 的padding大小
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        //遍历的这一行使用的宽度,用来判断child是否需要换行
        int widthUsed = paddingLeft + paddingRight;

        //记录当前行的x/y起始坐标
        int startX = paddingLeft;
        int startY = paddingTop;

        int left = 0, top = 0, right = 0, bottom = 0;

        //一行中的最大高度
        int maxHeightThisLine = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            int childMeasureWidth = child.getMeasuredWidth();
            int childMeasureHeight = child.getMeasuredHeight();

            //获取child的4个方向的margin
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int childMarginLeft = params.leftMargin;
            int childMarginTop = params.topMargin;
            int childMarginRight = params.rightMargin;
            int childMarginBottom = params.bottomMargin;

            int childWidthUsed = childMeasureWidth + childMarginLeft + childMarginRight;
            int childHeightUsed = childMeasureHeight + childMarginTop + childMarginBottom;

            if (widthUsed + childWidthUsed < r - l) {
                widthUsed += childWidthUsed;

                //不需要换行情况
                //摆放子View
                left = startX + childMarginLeft;
                top = startY + childMarginTop;
                right = left + childMeasureWidth;
                bottom = top + childMeasureHeight;

                //记录当前的开始X坐标
                startX += childWidthUsed;

                if (maxHeightThisLine < childHeightUsed) {
                    maxHeightThisLine = childHeightUsed;
                }
            } else {

                //需要换行的情况
                //重置X坐标的开始坐标
                startX = paddingLeft;

                //重置Y轴开始坐标
                startY += maxHeightThisLine + SPACE;

                //确定child位置
                left = startX + childMarginLeft;
                top = startY + childMarginTop;
                right = left + childMeasureWidth;
                bottom = top + childMeasureHeight;

                //重置当行已经被使用的高度为parent的paddingleft+childWidthUsed
                widthUsed = paddingLeft + childWidthUsed + paddingRight;

                //累加X轴坐标
                startX += childWidthUsed;

                //重置当行最大高度
                maxHeightThisLine = childHeightUsed;


            }
            child.layout(left, top, right, bottom);
        }
    }
}

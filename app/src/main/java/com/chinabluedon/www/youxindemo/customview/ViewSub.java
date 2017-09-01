package com.chinabluedon.www.youxindemo.customview;

import android.content.Context;
import android.view.View;

/**
 * @author ht
 * @time 2017/8/31  16:51
 * @desc ${TODD}
 */
public class ViewSub extends View {
    public ViewSub (Context context) {
        super(context);
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取父view给子View传递的测量规格
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //在宽和高为warp_content的时候,需要手动设置其款和高,不然默认为父布局的测量尺寸
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            widthSize = 100;
            heightSize = 100;
            //当宽为不确定时,需要手动确定宽度
        } else if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = 100;
            //当高为不确定时,需要手动确定高度
        } else if (heightSize == MeasureSpec.AT_MOST) {
            heightSize = 100;
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}

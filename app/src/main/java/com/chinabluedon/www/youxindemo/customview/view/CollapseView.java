package com.chinabluedon.www.youxindemo.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.chinabluedon.www.youxindemo.R;

/**
 * @author ht
 * @time 2017/9/6  17:20
 * @desc ${TODD}
 */
public class CollapseView extends LinearLayout {

    private Context mContext;

    public CollapseView (Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        LayoutInflater.from(mContext).inflate(R.layout.activity_collapse,this);
    }

    @Override
    protected void onLayout (boolean changed, int l, int t, int r, int b) {

    }

}

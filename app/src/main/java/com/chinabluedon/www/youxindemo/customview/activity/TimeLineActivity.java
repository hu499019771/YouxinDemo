package com.chinabluedon.www.youxindemo.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chinabluedon.www.youxindemo.R;

import java.math.BigDecimal;

/**
 * @author ht
 * @time 2017/9/19  10:54
 * @desc ${TODD}
 */
public class TimeLineActivity extends Activity {

    private View mView;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        mView = findViewById(R.id.timeLineView);
        double round = round(2.444446, 4);
        Log.e("TimeLineActivity",round+"");
    }

    @Override
    protected void onStart () {
        super.onStart();


    }

    @Override
    protected void onResume () {
        super.onResume();

    }

    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}

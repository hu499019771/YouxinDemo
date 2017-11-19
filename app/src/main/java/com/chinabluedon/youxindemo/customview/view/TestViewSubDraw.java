package com.chinabluedon.youxindemo.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author ht
 * @time 2017/9/5  14:54
 * @desc ${TODD}
 */
public class TestViewSubDraw extends View {

    private Paint mPaint;

    public TestViewSubDraw (Context context) {
        super(context);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public TestViewSubDraw (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw (Canvas canvas) {
        //绘制白色矩形
        mPaint.setColor(Color.WHITE);
        Rect rect = new Rect(100, 100, 500, 500);
        canvas.drawRect(rect, mPaint);
        mPaint.reset();

        //绘制直线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);
        canvas.drawLine(600,300,1000,300,mPaint);
        mPaint.reset();

        //绘制带边框的矩形
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(100,600,500,1000,mPaint);
        mPaint.reset();

        //绘制实心圆
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(15);
//        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(800,800,100,mPaint);
        mPaint.reset();

        //绘制椭圆
        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(15);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(100,100,500,300,mPaint);
        mPaint.reset();

        //绘制文字
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(60);
        canvas.drawText("我是文字",100,100,mPaint);
    }
}

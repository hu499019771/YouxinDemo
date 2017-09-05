package com.chinabluedon.www.youxindemo.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author ht
 * @time 2017/9/5  16:03
 * @desc ${TODD}
 */
public class TranslateView extends View {

    private Paint mPaint;

    public TranslateView (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setTextSize(40);
        mPaint.setColor(Color.RED);
        //画布移动前
        canvas.drawText("我是画布移动前文字",100,100,mPaint);
        //画布移动后
        canvas.translate(200,200);
        canvas.drawText("我是画布移动后文字",100,100,mPaint);
        //画布旋转后
//        canvas.rotate(30);
        canvas.drawText("我是画布旋转后文字",100,100,mPaint);

        //画布被剪裁后
        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(200,200,500,500,mPaint);
        canvas.clipRect(200,200,500,500);
        canvas.drawLine(100,100,600,600,mPaint);


        //建立蒙版
        canvas.save();
        canvas.drawRect(300,300,600,600,mPaint);

        //合并蒙版
        canvas.restore();
        mPaint.reset();
        mPaint.setTextSize(20);
        mPaint.setColor(Color.GREEN);
        canvas.drawText("我是蒙版合并后文字",300,400,mPaint);
    }
}

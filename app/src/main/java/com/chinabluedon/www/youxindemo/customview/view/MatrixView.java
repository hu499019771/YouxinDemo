package com.chinabluedon.www.youxindemo.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.chinabluedon.www.youxindemo.R;

/**
 * @author ht
 * @time 2017/9/6  17:03
 * @desc ${TODD}
 */
public class MatrixView extends View {

    private Paint mPaint;

    public MatrixView (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.music);
        Matrix matrix = new Matrix();
        //矩阵是对bitmap每个像素点进行了操作
        //平移
        matrix.setTranslate(200, 200);
        //旋转
        matrix.postRotate(30);
        //缩放
        //        matrix.setScale(2, 2);
        canvas.drawBitmap(bitmap, matrix, mPaint);

    }
}

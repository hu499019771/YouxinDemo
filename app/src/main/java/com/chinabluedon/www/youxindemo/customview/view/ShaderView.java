package com.chinabluedon.www.youxindemo.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.chinabluedon.www.youxindemo.R;

/**
 * @author ht
 * @time 2017/9/6  16:42
 * @desc ${TODD}
 */
public class ShaderView extends View {

    private Paint mPaint;

    public ShaderView (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw (Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.music);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        int radius = width / 2;
        mPaint.setShader(shader);
        canvas.translate(radius,radius);
        canvas.drawCircle(radius,radius,radius,mPaint);
    }
}

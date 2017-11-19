package com.chinabluedon.youxindemo.customview.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chinabluedon.youxindemo.R;


/**
 * @author ht
 * @time 2017/9/5  14:46
 * @desc ${TODD}
 */
public class TestDrawBitmapActivity extends Activity {

    private ImageView mImageView;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawbitmap);
        mImageView= (ImageView) findViewById(R.id.imageView);
        drawBitmap();
    }

    /**
     * 通过bitmap来画文字
     */
    private void drawBitmap () {
        //创建bitmap
        Bitmap bitmap = Bitmap.createBitmap(800, 400, Bitmap.Config.ARGB_8888);
        //创建paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setTextSize(60);
        //创建canvas
        Canvas canvas = new Canvas(bitmap);
        //画图
        canvas.drawColor(Color.GREEN);
        canvas.drawText("我是被绘制的文字",100,200,paint);
        mImageView.setImageBitmap(bitmap);
    }
}

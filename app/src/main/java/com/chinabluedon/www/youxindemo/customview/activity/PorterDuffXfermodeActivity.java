package com.chinabluedon.www.youxindemo.customview.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chinabluedon.www.youxindemo.R;

/**
 * @author ht
 * @time 2017/9/5  17:05
 * @desc ${TODD}
 */
public class PorterDuffXfermodeActivity extends Activity {

    private ImageView mImageView;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        mImageView= (ImageView) findViewById(R.id.imageView);
        mImageView.setImageBitmap(getRadiusBitmap());
    }

    private Bitmap getRadiusBitmap () {
        //获取图片资源为bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.radus);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(canvasBitmap);
        //画带圆角矩形
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(0);
        Rect rect = new Rect(0, 0, width, height);
        RectF rectF =new RectF(rect);
        canvas.drawRoundRect(rectF,100,100,paint);
        //设置模式
        PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        paint.setXfermode(xfermode);
        //画图片
        canvas.drawBitmap(bitmap,rect,rect,paint);
        return canvasBitmap;
    }


}

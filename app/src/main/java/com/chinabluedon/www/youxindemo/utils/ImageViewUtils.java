package com.chinabluedon.www.youxindemo.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author ht
 * @time 2017/9/27  17:40
 * @desc ${TODD}
 */
public class ImageViewUtils {

    public static Bitmap decodeSampleBitmapFromResource (Resources res, int resIs, int reqWidth, int reqHeight) {

        //获取bitmap资源
        BitmapFactory.Options options = new BitmapFactory.Options();

        //开始只采集图片的宽高信息,不加载图片,轻量级操作
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resIs, options);

        //计算采集率的大小
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        //关闭只采集图片宽高信息
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, resIs, options);

    }

    private static int calculateInSampleSize (BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth || height > reqHeight) {
            int halfWidth = width / 2;
            int halfHeight = height / 2;
            while ((halfWidth / inSampleSize) > reqWidth && (halfHeight / inSampleSize) > reqHeight) {
                inSampleSize = inSampleSize * 2;
            }
        }
        return inSampleSize;
    }
}

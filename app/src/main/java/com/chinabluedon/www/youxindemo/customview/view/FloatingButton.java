package com.chinabluedon.www.youxindemo.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinabluedon.www.youxindemo.utils.MeasureUtil;
import com.chinabluedon.www.youxindemo.R;

/**
 * @author ht
 * @time 2017/9/18  9:06
 * @desc ${TODD}
 */
public class FloatingButton extends ViewGroup {

    private static final int IS_SLIDE_DECREASE = 1;
    private static final int IS_SLIDE_INCREASE = 2;
    private int width;
    private int height;

    private int center;//圆的半径
    private int x;//矩形右边的x轴坐标(可变)
    private double maxX;//矩形右边的最大x轴坐标

    private Paint mPaint;
    private boolean isIncrease;//是否为增长状态

    private int circleBeginY = 30;//小圆Y轴初始半径
    private int y;//小圆x轴增量坐标

    private String text = "地铁/景区/商圈/城市";//textView中的文字

    private int mChildWidth;//textView的测量宽度
    private int mChildHeight;//textView的测量高度

    private double d_x;//行程比例

    private float degree;

    private View mChild;
    private int tx;
    private Bitmap openIcon;
    private Bitmap closeIcon;
    private int iconWidth;
    private float mTextSize;
    private float speed;
    private int backColor;
    private int textColor;
    private int innerCircleColor;

    private FoldListener mListener;

    private ClickListener mClickListener;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            d_x = x / maxX;
            switch (msg.what) {
                case IS_SLIDE_DECREASE:
                    x -= speed;
                    if (x > center) {

                        //不是最后一次缩短
                        y = (int) ((center - circleBeginY) * (1 - d_x));
                        tx = (int) (center * 2 + 5 + mChildWidth * d_x);
                        degree = (int) (-180 * (1 - d_x));
                        mHandler.sendEmptyMessageDelayed(IS_SLIDE_DECREASE, 1);
                    } else {

                        //已经到了最后一次缩短
                        x = center;
                        y = center - circleBeginY;
                        tx = center * 2 + 5;
                        degree = -180;
                        setEnabled(true);
                        if (mListener != null) {
                            mListener.onFold(isIncrease, FloatingButton.this);
                        }
                    }
                    break;
                case IS_SLIDE_INCREASE:
                    x += speed;
                    if (x < maxX) {

                        //不是最后一次变长
                        y = (int) ((center - circleBeginY) * (1 - d_x));
                        tx = (int) (center * 2 + 5 + mChildWidth * d_x);
                        degree = (int) (-180 * (1 - d_x));
                        mHandler.sendEmptyMessageDelayed(IS_SLIDE_INCREASE, 1);
                    } else {
                        degree = 0;
                        //最后一次变长
                        x = (int) maxX;
                        y = 0;
                        tx = center * 2 + 10 + mChildWidth;
                        setEnabled(true);
                        if (mListener != null) {
                            mListener.onFold(isIncrease, FloatingButton.this);
                        }
                    }
                    break;
            }
            requestLayout();
            invalidate();
        }
    };


    public FloatingButton(Context context) {
        super(context);
    }

    public FloatingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void setFolderListener(FoldListener listener) {
        mListener = listener;
    }

    public void setClickListener(ClickListener clickListener) {
        mClickListener = clickListener;
    }

    private void init(Context context, AttributeSet attrs) {

        //获取自定义属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FloatingButton);
        mTextSize = a.getFloat(R.styleable.FloatingButton_text_size, 20);
        speed = a.getFloat(R.styleable.FloatingButton_speed, 80);
        backColor = a.getColor(R.styleable.FloatingButton_bac_color, Color.BLACK);
        textColor = a.getColor(R.styleable.FloatingButton_text_color, Color.BLACK);
        innerCircleColor = a.getColor(R.styleable.FloatingButton_inner_circle_color, Color.BLACK);
        text = a.getString(R.styleable.FloatingButton_text);
        BitmapDrawable dra1 = (BitmapDrawable) a.getDrawable(R.styleable.FloatingButton_open_icon);
        BitmapDrawable dra2 = (BitmapDrawable) a.getDrawable(R.styleable.FloatingButton_close_icon);
        degree = a.getFloat(R.styleable.FloatingButton_degrees, 90);

        //获取两个图片的bitmap
        openIcon = dra1 != null ? dra1.getBitmap() : BitmapFactory.decodeResource(getResources(), R.mipmap.icon);
        closeIcon = dra2 != null ? dra2.getBitmap() : BitmapFactory.decodeResource(getResources(), R.mipmap.icon_2);
        a.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);

        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextSize(mTextSize);
        textView.setTextColor(textColor);
        addView(textView);

        iconWidth = circleBeginY - 5;
        openIcon = MeasureUtil.zoomImg(openIcon, iconWidth, iconWidth);
        closeIcon = MeasureUtil.zoomImg(closeIcon, iconWidth, iconWidth);

    }


    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        if (width == 0) {//加判断的目的是让括号内代码只执行一次
            width = MeasureSpec.getSize(widthSpec);
            height = MeasureSpec.getSize(heightSpec);

            center = height / 2;
            maxX = width - center;
            x = (int) maxX;

            //测量textView
            mChild = getChildAt(0);
            measureChild(mChild, widthSpec, heightSpec);
            mChildWidth = mChild.getMeasuredWidth();
            mChildHeight = mChild.getMeasuredHeight();
            tx = center * 2 + 10 + mChildWidth;
        }


        super.onMeasure(widthSpec, heightSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //textView的变长和变短通过修改其布局大小来控制
        mChild.layout(center * 2 + 5, center - mChildHeight / 2, tx, center + mChildHeight / 2);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        mPaint.setColor(backColor);
        //画左边圆
        canvas.drawCircle(center, center, center, mPaint);

        //画右边圆
        canvas.drawCircle(x, center, center, mPaint);

        //画矩形
        Rect rect = new Rect(center, 0, x, height);
        canvas.drawRect(rect, mPaint);

        mPaint.setColor(innerCircleColor);
        //画小圆
        canvas.drawCircle(center, center, y + circleBeginY, mPaint);

        //画bitmap
        canvas.save();
        //旋转画布
        canvas.rotate(degree, center, center);
        if (degree == -180) {
            canvas.drawBitmap(closeIcon, center - iconWidth / 2, center - iconWidth / 2, mPaint);
        } else {
            canvas.drawBitmap(openIcon, center - iconWidth / 2, center - iconWidth / 2, mPaint);
        }
        canvas.restore();

        super.dispatchDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (canClick(e.getX(), e.getY())) {
                    return true;
                } else {
                    return false;
                }
        }
        return super.onTouchEvent(e);
    }

    private boolean canClick(float x, float y) {
        if (isIncrease) {
            mClickListener.onClick(this);
            return true;
        }
        if (x <= center * 2 && y < center * 2 && mClickListener != null) {
            mClickListener.onClick(this);
            return true;
        }
        return false;
    }

    /**
     * 按钮收缩
     */
    private void startDecrease() {
        setEnabled(false);
        isIncrease = false;
        mHandler.sendEmptyMessageDelayed(IS_SLIDE_DECREASE, 40);
    }

    /**
     * 按钮变长
     */
    private void startIncrease() {
        setEnabled(false);
        isIncrease = true;
        mHandler.sendEmptyMessageDelayed(IS_SLIDE_INCREASE, 40);
    }

    /**
     * 外部调用
     */
    public void startScroll() {
        if (isIncrease) {

            //目前已经是伸展状态,需要缩回去
            startDecrease();
        } else {
            startIncrease();
        }
    }

    /**
     * 可折叠监听接口
     */
    public interface FoldListener {

        void onFold(boolean isIncrease, FloatingButton floatingButton);
    }

    /**
     * 点击事件的接口
     */
    public interface ClickListener {
        void onClick(FloatingButton floatingButton);
    }

}

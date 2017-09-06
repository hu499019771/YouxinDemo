package com.chinabluedon.www.youxindemo.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinabluedon.www.youxindemo.R;

/**
 * @author ht
 * @time 2017/9/6  17:20
 * @desc ${TODD}
 */
public class CollapseView extends LinearLayout {

    private Context mContext;
    private int parentWidthMeasureSpec;
    private int parentHeightMeasureSpec;
    private TextView mTvNumber;
    private TextView mTvContent;
    private ImageView mIvArrow;
    private RelativeLayout mRelativeLayout;
    private RelativeLayout mRlContainer;
    private final static int mDuration = 1000;
    private View mView;


    public CollapseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        //将局部绑定到LinearLayout上
        LayoutInflater.from(mContext).inflate(R.layout.collapse, this);

        initView();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentWidthMeasureSpec = widthMeasureSpec;
        parentHeightMeasureSpec = heightMeasureSpec;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void initView() {
        mTvNumber = (TextView) findViewById(R.id.tv_number);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mIvArrow = (ImageView) findViewById(R.id.iv_arrow);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relative_imageView);
        mRlContainer = (RelativeLayout) findViewById(R.id.rl_container);
        mRlContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateArrow();
            }


        });

        collapse();
    }

    private void rotateArrow() {
        if (mIvArrow.getTag() == null) {
            return;
        }
        if (mIvArrow.getTag().equals(true)) {
            //目前是展开情况
            mIvArrow.animate().setDuration(mDuration).rotation(0);
            collapse();
            mIvArrow.setTag(false);

        } else {
            //目前是缩起来情况
            mIvArrow.animate().setDuration(mDuration).rotation(180);
            extend();
            mIvArrow.setTag(true);
        }
    }

    private void collapse() {
        mRlContainer.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);
        final int measuredHeight = mRlContainer.getMeasuredHeight();

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    mRlContainer.setVisibility(GONE);
                } else {
                    mRlContainer.getLayoutParams().height = measuredHeight - (int) (measuredHeight * interpolatedTime);
                }
                requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(mDuration);
        mRlContainer.startAnimation(animation);

    }

    private void extend() {
        mRlContainer.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);
        final int measuredHeight = mRlContainer.getMeasuredHeight();

        mRlContainer.setVisibility(VISIBLE);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    mRlContainer.getLayoutParams().height = measuredHeight;
                } else {
                    mRlContainer.getLayoutParams().height = (int) (measuredHeight * interpolatedTime);
                }
                requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(mDuration);
        mRlContainer.startAnimation(animation);
    }

    public void addToContainer(int layoutResId) {
        mView = LayoutInflater.from(mContext).inflate(layoutResId, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mView.setLayoutParams(params);
        mRlContainer.addView(mView);
    }


}

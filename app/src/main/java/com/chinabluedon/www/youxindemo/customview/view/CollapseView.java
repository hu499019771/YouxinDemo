package com.chinabluedon.www.youxindemo.customview.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

    private final static String TAG="CollapseView";

    private Context mContext;
    private int parentWidthMeasureSpec;
    private int parentHeightMeasureSpec;
    private TextView mTvNumber;
    private TextView mTvContent;
    private ImageView mIvArrow;
    private RelativeLayout mRelativeLayout;
    private RelativeLayout mRlContainer;
    private final static int mDuration = 500;
    private View mView;

    public CollapseView (Context context) {
        this(context, null);
    }

    public CollapseView (Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        //将xml布局view绑定到LinearLayout上,并且保存xml根布局的layoutparams参数

        LayoutInflater.from(mContext).inflate(R.layout.collapse, this);

        initView();

    }

    public void setNumber (String number) {
        if (!TextUtils.isEmpty(number)) {
            mTvNumber.setText(number);
        }
    }

    public void setContent (String content) {
        if (!TextUtils.isEmpty(content)) {
            mTvContent.setText(content);
        }
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentWidthMeasureSpec = widthMeasureSpec;
        parentHeightMeasureSpec = heightMeasureSpec;
    }

    @Override
    protected void onLayout (boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private void initView () {
        mTvNumber = (TextView) findViewById(R.id.tv_number);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mIvArrow = (ImageView) findViewById(R.id.iv_arrow);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl_container);
        mRlContainer = (RelativeLayout) findViewById(R.id.relative_imageView);
        mRelativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick (View v) {
                rotateArrow();
            }


        });

        collapse();
    }

    private void rotateArrow () {
        if (mIvArrow.getTag() == null || mIvArrow.getTag().equals(false)) {

            //目前是缩起来情况
            mIvArrow.animate().setDuration(mDuration).rotation(-180);
            extend();
            mIvArrow.setTag(true);
        } else {
            //目前是展开情况
            mIvArrow.animate().setDuration(mDuration).rotation(0);
            collapse();
            mIvArrow.setTag(false);
        }
    }

    private void collapse () {
        final int measuredHeight = mRlContainer.getMeasuredHeight();

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation (float interpolatedTime, Transformation t) {
                Log.e(TAG,interpolatedTime+"");
                if (interpolatedTime == 1) {
                    mRlContainer.setVisibility(GONE);
                } else {
                    mRlContainer.getLayoutParams().height = measuredHeight - (int) (measuredHeight * interpolatedTime);
                    mRlContainer.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds () {
                return true;
            }
        };
        animation.setDuration(mDuration);
        mRlContainer.startAnimation(animation);

    }

    private void extend () {
        mRlContainer.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);
        final int measuredHeight = mRlContainer.getMeasuredHeight();

        mRlContainer.setVisibility(VISIBLE);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation (float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    mRlContainer.getLayoutParams().height = measuredHeight;
                } else {
                    mRlContainer.getLayoutParams().height = (int) (measuredHeight * interpolatedTime);
                }
                mRlContainer.requestLayout();
            }

            @Override
            public boolean willChangeBounds () {
                return true;
            }
        };
        animation.setDuration(mDuration);
        mRlContainer.startAnimation(animation);
    }

    public void addToContainer (int layoutResId) {
        mView = LayoutInflater.from(mContext).inflate(layoutResId, mRlContainer);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        mView.setLayoutParams(params);
//        mRlContainer.addView(mView);
    }


}

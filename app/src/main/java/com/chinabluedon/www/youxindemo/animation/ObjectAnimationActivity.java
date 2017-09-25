package com.chinabluedon.www.youxindemo.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chinabluedon.www.youxindemo.R;

/**
 * @author ht
 * @time 2017/9/25  11:08
 * @desc ${TODD}
 */
public class ObjectAnimationActivity extends Activity {

    private TextView mTextView;
    private int mHeight;
    private Button mButton;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);
        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                animatorSetFormXML();
            }
        });
        mTextView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange (View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                mHeight = mTextView.getHeight();
            }
        });
    }

    private void animatorSetFormXML () {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.property_animator1);
        set.setTarget(mButton);
        set.start();

    }

    private void animatorSet () {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(mTextView, "rotationX", 0, 360),
                ObjectAnimator.ofFloat(mTextView, "rotationY", 0, 180),
                ObjectAnimator.ofFloat(mTextView, "rotationY", 0, -90),
                ObjectAnimator.ofFloat(mTextView, "translationX", 0, 90),
                ObjectAnimator.ofFloat(mTextView, "translationY", 0, 90),
                ObjectAnimator.ofFloat(mTextView, "scaleX", 0, 1.5F),
                ObjectAnimator.ofFloat(mTextView, "scaleY", 0, 0.5f),
                ObjectAnimator.ofFloat(mTextView, "alpha", 1, 0.5f, 1)
        );
        set.setDuration(5000).start();
    }

    private void backgroundColor () {
        ObjectAnimator animator = ObjectAnimator.ofInt(mTextView, "backgroundColor", 0xFFFFFFFF, 0x00000000);
        animator.setDuration(2000);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
    }


}

package com.chinabluedon.youxindemo.animation;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chinabluedon.youxindemo.R;


/**
 * @author ht
 * @time 2017/9/27  11:25
 * @desc ${TODD}
 */
public class ButtonActivity extends Activity {

    private Button mButton;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {

            int startX = 0;
            int endX = 300;

            @Override
            public void onClick (View v) {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(startX, endX);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    private IntEvaluator mIntEvaluator = new IntEvaluator();

                    @Override
                    public void onAnimationUpdate (ValueAnimator animation) {
                        int animatedValue = (Integer) animation.getAnimatedValue();
                        Log.e("onAnimationUpdate", animatedValue + "");
                        float fraction = animation.getAnimatedFraction();
                        Integer evaluate = mIntEvaluator.evaluate(fraction, startX, endX);
                        mButton.getLayoutParams().width = animatedValue;
                        mButton.getLayoutParams().height = animatedValue;
                        mButton.requestLayout();
                    }
                });
                valueAnimator.setDuration(1000).start();
            }
        });
    }

    public static class ViewWarpper {

        private Button mButton;

        public ViewWarpper (Button button) {
            mButton = button;
        }

        public void setWidth (int width) {
            mButton.getLayoutParams().width = width;
            mButton.requestLayout();
        }

        public int getWidth () {
            return mButton.getLayoutParams().width;
        }

        public void setHeight (int height) {
            mButton.getLayoutParams().height = height;
            mButton.requestLayout();
        }

        public int getHeight () {
            return mButton.getLayoutParams().height;
        }
    }
}

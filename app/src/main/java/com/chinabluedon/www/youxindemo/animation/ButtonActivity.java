package com.chinabluedon.www.youxindemo.animation;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.chinabluedon.www.youxindemo.R;

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
            @Override
            public void onClick (View v) {
                ObjectAnimator.ofInt(new ViewWarpper(mButton), "width", 0,500).setDuration(2000).start();
                ObjectAnimator.ofInt(new ViewWarpper(mButton), "height", 0,500).setDuration(2000).start();
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

        public void setHeight(int height){
            mButton.getLayoutParams().height=height;
            mButton.requestLayout();
        }

        public int getHeight(){
            return mButton.getLayoutParams().height;
        }
    }
}

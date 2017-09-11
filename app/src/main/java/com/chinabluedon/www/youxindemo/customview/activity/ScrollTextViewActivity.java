package com.chinabluedon.www.youxindemo.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chinabluedon.www.youxindemo.R;

import static android.content.ContentValues.TAG;

/**
 * @author ht
 * @time 2017/9/11  14:36
 * @desc ${TODD}
 */
public class ScrollTextViewActivity extends Activity implements View.OnClickListener {

    private TextView mTextView;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_textview);
        mTextView = (TextView) findViewById(R.id.textview);
        findViewById(R.id.scrollTo).setOnClickListener(this);
        findViewById(R.id.scrollBy).setOnClickListener(this);
        findViewById(R.id.reset).setOnClickListener(this);
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.scrollBy:
                mTextView.scrollBy(30, 0);
                Log.e(TAG, "X轴的mScroll" + v.getScrollX() + "--" + v.getScrollY() + "Y轴的mScroll");
                break;
            case R.id.scrollTo:
                mTextView.scrollTo(300, 0);
                break;
            case R.id.reset:
                mTextView.scrollTo(0, 0);
                break;
        }
    }
}

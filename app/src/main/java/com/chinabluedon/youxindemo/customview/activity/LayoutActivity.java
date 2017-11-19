package com.chinabluedon.youxindemo.customview.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinabluedon.youxindemo.R;


/**
 * @author ht
 * @time 2017/9/7  20:27
 * @desc ${TODD}
 */
public class LayoutActivity extends Activity {

    private com.chinabluedon.youxindemo.customview.view.LayoutView mLayoutview;
    private String[] texts = new String[]{"聪明伶俐", "可爱至极", "眼观六路", "耳听八方", "风度翩翩", "双瞳剪水"};

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_layout);
        mLayoutview = (com.chinabluedon.youxindemo.customview.view.LayoutView) findViewById(R.id.layoutView);
        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                addTextView();
            }

        });
    }

    private void addTextView () {
        TextView textView = new TextView(this);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(15, 15, 15, 15);
        textView.setLayoutParams(params);
        textView.setPadding(10, 10, 10, 10);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundResource(R.drawable.background_text);
        textView.setTextColor(Color.parseColor("#666666"));
        textView.setText(texts[(int) (texts.length * Math.random())]);
        mLayoutview.addView(textView, params);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                return false;
            }
        });
    }
}

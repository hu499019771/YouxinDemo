package com.chinabluedon.youxindemo.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.chinabluedon.youxindemo.R;


/**
 * @author ht
 * @time 2017/9/18  14:19
 * @desc ${TODD}
 */
public class FloatingActivity extends Activity {

    private com.chinabluedon.youxindemo.customview.view.FloatingButton mFloatingButton;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating);
        mFloatingButton = (com.chinabluedon.youxindemo.customview.view.FloatingButton) findViewById(R.id.floatingButton);
        mFloatingButton.setFolderListener(new com.chinabluedon.youxindemo.customview.view.FloatingButton.FoldListener() {
            @Override
            public void onFold(boolean isIncrease, com.chinabluedon.youxindemo.customview.view.FloatingButton floatingButton) {
                if(isIncrease) {
                    Toast.makeText(FloatingActivity.this, "展开", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(FloatingActivity.this, "缩起来", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mFloatingButton.setClickListener(new com.chinabluedon.youxindemo.customview.view.FloatingButton.ClickListener() {
            @Override
            public void onClick(com.chinabluedon.youxindemo.customview.view.FloatingButton floatingButton) {
                Toast.makeText(FloatingActivity.this, "我被点击了", Toast.LENGTH_SHORT).show();
                mFloatingButton.startScroll();
            }
        });
    }

}

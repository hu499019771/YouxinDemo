package com.chinabluedon.www.youxindemo.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.chinabluedon.www.youxindemo.R;
import com.chinabluedon.www.youxindemo.customview.view.FloatingButton;

/**
 * @author ht
 * @time 2017/9/18  14:19
 * @desc ${TODD}
 */
public class FloatingActivity extends Activity {

    private FloatingButton mFloatingButton;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating);
        mFloatingButton = (FloatingButton) findViewById(R.id.floatingButton);
        mFloatingButton.setFolderListener(new FloatingButton.FoldListener() {
            @Override
            public void onFold(boolean isIncrease, FloatingButton floatingButton) {
                if(isIncrease) {
                    Toast.makeText(FloatingActivity.this, "展开", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(FloatingActivity.this, "缩起来", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mFloatingButton.setClickListener(new FloatingButton.ClickListener() {
            @Override
            public void onClick(FloatingButton floatingButton) {
                Toast.makeText(FloatingActivity.this, "我被点击了", Toast.LENGTH_SHORT).show();
                mFloatingButton.startScroll();
            }
        });
    }

}

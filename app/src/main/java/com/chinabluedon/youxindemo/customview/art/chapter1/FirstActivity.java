package com.chinabluedon.youxindemo.customview.art.chapter1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chinabluedon.youxindemo.R;


/**
 * @author 胡腾
 * @time 2017/10/11  21:52
 * @desc ${TODD}
 */
public class FirstActivity extends Activity {

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.e(TAG, "taskid" + getTaskId());
    }

    public void click(View view) {
        startActivity(new Intent(this, com.chinabluedon.youxindemo.customview.art.chapter1.SecondActivity.class));
    }
}

package com.chinabluedon.youxindemo.customview.art.chapter1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chinabluedon.youxindemo.R;


/**
 * @author 胡腾
 * @time 2017/10/11  21:58
 * @desc ${TODD}
 */
public class SecondActivity extends Activity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.e(TAG, "taskid" + getTaskId());
    }
}

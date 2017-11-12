package com.chinabluedon.www.youxindemo.process;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chinabluedon.www.youxindemo.R;

/**
 * @author 胡腾
 * @time 2017/11/9  23:34
 * @desc ${TODD}
 */
public class TwoActivity extends Activity {

    private static final String TAG = "TwoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Log.i(TAG,"TwoActivity--userId--"+UserManager.userId);
    }

    public void click(View view) {
        startActivity(new Intent(this, ThreeActivity.class));
    }

}

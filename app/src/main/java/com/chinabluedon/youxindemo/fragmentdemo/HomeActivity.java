package com.chinabluedon.youxindemo.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.chinabluedon.youxindemo.R;


/**
 * @author ht
 * @time 2017/10/19  10:11
 * @desc ${TODD}
 */
public class HomeActivity extends AppCompatActivity implements com.chinabluedon.youxindemo.fragmentdemo.OneFragment.OneFOnClickListener,
        com.chinabluedon.youxindemo.fragmentdemo.TwoFragment.TwoFOnClickListener,
        com.chinabluedon.youxindemo.fragmentdemo.Threeragment.ThreeFOnClickListener {

    private FragmentTransaction mTransaction;
    private FragmentManager mFragmentManager;

    private Fragment mTwoFragment;
    private Fragment mThreeFragment;


    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mFragmentManager = getSupportFragmentManager();

        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.add(R.id.fl_content, new com.chinabluedon.youxindemo.fragmentdemo.OneFragment(), "one");
        mTransaction.commit();
    }

    @Override
    public void oneOnClick () {
        if (mTwoFragment == null) {
            mTwoFragment = new com.chinabluedon.youxindemo.fragmentdemo.TwoFragment();
        }
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.fl_content, new com.chinabluedon.youxindemo.fragmentdemo.TwoFragment(), "two");
        mTransaction.addToBackStack(null);
        mTransaction.commit();
    }

    @Override
    public void twoOnClick () {
        if (mThreeFragment == null) {
            mThreeFragment = new com.chinabluedon.youxindemo.fragmentdemo.Threeragment();
        }
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.hide(mTwoFragment);
        mTransaction.add(R.id.fl_content, mThreeFragment, "three");
        mTransaction.addToBackStack(null);
        mTransaction.commit();
    }

    @Override
    public void threeOnClick () {

    }
}

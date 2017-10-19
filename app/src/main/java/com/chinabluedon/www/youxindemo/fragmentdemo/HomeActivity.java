package com.chinabluedon.www.youxindemo.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.chinabluedon.www.youxindemo.R;

/**
 * @author ht
 * @time 2017/10/19  10:11
 * @desc ${TODD}
 */
public class HomeActivity extends AppCompatActivity implements OneFragment.OneFOnClickListener,
        TwoFragment.TwoFOnClickListener,
        Threeragment.ThreeFOnClickListener {

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
        mTransaction.add(R.id.fl_content, new OneFragment(), "one");
        mTransaction.commit();
    }

    @Override
    public void oneOnClick () {
        if (mTwoFragment == null) {
            mTwoFragment = new TwoFragment();
        }
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.fl_content, new TwoFragment(), "two");
        mTransaction.addToBackStack(null);
        mTransaction.commit();
    }

    @Override
    public void twoOnClick () {
        if (mThreeFragment == null) {
            mThreeFragment = new Threeragment();
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

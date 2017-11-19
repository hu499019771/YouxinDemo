package com.chinabluedon.youxindemo.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.chinabluedon.youxindemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ht
 * @time 2017/9/26  8:45
 * @desc ${TODD}
 */
public class ArcViewActivity extends Activity implements View.OnClickListener {

    private int[] ids = new int[]{R.id.imageViewA, R.id.imageViewB, R.id.imageViewC, R.id.imageViewD,
            R.id.imageViewE, R.id.imageViewF, R.id.imageViewG, R.id.imageViewH};

    private List<ImageView> mImageViews = new ArrayList<>();

    private boolean flag = true;

    private boolean isFinish = true;


    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcview);
        initUI();
    }

    private void initUI () {
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = (ImageView) findViewById(ids[i]);
            imageView.setOnClickListener(this);
            mImageViews.add(imageView);
        }
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.imageViewA:
                if (isFinish) {
                    if (flag) {
                        startAnimator();
                    } else {
                        closeAnimator();
                    }
                }

                break;
            default:
                Toast.makeText(ArcViewActivity.this, v.getId() + "", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void closeAnimator () {
        for (int i = 1; i < mImageViews.size(); i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mImageViews.get(i), "translationY", -i * 150f);
            animator.setDuration(300);
            animator.setStartDelay(i * 200);
            animator.setInterpolator(new BounceInterpolator());
            if (i == 1) {
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart (Animator animation) {
                        isFinish = false;
                    }
                });
            }
            if (i == mImageViews.size() - 1) {
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd (Animator animation) {
                        isFinish = true;
                        flag = true;
                    }
                });
            }
            animator.start();
        }

    }

    private void startAnimator () {
        for (int i = 1; i < mImageViews.size(); i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mImageViews.get(i), "translationY", i * 150f);
            animator.setDuration(300);
            animator.setStartDelay(i * 200);
            animator.setInterpolator(new BounceInterpolator());
            if (i == 1) {
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart (Animator animation) {
                        isFinish = false;
                    }
                });
            }
            if (i == mImageViews.size() - 1) {
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd (Animator animation) {
                        isFinish = true;
                        flag = false;
                    }
                });
            }
            animator.start();
        }

    }
}

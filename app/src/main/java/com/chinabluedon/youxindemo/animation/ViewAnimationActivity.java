package com.chinabluedon.youxindemo.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.chinabluedon.youxindemo.R;


/**
 * @author 胡腾
 * @time 2017/9/25  20:55
 * @desc ${TODD}
 */
public class ViewAnimationActivity extends Activity {

    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        mImageView= (ImageView) findViewById(R.id.imageView);
    }

    public void move(View view){
        Toast.makeText(ViewAnimationActivity.this, "我被点击了", Toast.LENGTH_SHORT).show();
    }

    public void click(View view){
        objectAnimatorSet();
    }

    private void objectAnimatorSet() {

        //1.x轴平移300
        ObjectAnimator a1 = ObjectAnimator.ofFloat(mImageView, "translationX", 300F);

        //2.y轴平移300
        ObjectAnimator a2 = ObjectAnimator.ofFloat(mImageView, "translationY", 300F);

        //3.旋转360度
        ObjectAnimator a3 = ObjectAnimator.ofFloat(mImageView, "rotation", 360);

        //4.组合动画
        AnimatorSet set = new AnimatorSet();
//        set.playSequentially(a1,a2,a3);
        set.play(a1).with(a2).before(a3);
        set.setDuration(1000).start();

    }

    private void viewAnimation() {
        TranslateAnimation ta = new TranslateAnimation(0, 300, 0, 0);
        ta.setDuration(1000);
        ta.setFillAfter(true);
        mImageView.startAnimation(ta);
    }
}

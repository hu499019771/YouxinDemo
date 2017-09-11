package com.chinabluedon.www.youxindemo.customview.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chinabluedon.www.youxindemo.R;

/**
 * @author ht
 * @time 2017/9/8  17:03
 * @desc ${TODD}
 */
public class ViewPagerActivity extends Activity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_viewpager);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new ViewPagerAdaper(this));
        /*mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                mViewPager.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });*/
    }

    public static class ViewPagerAdaper extends PagerAdapter {

        private Context mContext;
        private int[] pagesArray = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};

        public ViewPagerAdaper (Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem (View container, int position) {
            LinearLayout itemView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_imageview, (ViewGroup) container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(pagesArray[position]);
            ((ViewGroup) container).addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem (View container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public int getCount () {
            return pagesArray.length;
        }

        @Override
        public boolean isViewFromObject (View view, Object object) {
            return view == object;
        }
    }
}

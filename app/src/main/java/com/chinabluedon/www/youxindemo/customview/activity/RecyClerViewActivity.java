package com.chinabluedon.www.youxindemo.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinabluedon.www.youxindemo.R;

import java.util.ArrayList;

/**
 * @author ht
 * @time 2017/9/9  10:44
 * @desc ${TODD}
 */
public class RecyClerViewActivity extends Activity {

    private RecyclerView mRecyclerView;
    private ArrayList<ItemBean> mDatas = new ArrayList<>();

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new Adapter());
        /*mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                mRecyclerView.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });*/
    }


    private void initData () {

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                mDatas.add(new ItemBean("我是类型1", R.drawable.a, ItemBean.TYPE_DOUBLE));
            } else {
                mDatas.add(new ItemBean("我是类型2", R.drawable.b, ItemBean.TYPE_SINGLE));
            }
        }
    }

    public class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public int getItemViewType (int position) {
            return mDatas.get(position).getType();
        }

        @Override
        public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
            View view = null;
            if (viewType == ItemBean.TYPE_SINGLE) {
                view = LayoutInflater.from(RecyClerViewActivity.this).
                        inflate(R.layout.item_recyclerview, parent, false);
            } else if (viewType == ItemBean.TYPE_DOUBLE) {
                view = LayoutInflater.from(RecyClerViewActivity.this).
                        inflate(R.layout.item_double, parent, false);
            }
            return new ViewHolder(view,viewType);
        }

        @Override
        public void onBindViewHolder (ViewHolder holder, int position) {
            int type = mDatas.get(position).getType();
            switch (type) {
                case ItemBean.TYPE_SINGLE:
                    holder.textView.setText(mDatas.get(position).getTitle());
                    holder.imageView.setImageResource(mDatas.get(position).getImageViewId());
                    break;
                case ItemBean.TYPE_DOUBLE:
                    holder.imageViewOther.setImageResource(mDatas.get(position).getImageViewId());
                    break;
                default:
                    break;
            }

        }

        @Override
        public int getItemCount () {
            return mDatas.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private int type;

        private TextView textView;
        private ImageView imageView;
        private ImageView imageViewOther;

        public ViewHolder (View itemView, int type) {
            super(itemView);
            this.type = type;
            switch (type) {
                case ItemBean.TYPE_SINGLE:
                    textView = (TextView) itemView.findViewById(R.id.textView);
                    imageView = (ImageView) itemView.findViewById(R.id.imageView);
                    break;
                case ItemBean.TYPE_DOUBLE:
                    imageViewOther = (ImageView) itemView;
                    break;
                default:
                    break;
            }

        }
    }
}

package com.chinabluedon.youxindemo.customview.art.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chinabluedon.youxindemo.R;
import com.chinabluedon.youxindemo.utils.ResourceUtils;

import java.util.ArrayList;

/**
 * @author ht
 * @time 2017/9/22  10:51
 * @desc ${TODD}
 */
public class HorizontalScrollViewExActivity extends Activity{

    private static final String TAG = "DemoActivity_2";

    private com.chinabluedon.youxindemo.customview.art.ui.HorizontalScrollViewEx mListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scrollviewex);
        Log.d(TAG, "onCreate");
        initView();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        mListContainer = (com.chinabluedon.youxindemo.customview.art.ui.HorizontalScrollViewEx) findViewById(R.id.container);
        final int screenWidth = ResourceUtils.getScreenWidth(this);
        final int screenHeight = ResourceUtils.getScreenHeight(this);
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(
                    R.layout.content_layout, mListContainer, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = (TextView) layout.findViewById(R.id.title);
            textView.setText("page " + (i + 1));
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout);
            mListContainer.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListView listView = (ListView) layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.content_list_item, R.id.name, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(HorizontalScrollViewExActivity.this, "click item",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}

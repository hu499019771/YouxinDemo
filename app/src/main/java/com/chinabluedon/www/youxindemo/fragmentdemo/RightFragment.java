package com.chinabluedon.www.youxindemo.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinabluedon.www.youxindemo.R;

/**
 * @author ht
 * @time 2017/10/17  17:29
 * @desc ${TODD}
 */
public class RightFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right, container, false);
        return view;

    }
}

package com.chinabluedon.youxindemo.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinabluedon.youxindemo.R;


/**
 * @author ht
 * @time 2017/10/19  10:16
 * @desc ${TODD}
 */
public class TwoFragment extends Fragment implements View.OnClickListener {


    public interface TwoFOnClickListener {

        void twoOnClick ();
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        view.findViewById(R.id.bt_one).setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick (View v) {
        if (getActivity() instanceof TwoFOnClickListener) {
            ((TwoFOnClickListener)getActivity()).twoOnClick();
        }
    }
}

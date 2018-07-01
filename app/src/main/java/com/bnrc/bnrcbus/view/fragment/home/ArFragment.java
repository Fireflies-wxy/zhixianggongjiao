package com.bnrc.bnrcbus.view.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bnrc.bnrcbus.R;
import com.bnrc.bnrcbus.view.fragment.BaseFragment;

/**
 * Created by apple on 2018/5/24.
 */

public class ArFragment extends BaseFragment {

    private View mContentView;
    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_ar,container,false);
        initView();
        return mContentView;
    }

    public void initView(){
        mTextView = mContentView.findViewById(R.id.tv_ar);
        mTextView.setText("ar");
    }
}


package com.bnrc.bnrcbus.view.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bnrc.bnrcbus.R;
import com.bnrc.bnrcbus.view.fragment.BaseFragment;

/**
 * Created by apple on 2018/6/4.
 */

public class CollectFragment extends BaseFragment {

    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_collect,container,false);
        return mContentView;
    }

}

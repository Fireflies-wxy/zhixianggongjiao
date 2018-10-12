package com.bnrc.bnrcbus.view.fragment.buscircle;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bnrc.bnrcbus.R;
import com.bnrc.bnrcbus.view.fragment.BaseFragment;
import com.cb.ratingbar.CBRatingBar;

/**
 * Created by apple on 2018/5/24.
 */

public class BusCircleFragment extends BaseFragment {

    private View mContentView;
    private CBRatingBar cbRatingBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_buscircle,container,false);
        initView();
        return mContentView;
    }

    public void initView(){
//        cbRatingBar = mContentView.findViewById(R.id.cbratingbar);
//        cbRatingBar.setStarSize(100) //大小
//                .setStarCount(5) //数量
//                .setStarSpace(20) //间距
//                //.setShowStroke(true) //是否显示边框
//                //.setStarStrokeColor(Color.parseColor("#00ff00")) //边框颜色
//                .setStarStrokeWidth(5) //边框大小
////              .setStarFillColor(Color.parseColor("#00ff00")) //填充的背景颜色
////              .setStarCoverColor(Color.parseColor("#ffffff")) //填充的进度颜色
//                .setStarMaxProgress(100) //最大进度
//                .setStarProgress(50) //当前显示的进度
//                .setUseGradient(true) //是否使用渐变填充（如果使用则coverColor无效）
//                .setStartColor(Color.parseColor("#08af0c")) //渐变的起点颜色
//                .setEndColor(Color.parseColor("#af0813")) //渐变的终点颜色
//                .setCanTouch(true) //是否可以点击
//                .setPathData(getResources().getString(R.string.user_svg)) //传入path的数据
//                .setPathDataId(R.string.user_svg) //传入path数据id
//                .setCoverDir(CBRatingBar.CoverDir.leftToRight) //设置进度覆盖的方向
//                .setOnStarTouchListener(new CBRatingBar.OnStarTouchListener() { //点击监听
//                    @Override
//                    public void onStarTouch(int touchCount) {
//                        Toast.makeText(getContext(), "点击第" + touchCount + "个星星", Toast.LENGTH_SHORT).show();
//                    }
//                });
    }
}


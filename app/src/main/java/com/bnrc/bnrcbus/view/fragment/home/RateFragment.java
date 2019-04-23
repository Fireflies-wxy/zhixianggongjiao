package com.bnrc.bnrcbus.view.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bnrc.bnrcbus.R;
import com.bnrc.bnrcbus.network.RequestCenter;
import com.bnrc.bnrcbus.view.fragment.BaseFragment;
import com.cb.ratingbar.CBRatingBar;

/**
 * Created by apple on 2018/5/24.
 */

public class RateFragment extends BaseFragment {

    private View mContentView;
    private RadioGroup mRgCarStatus, mRgWaitStatus;
    private int carStatusRate;
    private int waitStatusRate;
    private Button mButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_rate, container, false);
        initView();
        return mContentView;
    }

    public void initView() {
        mRgCarStatus = mContentView.findViewById(R.id.rg_car_status);
        mRgWaitStatus = mContentView.findViewById(R.id.rg_wait_status);
        mButton = mContentView.findViewById(R.id.btn_sunmit);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                RequestCenter.requestBusData();
                Toast.makeText(mContext,"提交成功，感谢您的反馈。",Toast.LENGTH_SHORT).show();

            }
        });

        mRgCarStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rbtn_car_status_low:
                        carStatusRate = 1;
                        break;
                    case R.id.rbtn_car_status_mid:
                        carStatusRate = 2;
                        break;
                    case R.id.rbtn_car_status_high:
                        carStatusRate = 3;
                        break;
                }
            }
        });

        mRgWaitStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rbtn_wait_status_low:
                        waitStatusRate = 1;
                        break;
                    case R.id.rbtn_wait_status_mid:
                        waitStatusRate = 2;
                        break;
                    case R.id.rbtn_wait_status_high:
                        waitStatusRate = 3;
                        break;
                }
            }
        });

    }


}


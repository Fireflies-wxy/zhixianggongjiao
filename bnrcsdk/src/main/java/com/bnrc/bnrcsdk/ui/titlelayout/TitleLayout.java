package com.bnrc.bnrcsdk.ui.titlelayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.bnrc.bnrcsdk.R;

/**
 * Created by apple on 2018/6/28.
 */

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.layout_title,this);
    }
}

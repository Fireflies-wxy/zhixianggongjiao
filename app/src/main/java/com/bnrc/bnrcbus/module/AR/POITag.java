package com.bnrc.bnrcbus.module.AR;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bnrc.bnrcbus.R;

public class POITag extends FrameLayout {

    public POITag(Context context) {
        this(context, null);
        Log.i("poiresultinfo", "public invoked");
    }

    public POITag(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public POITag(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.poitag_layout, this, true);
    }
}

package com.bnrc.bnrcbus.module.AR;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bnrc.bnrcbus.R;

public class POITag extends FrameLayout {

    private TextView poi_name,poi_distance;

    Context mContext;

    public POITag(Context context) {
        this(context, null);
    }

    public POITag(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public POITag(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        poi_name = findViewById(R.id.poi_name);
        poi_distance = findViewById(R.id.poi_distance);
        poi_name.setWidth(10+(poi_name.length()>poi_distance.length()?poi_name.length():poi_distance.length()));
        poi_distance.setWidth(10+(poi_name.length()>poi_distance.length()?poi_name.length():poi_distance.length()));
    }
}

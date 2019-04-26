package com.bnrc.bnrcbus.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bnrc.bnrcbus.R;

public class BnrcToast {
    private Toast mToast;
    private BnrcToast(Context context, CharSequence text, int duration) {
        View v = LayoutInflater.from(context).inflate(R.layout.bnrc_toast, null);
        TextView textView = (TextView) v.findViewById(R.id.textView1);
        textView.setText(text);
        mToast = new Toast(context);
        mToast.setDuration(duration);
        mToast.setView(v);
    }

    public static BnrcToast makeText(Context context, CharSequence text, int duration) {
        return new BnrcToast(context, text, duration);
    }
    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }
    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }
}

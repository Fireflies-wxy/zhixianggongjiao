package com.bnrc.bnrcbus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by apple on 2018/7/22.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "BrpadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: ");
        Toast.makeText(context,"broadcast received\n"+"status: "+"STILL",Toast.LENGTH_SHORT).show();
    }
}
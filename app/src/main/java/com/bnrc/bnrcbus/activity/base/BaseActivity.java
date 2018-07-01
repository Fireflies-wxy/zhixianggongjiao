package com.bnrc.bnrcbus.activity.base;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.ColorRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bnrc.bnrcbus.R;
import com.bnrc.bnrcbus.constant.Constants;
import com.bnrc.bnrcbus.ui.LoadingDialog;

public class BaseActivity extends AppCompatActivity {

    private LoadingDialog mLoading;
    private Intent mAlertIntent, mScanWifiIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public synchronized LoadingDialog showLoading() {
        if (mLoading == null) {
            mLoading = new LoadingDialog(this, R.layout.view_tips_loading);
            mLoading.setCancelable(false);
            mLoading.setCanceledOnTouchOutside(true);
        }
        if (!this.isFinishing() && this.mLoading != null)
            mLoading.show();
        return mLoading;
    }

    public synchronized void dismissLoading() {

        if (!this.isFinishing() && this.mLoading != null
                && this.mLoading.isShowing()) {
            mLoading.dismiss();
            mLoading = null;
        }
    }

    /**
     * 申请指定的权限.
     */
    public void requestPermission(int code, String... permissions) {

        ActivityCompat.requestPermissions(this, permissions, code);
    }

    /**
     * 判断是否有指定的权限
     */
    public boolean hasPermission(String... permissions) {

        for (String permisson : permissions) {
            if (ContextCompat.checkSelfPermission(this, permisson)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Constants.WRITE_READ_EXTERNAL_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doSDCardPermission();
                }
                break;
        }
    }

    /**
     * 处理整个应用用中的SDCard业务
     */
    public void doSDCardPermission() {
    }

}

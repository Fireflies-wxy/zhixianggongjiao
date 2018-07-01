package com.bnrc.bnrcbus.application;

import android.app.Application;

import com.bnrc.bnrcsdk.ui.icon.IconFontModule;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;

/**
 * Created by apple on 2018/5/23.
 *
 */

public class BnrcApplication extends Application {

    private static BnrcApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        //初始化图标库
        Iconify
                .with(new FontAwesomeModule())
                .with(new IoniconsModule())
                .with(new IconFontModule());
    }

    public static BnrcApplication getInstance(){
        return mApplication;
    }
}

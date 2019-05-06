package com.bnrc.bnrcbus.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.bnrc.bnrcbus.activity.SettingActivity;


public class SharePrefrenceUtil {
	private static SharePrefrenceUtil mInstance;
	private Context mContext;
	private SharedPreferences mPreferences;
	private SharedPreferences.Editor mEditor;
	private final String category = "SETTING";

	private SharePrefrenceUtil(Context ctx) {
		mContext = ctx;
	}

	public static SharePrefrenceUtil getInstance(Context ctx) {
		if (mInstance == null)
			mInstance = new SharePrefrenceUtil(ctx);
		return mInstance;
	}

	public void setKey(String key, String value) {
		mPreferences = mContext.getSharedPreferences(category,
				SettingActivity.MODE_PRIVATE);
		mEditor = mPreferences.edit();
		mEditor.putString(key, value);
		mEditor.commit();
	}

	public String getValue(String key, String defaultString) {
		mPreferences = mContext.getSharedPreferences(category,
				SettingActivity.MODE_PRIVATE);
		String value = mPreferences.getString(key, defaultString);
		return value;
	}
}

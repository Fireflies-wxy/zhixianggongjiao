package com.bnrc.bnrcbus.adapter;

import com.bnrc.bnrcbus.module.rtBus.Child;
import com.bnrc.bnrcbus.ui.LoadingDialog;

public interface IPopWindowListener {
	void onPopClick(Child child);

	void onLoginClick();

	LoadingDialog showLoading();

	void dismissLoading();
}

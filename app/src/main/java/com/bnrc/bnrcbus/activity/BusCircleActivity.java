package com.bnrc.bnrcbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.bnrc.bnrcbus.R;
import com.bnrc.bnrcbus.activity.base.BaseActivity;
import com.bnrc.bnrcsdk.util.AnimationUtil;


public class BusCircleActivity extends BaseActivity {
	private TextView mSubway;
	private TextView mHotel;
	private TextView mRestaurant;
	private TextView mBank;
	private TextView mSupermarket;
	private TextView mOil;
	private TextView mNetbar;
	private TextView mKTV;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscircle);
		mSubway = (TextView) findViewById(R.id.tv_subway);
		mHotel = (TextView) findViewById(R.id.tv_hotel);
		mRestaurant = (TextView) findViewById(R.id.tv_restaurant);
		mBank = (TextView) findViewById(R.id.tv_ATM);
		mSupermarket = (TextView) findViewById(R.id.tv_supermarket);
		mOil = (TextView) findViewById(R.id.tv_oil);
		mNetbar = (TextView) findViewById(R.id.tv_netbar);
		mKTV = (TextView) findViewById(R.id.tv_KTV);
		setListener();

		findViewById(R.id.menu_view).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	private void setListener() {
		mSubway.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(BusCircleActivity.this,
						SearchSomethingActivity.class);

				// ���ñ�����͸��
				intent.putExtra("Keyword", "地铁");
				startActivity(intent);
				AnimationUtil.activityZoomAnimation(BusCircleActivity.this);

			}
		});
		mOil.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(BusCircleActivity.this,
						SearchSomethingActivity.class);
				// ���ñ�����͸��
				intent.putExtra("Keyword", "加油站");
				startActivity(intent);
				AnimationUtil.activityZoomAnimation(BusCircleActivity.this);

			}
		});

		mNetbar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(BusCircleActivity.this,
						SearchSomethingActivity.class);
				// ���ñ�����͸��
				intent.putExtra("Keyword", "网吧");
				startActivity(intent);
				AnimationUtil.activityZoomAnimation(BusCircleActivity.this);

			}
		});

		mKTV.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(BusCircleActivity.this,
						SearchSomethingActivity.class);
				intent.putExtra("Keyword", "KTV");
				startActivity(intent);
				AnimationUtil.activityZoomAnimation(BusCircleActivity.this);

			}
		});
		mHotel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// �Ƿ�ֻ���ѵ�¼�û����ܴ򿪷���ѡ��ҳ

				Intent intent = new Intent(BusCircleActivity.this,
						SearchSomethingActivity.class);
				// ���ñ�����͸��
				intent.putExtra("Keyword", "酒店");
				startActivity(intent);
				AnimationUtil.activityZoomAnimation(BusCircleActivity.this);

			}
		});

		mRestaurant.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(BusCircleActivity.this,
						SearchSomethingActivity.class);
				intent.putExtra("Keyword", "小吃");
				startActivity(intent);
				AnimationUtil.activityZoomAnimation(BusCircleActivity.this);

			}
		});

		mBank.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// �Ƿ�ֻ���ѵ�¼�û����ܴ򿪷���ѡ��ҳ

				Intent intent = new Intent(BusCircleActivity.this,
						SearchSomethingActivity.class);
				// ���ñ�����͸��
				intent.putExtra("Keyword", "银行");
				startActivity(intent);
				AnimationUtil.activityZoomAnimation(BusCircleActivity.this);

			}
		});

		mSupermarket.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// �Ƿ�ֻ���ѵ�¼�û����ܴ򿪷���ѡ��ҳ

				Intent intent = new Intent(BusCircleActivity.this,
						SearchSomethingActivity.class);
				intent.putExtra("Keyword", "超市");
				startActivity(intent);
				AnimationUtil.activityZoomAnimation(BusCircleActivity.this);

			}
		});

	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

}

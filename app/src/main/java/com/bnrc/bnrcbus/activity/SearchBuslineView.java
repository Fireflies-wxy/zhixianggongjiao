package com.bnrc.bnrcbus.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bnrc.bnrcbus.R;
import com.bnrc.bnrcbus.activity.base.BaseActivity;
import com.bnrc.bnrcbus.adapter.MyLatestListViewAdapter;
import com.bnrc.bnrcbus.constant.Constants;
import com.bnrc.bnrcbus.listener.ItemDelListener;
import com.bnrc.bnrcbus.module.rtBus.historyItem;
import com.bnrc.bnrcbus.util.database.PCDataBaseHelper;
import com.bnrc.bnrcbus.util.database.PCUserDataDBHelper;
import com.bnrc.bnrcsdk.util.AnimationUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchBuslineView extends BaseActivity implements ItemDelListener {
	private static final String TAG = SearchBuslineView.class.getSimpleName();
	private ImageView mDeleteView;
	private EditText mSearchEdt;
	private Button mSearchBtn;
	private ListView mSearchListView;
	private RelativeLayout mNoHistory;
	public PCDataBaseHelper mSearchDB = null;
	public PCUserDataDBHelper mUserDB = null;
	public List<historyItem> mListData;
	public List<historyItem> mTempData;
	private MyLatestListViewAdapter mListViewAdapter;
	private List<historyItem> mBuslines;
	private List<historyItem> mStations;
	private List<historyItem> mHistory;
	private RelativeLayout mAdContainer;

	private TextView tv_search_title, search_back_view;

	private View footerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		register();
		setListener();
		mSearchEdt.setFocusable(true);
		mSearchEdt.requestFocus();
		InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		// 接受软键盘输入的编辑文本或其它视图
		inputMethodManager.showSoftInput(mSearchEdt,
				InputMethodManager.SHOW_FORCED);
		mSearchDB = PCDataBaseHelper.getInstance(SearchBuslineView.this);
		mUserDB = PCUserDataDBHelper.getInstance(SearchBuslineView.this);
		mListData = new ArrayList<>();
		mTempData = new ArrayList<>();
		mHistory = new ArrayList<>();
		mListViewAdapter = new MyLatestListViewAdapter(this, mListData,
				R.layout.list_items, new String[] { "itemsIcon", "itemsTitle",
						"itemsText", "itemsDel" }, new int[] { R.id.itemsIcon,
						R.id.tv_stationName, R.id.itemsText, R.id.iv_delete });
		mSearchListView.setAdapter(mListViewAdapter);

		tv_search_title = findViewById(R.id.tv_search_title);
		search_back_view = findViewById(R.id.search_back_view);
		search_back_view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
						finish();
			}
		});
		loadHistoryData();
	}

	private void register() {
		mDeleteView = (ImageView) findViewById(R.id.iv_delete);
		mSearchEdt = (EditText) findViewById(R.id.edt_input);
		mSearchBtn = (Button) findViewById(R.id.btn_search);
		mSearchListView = (ListView) findViewById(R.id.mBuslineListView);
		mNoHistory = (RelativeLayout) findViewById(R.id.rLayout_search_history);
	}

	private void setListener() {
		mDeleteView.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mSearchEdt.setText("");
				loadHistoryData();
			}
		});

		mSearchEdt.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
				// TODO Auto-generated method stub
				if (s.length() >= 1) {
					// getBuslineWithKeyword(mSearchEdt.getText().toString());
				}

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
				// TODO Auto-generated method stub
				// loadHistoryData();
			}

			public void afterTextChanged(Editable s) {
				if (s.length() == 0) {
					mDeleteView.setVisibility(View.GONE);
					loadHistoryData();
				} else {
					mDeleteView.setVisibility(View.VISIBLE);
				}
			}
		});

		mSearchBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager) getSystemService(SearchBuslineView.this.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(mSearchEdt.getWindowToken(), 0);
				if (mSearchEdt.getText().length() < 1) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"线路名称不能为空哦~", Toast.LENGTH_LONG);
					toast.show();
				} else {
					getBuslineWithKeyword(mSearchEdt.getText().toString());
				}
			}
		});
		mSearchListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
				historyItem item = mListData.get(position);
				switch (item.getType()) {
				case Constants.STATION:
					Intent stationIntent = new Intent(SearchBuslineView.this,
							StationListView.class);
					stationIntent.putExtra("StationName", item.getStationName());
					startActivity(stationIntent);
					AnimationUtil.activityZoomAnimation(SearchBuslineView.this);
					item.setType(Constants.STATION);
					mUserDB.addSearchRecord(item);
					break;
				case Constants.BUSLINE:
					Intent buslineIntent = new Intent(SearchBuslineView.this,
							BuslineListViewParallel.class);
					int LineID = item.getLineID();
					int StationID = 0;
					int Sequence = 1;
					buslineIntent.putExtra("LineID", LineID);
					buslineIntent.putExtra("StationID", StationID);
					buslineIntent.putExtra("Sequence", Sequence);
					startActivity(buslineIntent);
					AnimationUtil.activityZoomAnimation(SearchBuslineView.this);
					item.setType(Constants.BUSLINE);
					mUserDB.addSearchRecord(item);
					break;
				default:
					break;

				}
			};
		});
	}

	public void loadHistoryData() {
		mListData.clear();
		mHistory = mUserDB.acquireLatestSearchHistory();
		if (footerView != null && mSearchListView.getFooterViewsCount() != 0)
			try {
				// 每次总是先remove掉FooterView
				mSearchListView.removeFooterView(footerView);
			} catch (Exception e) {

			}
		mListData.addAll(mHistory);
		if (mListData.size() > 0) {

			if (footerView != null
					&& mSearchListView.getFooterViewsCount() != 0)
				try {
					// 每次总是先remove掉FooterView
					mSearchListView.removeFooterView(footerView);

				} catch (Exception e) {

				}
			footerView = LayoutInflater.from(this).inflate(
					R.layout.footer_clear, null);
			mSearchListView.addFooterView(footerView);
			mNoHistory.setVisibility(View.GONE);
			footerView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View paramView) {
					// TODO Auto-generated method stub
					mUserDB.delAllSearchRecord();
					mListData.clear();
					mListViewAdapter.notifyDataSetChanged();
					mSearchListView.removeFooterView(footerView);
					mNoHistory.setVisibility(View.VISIBLE);
				}
			});
		} else
			mNoHistory.setVisibility(View.VISIBLE);
		mListViewAdapter.setDelListener(this);
		mListViewAdapter.notifyDataSetChanged();
	}

	public void getBuslineWithKeyword(String keyword) {
		mListData.clear();
		mTempData.clear();
		mBuslines = mSearchDB.acquireBusLinesWithKeyword(keyword);
		mStations = mSearchDB.acquireStationsWithStationKeyword(keyword);
		// mTitleTextView.setText("共搜索到"+j+"条关于\""+etSearch.getText().toString()+"\"的公交线路·");
		if (getNumWithStr(keyword) > 0) {
			mTempData.addAll(mBuslines);
			mTempData.addAll(mStations);
		} else {
			mTempData.addAll(mStations);
			mTempData.addAll(mBuslines);
		}

		if (footerView != null && mSearchListView.getFooterViewsCount() != 0)
			try {
				// 每次总是先remove掉FooterView
				mSearchListView.removeFooterView(footerView);

			} catch (Exception e) {

			}
		if (mTempData.size() > 10) {
			mListData.addAll(mTempData.subList(0, 10));
			footerView = LayoutInflater.from(this).inflate(
					R.layout.footer_more, null);
			mSearchListView.addFooterView(footerView);
			footerView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View paramView) {
					// TODO Auto-generated method stub
					mListData.clear();
					mListData.addAll(mTempData);
					mSearchListView.removeFooterView(footerView);
					mListViewAdapter.notifyDataSetChanged();
				}
			});
		} else {
			mListData.clear();
			mListData.addAll(mTempData);
			mListViewAdapter.notifyDataSetChanged();
		}
		mNoHistory.setVisibility(View.GONE);
		mListViewAdapter.notifyDataSetChanged();
		if (mListData == null || mListData.size() <= 0)
			Toast.makeText(this, "没有相关站点或线路，请重新输入关键字！", Toast.LENGTH_SHORT)
					.show();
	}

	public int getNumWithStr(String str) {
		str = str.trim();
		String str2 = "-1";
		if (str != null && !"".equals(str)) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
					str2 += str.charAt(i);
				}
			}
		}
		return Integer.parseInt(str2);
	}

	private void initAD() {
//		MobclickAgent.updateOnlineConfig(this);
//		MobclickAgent
//				.setOnlineConfigureListener(new UmengOnlineConfigureListener() {
//					@Override
//					public void onDataReceived(JSONObject data) {
//					}
//				});
//		String value = MobclickAgent.getConfigParams(SearchBuslineView.this,
//				"open_ad");

		// if (value.equals("1")) {
		// mAdContainer = (RelativeLayout) findViewById(R.id.adcontainer);
		// // Create ad view
		// mAdview = new AdView(this, "56OJzfwIuN7tr9LoSs",
		// "16TLmHWoAp8diNUdpuAEMYfi");
		// SharedPreferences mySharedPreferences = getSharedPreferences(
		// "setting", UserSettingView.MODE_PRIVATE);
		// String agString = mySharedPreferences.getString("userAge", "20");
		// String sexString = mySharedPreferences.getString("userSex", "女");
		//
		// if (sexString.equals("女")) {
		// mAdview.setUserGender("female");
		// } else {
		// mAdview.setUserGender("male");
		// }
		// // mAdview.setKeyword("game");
		//
		// Calendar mycalendar = Calendar.getInstance();// ��ȡ����ʱ��
		// String curYearString = String
		// .valueOf(mycalendar.get(Calendar.YEAR));// ��ȡ���
		// int age = Integer.parseInt(agString);
		// int birth = Integer.parseInt(curYearString) - age;
		// mAdview.setUserBirthdayStr(birth + "-08-08");
		// mAdview.setUserPostcode("123456");
		// mAdview.setAdEventListener(new AdEventListener() {
		// @Override
		// public void onAdOverlayPresented(AdView adView) {
		// Log.i("DomobSDKDemo", "overlayPresented");
		// }
		//
		// @Override
		// public void onAdOverlayDismissed(AdView adView) {
		// Log.i("DomobSDKDemo", "Overrided be dismissed");
		// }
		//
		// @Override
		// public void onAdClicked(AdView arg0) {
		// Log.i("DomobSDKDemo", "onDomobAdClicked");
		// }
		//
		// @Override
		// public void onLeaveApplication(AdView arg0) {
		// Log.i("DomobSDKDemo", "onDomobLeaveApplication");
		// }
		//
		// @Override
		// public Context onAdRequiresCurrentContext() {
		// return SearchBuslineView.this;
		// }
		//
		// @Override
		// public void onAdFailed(AdView arg0, ErrorCode arg1) {
		// Log.i("DomobSDKDemo", "onDomobAdFailed");
		// }
		//
		// @Override
		// public void onEventAdReturned(AdView arg0) {
		// Log.i("DomobSDKDemo", "onDomobAdReturned");
		// }
		// });
		// RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(
		// LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		// layout.addRule(RelativeLayout.CENTER_HORIZONTAL);
		// mAdview.setLayoutParams(layout);
		// mAdContainer.addView(mAdview);
		// }

	}

	@Override
	public void onResume() {
		super.onResume();
		// getSearchList();
		//MobclickAgent.onPageStart("SplashScreen"); // ͳ��ҳ��
		//MobclickAgent.onResume(this); // ͳ��ʱ��
		// registerReceiver(mWifiReceiver, wifiFilter);
		// registerReceiver(mActivityReceiver, activityFilter);
		Log.i(TAG, "onResume");

	}

	@Override
	public void onRestart() {
		super.onRestart();
		// loadHistoryData();
		Log.i(TAG, "onRestart");
		// 获取编辑框焦点
		mSearchEdt.setFocusable(true);

		// 打开软键盘
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	@Override
	public void onPause() {
		super.onPause();
		//MobclickAgent.onPageEnd("SplashScreen");
		//MobclickAgent.onPause(this);
		// unregisterReceiver(mWifiReceiver);
		// unregisterReceiver(mActivityReceiver);

		// 关闭软键盘
		mSearchEdt.clearFocus();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(mSearchEdt.getWindowToken(), 0);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}

	@Override
	public void onDelItem(int position) {
		// TODO Auto-generated method stub
		// ArrayList<String> delRecord = new ArrayList<String>();
		historyItem item = mListData.get(position);
		mUserDB.delOneSearchRecordByType(item);
		mListData.remove(position);
		if (mListData.size() == 0 && footerView != null
				&& mSearchListView.getFooterViewsCount() != 0) {
			mNoHistory.setVisibility(View.VISIBLE);
			mSearchListView.removeFooterView(footerView);
		}
		mListViewAdapter.notifyDataSetChanged();
	}

}

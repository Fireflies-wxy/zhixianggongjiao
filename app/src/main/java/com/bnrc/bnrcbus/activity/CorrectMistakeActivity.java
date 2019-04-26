package com.bnrc.bnrcbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bnrc.bnrcbus.R;
import com.bnrc.bnrcbus.activity.base.BaseActivity;
import com.bnrc.bnrcsdk.util.AnimationUtil;


public class CorrectMistakeActivity extends BaseActivity {
	private RelativeLayout mDelay, mAdvance, mHasCar, mNoCar, mWrongName,
			mWrongStation, mWrongLine, mCancelLine, mWrongTime;

	private TextView correct_menu_view,tv_correct_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_correct_mistake);

		tv_correct_title = findViewById(R.id.tv_correct_title);
		correct_menu_view = findViewById(R.id.correct_menu_view);
		correct_menu_view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				switch (view.getId()){
					case R.id.correct_menu_view:
						finish();
						break;
				}
			}
		});

		tv_correct_title.setText("纠错");

		mDelay = (RelativeLayout) findViewById(R.id.rLayout_delay);
		mAdvance = (RelativeLayout) findViewById(R.id.rLayout_advance);
		mHasCar = (RelativeLayout) findViewById(R.id.rLayout_hasCar);
		mNoCar = (RelativeLayout) findViewById(R.id.rLayout_noCar);

		mWrongName = (RelativeLayout) findViewById(R.id.rLayout_wrongName);
		mWrongStation = (RelativeLayout) findViewById(R.id.rLayout_wrongStation);
		mWrongLine = (RelativeLayout) findViewById(R.id.rLayout_wrongLine);
		mCancelLine = (RelativeLayout) findViewById(R.id.rLayout_cancelLine);
		mWrongTime = (RelativeLayout) findViewById(R.id.rLayout_wrongTime);

		mDelay.setOnClickListener(mListener);
		mAdvance.setOnClickListener(mListener);
		mHasCar.setOnClickListener(mListener);
		mNoCar.setOnClickListener(mListener);

		mWrongName.setOnClickListener(mListener);
		mWrongStation.setOnClickListener(mListener);
		mWrongLine.setOnClickListener(mListener);
		mCancelLine.setOnClickListener(mListener);
		mWrongTime.setOnClickListener(mListener);

	}

	private OnClickListener mListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String mistakeName = "未知";
			switch (v.getId()) {
			case R.id.rLayout_delay:
				mistakeName = "延迟到站";
				break;
			case R.id.rLayout_advance:
				mistakeName = "提前到站";

				break;
			case R.id.rLayout_hasCar:
				mistakeName = "实际有车，显示无车";

				break;
			case R.id.rLayout_noCar:
				mistakeName = "显示有车，实际无车";

				break;

			case R.id.rLayout_wrongName:
				mistakeName = "站名有误";

				break;
			case R.id.rLayout_wrongStation:
				mistakeName = "少站或多站";

				break;
			case R.id.rLayout_wrongLine:
				mistakeName = "线路已变更";

				break;
			case R.id.rLayout_cancelLine:
				mistakeName = "线路取消或停运";

				break;
			case R.id.rLayout_wrongTime:
				mistakeName = "首末班时间有误";

				break;
			default:
				break;
			}
//			Intent intent = new Intent(CorrectMistakeActivity.this,
//					UploadMistakeActivity.class);
//			intent.putExtra("MistakeName", mistakeName);
//			startActivity(intent);
//			AnimationUtil.activityZoomAnimation(CorrectMistakeActivity.this);
			Toast.makeText(getApplicationContext(),"Report mistake",Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onPause() {
		super.onPause();
//		MobclickAgent.onPageEnd("SplashScreen");
//		MobclickAgent.onPause(this);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		MobclickAgent.onPageStart("SplashScreen");
//		MobclickAgent.onResume(this);
	}
}

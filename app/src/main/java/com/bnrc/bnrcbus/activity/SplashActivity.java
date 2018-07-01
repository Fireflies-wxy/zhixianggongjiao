package com.bnrc.bnrcbus.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bnrc.bnrcbus.R;

public class SplashActivity extends AppCompatActivity {

    private int TIME = 1;  //3秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            public void run() {
				/*
				 * Create an Intent that will start the Main WordPress Activity.
				 */
                Intent intent = new Intent(SplashActivity.this,
                        HomeActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        }, TIME * 1000); // 2900 for release
    }
}

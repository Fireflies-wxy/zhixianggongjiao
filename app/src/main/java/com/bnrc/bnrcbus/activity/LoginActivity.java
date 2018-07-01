package com.bnrc.bnrcbus.activity;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bnrc.bnrcbus.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView btn_sign_in;
    private TextView tv_link_sign_up;
    private TextView pressback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView(){
        btn_sign_in = findViewById(R.id.btn_sign_in);
        btn_sign_in.setOnClickListener(this);
        tv_link_sign_up = findViewById(R.id.tv_link_sign_up);
        tv_link_sign_up.setOnClickListener(this);
        pressback = findViewById(R.id.back_login);
        pressback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_sign_in:
                Intent homeIntent = new Intent(LoginActivity.this,
                        HomeActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.tv_link_sign_up:
                Intent registerIntent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(registerIntent);
                break;
            case R.id.back_login:
                finish();
                break;
        }
    }

}

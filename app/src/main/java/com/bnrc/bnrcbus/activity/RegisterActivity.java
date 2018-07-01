package com.bnrc.bnrcbus.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bnrc.bnrcbus.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView btn_sign_up;
    private TextView tv_link_sign_in;
    private TextView pressback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    public void initView(){
        btn_sign_up = findViewById(R.id.btn_sign_up);
        btn_sign_up.setOnClickListener(this);
        tv_link_sign_in = findViewById(R.id.tv_link_sign_in);
        tv_link_sign_in.setOnClickListener(this);
        pressback = findViewById(R.id.back_register);
        pressback.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_sign_up:
            case R.id.tv_link_sign_in:
                Intent loginIntent = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.back_register:
                finish();
                break;
        }
    }
}

package com.bnrc.bnrcbus.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bnrc.bnrcbus.R;
import com.bnrc.bnrcbus.activity.base.BaseActivity;
import com.bnrc.bnrcbus.module.user.RegisterInfo;
import com.bnrc.bnrcbus.network.RequestCenter;
import com.bnrc.bnrcsdk.okhttp.listener.DisposeDataListener;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "RegisterActivity";

    private TextView btn_sign_up;
    private TextView tv_link_sign_in;
    private TextView pressback;
    private EditText et_username;
    private EditText et_password;

    private String username;
    private String password;

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

        et_username = findViewById(R.id.edit_sign_up_name);
        et_password = findViewById(R.id.edit_sign_up_password);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_sign_up:
                username = et_username.getText().toString().trim();
                password = et_password.getText().toString().trim();
                RequestCenter.register(username, password,new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        RegisterInfo info = (RegisterInfo) responseObj;
                        Log.i(TAG, "onSuccess: "+info.errorCode+password);
                        if(info.errorCode == 0){
                            Toast.makeText(getApplicationContext(),"注册成功,请登录",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,
                                    LoginActivity.class));
                        }else if(info.errorCode == 40000){
                            Toast.makeText(getApplicationContext(),"用户名已存在",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.tv_link_sign_in:
                Log.i(TAG, "onSuccess: 201");
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

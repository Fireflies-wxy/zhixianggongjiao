package com.bnrc.bnrcbus.activity;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bnrc.bnrcbus.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,PlatformActionListener{
    private static final String TAG = "LoginActivity";

    private TextView btn_sign_in;
    private TextView tv_link_sign_up;
    private TextView pressback;
    private TextView login_wechat,login_qq,login_sina;

    private PlatformDb platDB; //平台授权数据DB

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
        login_wechat = findViewById(R.id.icon_sign_in_wechat);
        login_wechat.setOnClickListener(this);
        login_qq = findViewById(R.id.icon_sign_in_qq);
        login_qq.setOnClickListener(this);
        login_sina = findViewById(R.id.icon_sign_in_sina);
        login_sina.setOnClickListener(this);
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
            case R.id.icon_sign_in_wechat:
                // 微信登录
//                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
//                authorize(wechat);
                break;
            case R.id.icon_sign_in_qq:
                // qq登录
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                authorize(qq);
                break;
            case R.id.icon_sign_in_sina:
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                Toast.makeText(this,"微博登录",Toast.LENGTH_SHORT).show();
                authorize(weibo);
                break;
        }
    }

    //第三方授权登录
    private void authorize(Platform plat) {

        if (plat == null) {
            return;
        }

        //判断指定平台是否已经完成授权
        if(plat.isAuthValid()) {

            plat.removeAccount(true);

            Toast.makeText(this,"删除授权",Toast.LENGTH_SHORT).show();
        }

        // true不使用SSO授权，false使用SSO授权
        plat.SSOSetting(false);
        plat.setPlatformActionListener(this);
        plat.authorize();

        //获取用户资料
        plat.showUser(null);
    }

    //授权成功回调
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Log.i(TAG,"授权成功");
        Log.i(TAG,"i: "+i);

        String headImageUrl = null;//头像
        String userId;//userId
        String token;//token
        String gender;//性别
        String name = null;//用户名

        if (i == Platform.ACTION_USER_INFOR) {

            platDB = platform.getDb(); // 获取平台数据DB

            token = platDB.getToken();
            userId = platDB.getUserId();
            name = hashMap.get("nickname").toString(); // 名字
            gender = hashMap.get("gender").toString(); // 年龄
            headImageUrl = hashMap.get("figureurl_qq_2").toString(); // 头像figureurl_qq_2 中等图，figureurl_qq_1缩略图

            Log.i(TAG,"token: "+token);
            Log.i(TAG,"userId: "+userId);
            Log.i(TAG,"name: "+name);
            Log.i(TAG,"gender: "+gender);
            Log.i(TAG,"headImageUrl: "+headImageUrl);


        }

    }

    //授权出错回调
    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Log.i(TAG,"授权出错");
    }

    //取消授权回调
    @Override
    public void onCancel(Platform platform, int i) {
        Log.i(TAG,"取消授权");
    }


}

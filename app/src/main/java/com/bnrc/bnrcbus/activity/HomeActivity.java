package com.bnrc.bnrcbus.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bnrc.bnrcbus.R;
import com.bnrc.bnrcbus.activity.ar.ArActivity;
import com.bnrc.bnrcbus.activity.base.BaseActivity;
import com.bnrc.bnrcbus.view.fragment.BaseFragment;
import com.bnrc.bnrcbus.view.fragment.buscircle.BusCircleFragment;
import com.bnrc.bnrcbus.view.fragment.home.HomeFragment;
import com.bnrc.bnrcbus.view.fragment.message.MessageFragment;
import com.bnrc.bnrcbus.view.fragment.route.RouteFragment;
import com.bnrc.bnrcsdk.ui.tabhost.RTabHost;
import com.joanzapata.iconify.widget.IconTextView;
import com.bnrc.bnrcsdk.ui.circleimage.CircleImageView;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;


/**
 * 创建首页及其他fragment
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener{

    private FragmentManager fm;
    private List<BaseFragment> fragmentList = new ArrayList<BaseFragment>();
    private BaseFragment mFragment;
    private List<Class<? extends BaseFragment>> classList = null;
    Class<? extends BaseFragment> fragClass = null;
    private RTabHost mTabHost;
    private int mLastIndex = 0;  //初始化时默认加载第一项"首页"
    private TextView tv_toolbar;
    private String[] titleList = {"等车来","路线","公交圈","消息"};

    private HomeFragment mHomeFragment;
    private RouteFragment mRouteFragment;
    private BusCircleFragment mBusCircleFragment;
    private MessageFragment mMessageFragment;

    private RelativeLayout mHomeLayout;
    private RelativeLayout mRouteLayout;
    private RelativeLayout mArLayout;
    private RelativeLayout mBusCircleLayout;
    private RelativeLayout mMessageLayout;

    private TextView icon_home,tv_home;
    private TextView icon_route,tv_route;
    private CircleImageView icon_ar;
    private TextView tv_ar;
    private TextView icon_buscircle,tv_buscircle;
    private TextView icon_message,tv_message;

    private DrawerLayout mDrawerLayout;
    private IconTextView icon_menu;

    private CircleImageView user_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //初始化页面中所有控件
        initView();
        initFragments();
        initTabHost();

        //添加默认要显示的fragment
//        mHomeFragment = new HomeFragment();
//        fm = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.content_layout,mHomeFragment);
//        fragmentTransaction.commit();
    }

    private void initView(){
        mHomeLayout = findViewById(R.id.home_layout_view);
        //mHomeLayout.setOnClickListener(this);
        mRouteLayout = findViewById(R.id.route_layout_view);
        //mRouteLayout.setOnClickListener(this);
        mArLayout = findViewById(R.id.ar_layout_view);
        mArLayout.setOnClickListener(this);
        mBusCircleLayout = findViewById(R.id.buscircle_layout_view);
        //mBusCircleLayout.setOnClickListener(this);
        mMessageLayout = findViewById(R.id.message_layout_view);
       // mMessageLayout.setOnClickListener(this);

        icon_home = findViewById(R.id.home_image_view);
        icon_route = findViewById(R.id.route_image_view);
        icon_ar = findViewById(R.id.ar_image_view);
        icon_buscircle = findViewById(R.id.buscircle_image_view);
        icon_message = findViewById(R.id.message_image_view);

        tv_home = findViewById(R.id.home_tv_view);
        tv_route = findViewById(R.id.route_tv_view);
        tv_ar = findViewById(R.id.ar_tv_view);
        tv_buscircle = findViewById(R.id.buscircle_tv_view);
        tv_message = findViewById(R.id.message_tv_view);
        tv_toolbar = findViewById(R.id.tv_home_title);//标题文字

        mDrawerLayout = findViewById(R.id.drawer_layout);
        icon_menu = findViewById(R.id.menu_view);

        user_icon = findViewById(R.id.icon_user);
        user_icon.setOnClickListener(this);
    }

    private void initFragments(){
        fragmentList.clear();
        classList = new ArrayList<Class<? extends BaseFragment>>();
        classList.add(HomeFragment.class);
        classList.add(RouteFragment.class);
        classList.add(BusCircleFragment.class);
        classList.add(MessageFragment.class);

        fm = getSupportFragmentManager();
        FragmentTransaction transcation = fm.beginTransaction();

        fragClass = classList.get(mLastIndex);
        mFragment = createFragmentByClass(fragClass);
        transcation.replace(R.id.content_layout, mFragment).commit();
    }

    private BaseFragment createFragmentByClass(
            Class<? extends BaseFragment> fragClass) {
        BaseFragment frag = null;
        try {
            try {
                Constructor<? extends BaseFragment> cons = null;
                cons = fragClass.getConstructor();
                frag = cons.newInstance();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (Throwable e) {
            throw new RuntimeException("Can not create instance for class "
                    + fragClass.getName(), e);
        }
        return frag;
    }

    private void initTabHost() {
        mTabHost = findViewById(R.id.tab_host);

        mTabHost.setQQTabHostListener(new RTabHost.RTabHostListener() {

            @Override
            public void onTabSelected(int index) {
                if(index>1)
                    index--;  //"-1" 是为了忽略"公交AR"这一项，此项不算做开启fragment
                selectTab(index);
                tv_toolbar.setText(titleList[index]);
            }
        });

        mTabHost.selectTab(mLastIndex);
    }

    private void selectTab(int index) {
        if (mLastIndex == index) {
            return;
        }
        mLastIndex = index;
        selectFragment(index);
    }

    private void selectFragment(int index) {

        FragmentTransaction transcation = getSupportFragmentManager().beginTransaction();
        fragClass = classList.get(index);
        mFragment = createFragmentByClass(fragClass);
        transcation.replace(R.id.content_layout, mFragment).commit();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void hideFragment(Fragment fragment, FragmentTransaction ft){
        if(fragment != null)
            ft.hide(fragment);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.ar_layout_view:
                Intent subwayIntent = new Intent(HomeActivity.this,
                        ArActivity.class);
                startActivity(subwayIntent);
                break;
            case R.id.icon_user:
                Intent loginIntent = new Intent(HomeActivity.this,
                        LoginActivity.class);
                startActivity(loginIntent);
                break;

        }

    }

    public void openDrawerLayout(View view){
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

}

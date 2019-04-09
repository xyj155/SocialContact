package com.xuyijie.lifecircle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.module_library.MyApp;
import com.example.module_library.base.BaseActivity;
import com.example.module_library.config.RouterConfig;
import com.example.module_library.logic.contract.EmptyContract;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_library.weight.toast.ToastUtils;
import com.xuyijie.home_module.view.HomeFragment;
import com.xuyijie.location_module.view.SquareFragment;
import com.xuyijie.module_message.view.MessageFragment;
import com.xuyijie.user_module.view.UserFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity<EmptyContract.View, EmptyPresenter> {

    private RadioButton rbHome;
    private RadioButton rbKind;
    private RadioButton rbShopcar;
    private RadioButton rbUser;
    private RadioGroup rgHome;
    private static final String TAG = "MainActivity";


    private FragmentManager supportFragmentManager;
    private HomeFragment homeFragment;
    private SquareFragment kindFragment;
    private MessageFragment shopCarFragment;
    private UserFragment userFragment;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    //初始化AMapLocationClientOption对象

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
//                    tvLocation.setText(amapLocation.getStreet());latitude",
//"longitude"
                    Log.i(TAG, "onLocationChanged: getLatitude" + amapLocation.getLatitude());
                    Log.i(TAG, "onLocationChanged: getLongitude" + amapLocation.getLongitude());
                    Map<String, Object> map = new HashMap<>();
                    map.put("latitude",String.valueOf(amapLocation.getLatitude()));
                    map.put("longitude",String.valueOf(amapLocation.getLongitude()));
                    SharePreferenceUtil.saveUser(map);
                } else {
//                    tvLocation.setText("定位错误");
                    ToastUtils.show("定位错误：" + amapLocation.getErrorInfo());
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    private void initAMap() {
        mLocationOption = new AMapLocationClientOption();
        mLocationClient = new AMapLocationClient(MyApp.getInstance());
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    @Override
    public boolean isSetStatusBarTranslucent() {
        return true;
    }

    @Override
    public EmptyPresenter getPresenter() {
        return null;
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initAMap();
        rbHome = findViewById(R.id.rb_home);
        rbKind = findViewById(R.id.rb_kind);
        rbShopcar = findViewById(R.id.rb_shopcar);
        rbUser = findViewById(R.id.rb_user);
        rgHome = findViewById(R.id.rg_home);
    }

    @Override
    public void initData() {
        supportFragmentManager = getSupportFragmentManager();
        rbHome.setChecked(true);
        showFirstPosition();
        rgHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final FragmentTransaction transaction = supportFragmentManager.beginTransaction();
                hideAllFragment(transaction);
                switch (checkedId) {
                    case R.id.rb_home:
                        if (homeFragment == null) {
                            homeFragment = new HomeFragment();
                            transaction.add(R.id.flContainer, homeFragment);
                        } else {
                            transaction.show(homeFragment);
                        }
                        break;
                    case R.id.rb_kind:
                        if (kindFragment == null) {
                            kindFragment = new SquareFragment();
                            transaction.add(R.id.flContainer, kindFragment);
                        } else {
                            transaction.show(kindFragment);
                        }
                        break;
                    case R.id.rb_shopcar:
                        if (shopCarFragment == null) {
                            shopCarFragment = new MessageFragment();
                            transaction.add(R.id.flContainer, shopCarFragment);
                        } else {
                            transaction.show(shopCarFragment);
                        }
                        break;
                    case R.id.rb_user:
                        if (userFragment == null) {
                            userFragment = new UserFragment();
                            transaction.add(R.id.flContainer, userFragment);
                        } else {
                            transaction.show(userFragment);
                        }
                        break;
                }
                transaction.commit();
            }
        });
    }


    private void showFirstPosition() {
        supportFragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        homeFragment = new HomeFragment();
        transaction.add(R.id.flContainer, homeFragment);
        transaction.commit();
    }

    public void hideAllFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (kindFragment != null) {
            transaction.hide(kindFragment);
        }
        if (shopCarFragment != null) {
            transaction.hide(shopCarFragment);
        }
        if (userFragment != null) {
            transaction.hide(userFragment);
        }
    }

}

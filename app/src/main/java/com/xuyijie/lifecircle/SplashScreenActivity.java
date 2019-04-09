package com.xuyijie.lifecircle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.module_library.base.BaseActivity;
import com.example.module_library.logic.contract.EmptyContract;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.xuyijie.module_login.view.UserLoginActivity;

public class SplashScreenActivity extends BaseActivity<EmptyContract.View, EmptyPresenter> {


    @Override
    public boolean isSetStatusBarTranslucent() {
        return false;
    }

    @Override
    public EmptyPresenter getPresenter() {
        return null;
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_splash_screen;
    }

    @Override
    public void initView() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(UserLoginActivity.class);
        startActivity(new Intent(SplashScreenActivity.this,UserLoginActivity.class));
//            }
//        }, 0);
    }

    @Override
    public void initData() {

    }
}

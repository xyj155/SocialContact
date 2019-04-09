package com.xuyijie.user_module.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.module_library.base.BaseActivity;
import com.example.module_library.logic.contract.EmptyContract;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.xuyijie.user_module.R;

public class UserSettingActivity extends BaseActivity<EmptyContract.View, EmptyPresenter> {


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
        return R.layout.activity_user_setting;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}

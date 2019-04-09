package com.xuyijie.home_module.view;

import android.support.v4.app.Fragment;
import android.view.View;

import com.example.module_library.base.BaseActivity;
import com.example.module_library.base.BaseFragment;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.xuyijie.home_module.R;

public class SameCityFragment extends BaseFragment<EmptyPresenter> {
    @Override
    public void initData() {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public int initLayout() {
        return R.layout.fragment_samecity;
    }

    @Override
    public EmptyPresenter initPresenter() {
        return null;
    }
}

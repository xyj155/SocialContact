package com.xuyijie.module_login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.module_library.base.BaseActivity;
import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_library.weight.toast.ToastUtils;
import com.xuyijie.module_login.R;
import com.xuyijie.module_login.R2;
import com.xuyijie.module_login.contract.UserContract;
import com.xuyijie.module_login.presenter.UserPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nico.stytool.gson_module.UserGson;

public class UserLoginActivity extends BaseActivity<UserContract.View, UserPresenter> implements UserContract.View {


    @BindView(R2.id.et_tel)
    EditText etTel;
    @BindView(R2.id.btn_login)
    Button btnLogin;
    @BindView(R2.id.iv_close)
    ImageView ivClose;

    @Override
    public boolean isSetStatusBarTranslucent() {
        return true;
    }

    @Override
    public UserPresenter getPresenter() {
        return new UserPresenter(this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_user_login;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @Override
    public void getUserByTel(boolean tel) {
        if (tel) {
            startActivity(new Intent(UserLoginActivity.this, UserLoginByPassWordActivity.class));
        } else {

        }
        Map<String, Object> map = new HashMap<>();
        map.put("tel", etTel.getText().toString());
        SharePreferenceUtil.saveUser(map);

    }

    @Override
    public void userLoginByUserName(UserGson userGson) {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showDialog() {
        createDialog();
    }

    @Override
    public void hideDialog() {
        mhideDialog();
    }

    @OnClick({R2.id.btn_login, R2.id.iv_close})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_close) {
            finish();
        }else if (id==R.id.btn_login){
            mPresenter.queryUserByTelPhone(etTel.getText().toString());
        }

    }
}

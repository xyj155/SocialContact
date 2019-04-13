package com.xuyijie.module_login.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_library.base.BaseActivity;
import com.example.module_library.config.RouterConfig;
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
import io.rong.imkit.MainActivity;
import nico.stytool.gson_module.UserGson;

public class UserLoginByPassWordActivity extends BaseActivity<UserContract.View, UserPresenter> implements UserContract.View {


    @BindView(R2.id.et_tel)
    EditText etTel;
    @BindView(R2.id.ll_tel)
    LinearLayout llTel;
    @BindView(R2.id.btn_login)
    Button btnLogin;

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
        return R.layout.activity_user_login_by_pass_word;
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

    @OnClick({R2.id.btn_login, R2.id.iv_close})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_close) {
            finish();
        } else if (id == R.id.btn_login) {
            Log.i(TAG, "onViewClicked: ");
            mPresenter.userLoginByUserName(String.valueOf(SharePreferenceUtil.getUser("tel", "String")), etTel.getText().toString());
        }

    }

    @Override
    public void getUserByTel(boolean tel) {

    }

    @Override
    public void userLoginByUserName(UserGson userGson) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", userGson.getUsername());
        map.put("tel", userGson.getMobileNum());
        map.put("avatar", userGson.getAvatar());
        map.put("age", userGson.getAge());
        map.put("uid", String.valueOf(userGson.getId()));
        map.put("city", userGson.getCity());
        map.put("signature", userGson.getSignature());
        map.put("sex", userGson.getSex());
        map.put("login", true);
        map.put("constellation", userGson.getConstellation());
        SharePreferenceUtil.saveUser(map);
        ARouter.getInstance().build(RouterConfig.MAIN)
                .navigation();
//        startActivity(new Intent(UserLoginByPassWordActivity.this, MainActivity.class));
    }

    @Override
    public void showError(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void showDialog() {
        createDialog();
    }

    @Override
    public void hideDialog() {
        mhideDialog();
    }
}

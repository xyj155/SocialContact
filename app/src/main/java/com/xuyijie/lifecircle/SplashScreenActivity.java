package com.xuyijie.lifecircle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module_library.base.BaseActivity;
import com.example.module_library.logic.contract.EmptyContract;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_library.weight.toast.ToastUtils;
import com.facebook.common.util.SecureHashUtil;
import com.xuyijie.location_module.view.UserPostSubmitActivity;
import com.xuyijie.module_login.view.UserLoginActivity;

import java.util.ArrayList;
import java.util.List;

public class SplashScreenActivity extends BaseActivity<EmptyContract.View, EmptyPresenter> {
    String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    List<String> mPermissionList = new ArrayList<>();
    private static final int MY_PERMISSIONS_REQUEST_CALL_CAMERA = 2;

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
        return R.layout.activity_splash_screen;
    }

    private ImageView ivStart;
    private TextView tvTitle;

    @Override
    public void initView() {
        ivStart = findViewById(R.id.iv_start);
        tvTitle = findViewById(R.id.tv_title);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "initView: "+SharePreferenceUtil.getUser("login", "boolean"));
                if ((boolean) SharePreferenceUtil.getUser("login", "boolean")) {
                    startActivity(new Intent(SplashScreenActivity.this, UserPostSubmitActivity.class));
                    finish();
                } else {
                    ivStart.setVisibility(View.VISIBLE);
                    tvTitle.setVisibility(View.VISIBLE);
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                    alphaAnimation.setDuration(1000);
                    alphaAnimation.setFillAfter(true);
                    alphaAnimation.setFillBefore(true);
                    alphaAnimation.setRepeatMode(AlphaAnimation.REVERSE);
                    alphaAnimation.setRepeatCount(AlphaAnimation.INFINITE);
                    ivStart.startAnimation(alphaAnimation);
                    ivStart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(SplashScreenActivity.this, UserLoginActivity.class));
                        }
                    });
                }
            }
        }, 3000);

        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(SplashScreenActivity.this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
            Toast.makeText(SplashScreenActivity.this, "已经授权", Toast.LENGTH_LONG).show();
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(SplashScreenActivity.this, permissions, MY_PERMISSIONS_REQUEST_CALL_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_CAMERA) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    //判断是否勾选禁止后不再询问
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(SplashScreenActivity.this, permissions[i]);
                    if (showRequestPermission) {
//                        showToast("权限未申请");
                        ToastUtils.show("权限未申请");
                    } else {

                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void initData() {

    }
}

package com.example.module_library.config;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_library.MyApp;

import com.example.module_library.weight.toast.ToastUtils;
import com.tencent.tauth.Tencent;

import io.rong.imkit.RongIM;


public class ApplicationInitial {
    public ApplicationInitial initAliBabaRouter() {
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(MyApp.getInstance());
        return this;
    }

    public ApplicationInitial initToast() {
        ToastUtils.init(MyApp.getInstance());
        return this;
    }
    public ApplicationInitial initRongIM() {
        RongIM.init(MyApp.getInstance(),"p5tvi9dspekn4");
        return this;
    }

    public ApplicationInitial initTencentQQ() {
        Tencent instance = Tencent.createInstance("1108195133", MyApp.getInstance());
        return this;
    }

    public ApplicationInitial initCrash() {
//        CrashHandler.getInstance().init(MyApp.getInstance());//初始化全局异常管理
        return this;
    }
}

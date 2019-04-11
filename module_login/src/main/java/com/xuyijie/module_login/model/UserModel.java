package com.xuyijie.module_login.model;

import com.example.module_library.base.BaseGson;
import com.example.module_library.http.RetrofitUtils;
import com.xuyijie.module_login.contract.UserContract;

import nico.stytool.gson_module.EmptyGson;
import nico.stytool.gson_module.UserGson;
import rx.Observable;

public class UserModel implements UserContract.Model {
    @Override
    public Observable<BaseGson<EmptyGson>> queryUserByTelPhone(String tel) {
        return RetrofitUtils.getInstance().create().queryUserByTelPhone(tel);
    }

    @Override
    public Observable<BaseGson<UserGson>> userLoginByUserName(String username, String password) {
        return RetrofitUtils.getInstance().create().userLoginByUserName(username,password);
    }
}

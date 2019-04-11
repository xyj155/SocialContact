package com.xuyijie.location_module.model;

import com.example.module_library.base.BaseGson;
import com.example.module_library.http.RetrofitUtils;
import com.xuyijie.location_module.contract.UserPostContract;

import nico.stytool.gson_module.PostGson;
import rx.Observable;

public class UserPostModel implements UserPostContract.Model {
    @Override
    public Observable<BaseGson<PostGson>> queryUserPost(String city, String page) {
        return RetrofitUtils.getInstance().create().queryUserPost(city,page);
    }
}

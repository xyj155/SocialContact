package com.xuyijie.home_module.model;

import com.example.module_library.base.BaseGson;
import com.example.module_library.http.RetrofitUtils;
import com.xuyijie.home_module.contract.HomePageFragmentContract;

import nico.stytool.gson_module.UserGson;
import rx.Observable;

public class HomePageFragmentModel implements HomePageFragmentContract.Model {
    @Override
    public Observable<BaseGson<UserGson>> queryAroundUserByLocation(String city,String page) {
        return RetrofitUtils.getInstance().create().queryAroundUserByLocation(city,page);
    }
}

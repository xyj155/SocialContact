package com.xuyijie.location_module.model;

import com.example.module_library.base.BaseGson;
import com.example.module_library.http.RetrofitUtils;
import com.xuyijie.location_module.contract.UserPostContract;

import java.util.List;
import java.util.Map;

import nico.stytool.gson_module.EmptyGson;
import nico.stytool.gson_module.PostGson;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

public class UserPostModel implements UserPostContract.Model {
    @Override
    public Observable<BaseGson<PostGson>> queryUserPost(String city, String page) {
        return RetrofitUtils.getInstance().create().queryUserPost(city,page);
    }

    @Override
    public Observable<BaseGson<EmptyGson>> submitUserPostByUid(Map<String, RequestBody> partMap, List<MultipartBody.Part> file) {
        return RetrofitUtils.getInstance().create().submitUserPostByUid(partMap,file);
    }
}

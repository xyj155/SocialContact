package com.xuyijie.location_module.contract;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BaseView;

import java.util.List;
import java.util.Map;

import nico.stytool.gson_module.EmptyGson;
import nico.stytool.gson_module.PostGson;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

public interface UserPostContract {
    interface Model {
        Observable<BaseGson<PostGson>> queryUserPost(String city, String page);

        Observable<BaseGson<EmptyGson>> submitUserPostByUid(Map<String, RequestBody> partMap, List<MultipartBody.Part> file);
    }

    interface View extends BaseView {
        void queryUserPost(List<PostGson> postGsonList);
        void submitUserPostByUid(boolean postGsonList);
    }

    interface Presenter {
        void queryUserPost(String city, String page);

        void submitUserPostByUid(Map<String, RequestBody> partMap, List<MultipartBody.Part> file);
    }
}

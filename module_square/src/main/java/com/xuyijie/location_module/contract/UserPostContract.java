package com.xuyijie.location_module.contract;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BaseView;

import java.util.List;

import nico.stytool.gson_module.PostGson;
import rx.Observable;

public interface UserPostContract {
    interface Model {
        Observable<BaseGson<PostGson>>queryUserPost(String city,String page);
    }

    interface View extends BaseView {
        void queryUserPost(List<PostGson> postGsonList);
    }

    interface Presenter {
        void queryUserPost(String city,String page);
    }
}

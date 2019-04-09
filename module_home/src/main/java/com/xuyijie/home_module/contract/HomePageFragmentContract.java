package com.xuyijie.home_module.contract;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BaseView;

import java.util.List;

import nico.stytool.gson_module.UserGson;
import rx.Observable;

public interface HomePageFragmentContract {
    interface Model {
        Observable<BaseGson<UserGson>> queryAroundUserByLocation(String city,String page);
    }

    interface View extends BaseView {
        void queryAroundUserByLocation(List<UserGson> userGsonList);
    }

    interface Presenter {
        void queryAroundUserByLocation(String city,String page);
    }
}

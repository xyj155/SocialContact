package com.xuyijie.module_login.contract;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BaseView;

import nico.stytool.gson_module.EmptyGson;
import nico.stytool.gson_module.UserGson;
import retrofit2.http.Query;
import rx.Observable;

public interface UserContract {
    interface Model {
        Observable<BaseGson<EmptyGson>>queryUserByTelPhone(String tel);
        Observable<BaseGson<UserGson>>userLoginByUserName( String  username,  String  password);
    }

    interface View extends BaseView {
        void getUserByTel(boolean tel);
        void userLoginByUserName(UserGson userGson);
    }

    interface Presenter {
        void queryUserByTelPhone(String tel);
        void userLoginByUserName( String  username,  String  password);
    }
}

package com.xuyijie.home_module.presenter;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BasePresenter;
import com.example.module_library.http.BaseObserver;
import com.xuyijie.home_module.contract.HomePageFragmentContract;
import com.xuyijie.home_module.model.HomePageFragmentModel;

import nico.stytool.gson_module.UserGson;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePageFragmentPresenter extends BasePresenter<HomePageFragmentContract.View> implements HomePageFragmentContract.Presenter {

    public HomePageFragmentPresenter(HomePageFragmentContract.View mMvpView) {
        super(mMvpView);
    }

    HomePageFragmentModel homePageFragmentModel = new HomePageFragmentModel();

    @Override
    public void queryAroundUserByLocation(String city,String page) {
        mMvpView.showDialog();
        homePageFragmentModel.queryAroundUserByLocation(city,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        mMvpView.hideDialog();
                        if (userGsonBaseGson.isStatus()) {
                            mMvpView.queryAroundUserByLocation(userGsonBaseGson.getData());
                        }
                    }

                    @Override
                    public void onError(String error) {
                        mMvpView.hideDialog();
                    }
                });
    }
}

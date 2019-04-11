package com.xuyijie.location_module.presenter;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BasePresenter;
import com.example.module_library.http.BaseObserver;
import com.xuyijie.location_module.contract.UserPostContract;
import com.xuyijie.location_module.model.UserPostModel;

import nico.stytool.gson_module.PostGson;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserPostPresenter extends BasePresenter<UserPostContract.View> implements UserPostContract.Presenter {
    public UserPostPresenter(UserPostContract.View mMvpView) {
        super(mMvpView);
    }

    private UserPostModel userPostModel = new UserPostModel();

    @Override
    public void queryUserPost(String city, final String page) {
        mMvpView.showDialog();
        userPostModel.queryUserPost(city, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<PostGson>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<PostGson> postGsonBaseGson) {
                        mMvpView.hideDialog();
                        if (postGsonBaseGson.isStatus()){
                            mMvpView.queryUserPost(postGsonBaseGson.getData());
                        }else {
                            mMvpView.showError(postGsonBaseGson.getMsg());
                        }
                    }

                    @Override
                    public void onError(String error) {
                        mMvpView.hideDialog();
                        mMvpView.showError(error);
                    }
                });
    }
}

package com.xuyijie.module_login.presenter;

import com.example.module_library.base.BaseGson;
import com.example.module_library.base.BasePresenter;
import com.example.module_library.http.BaseObserver;
import com.xuyijie.module_login.contract.UserContract;
import com.xuyijie.module_login.model.UserModel;

import nico.stytool.gson_module.EmptyGson;
import nico.stytool.gson_module.UserGson;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserPresenter extends BasePresenter<UserContract.View> implements UserContract.Presenter {
    private UserModel userModel = new UserModel();

    public UserPresenter(UserContract.View mMvpView) {
        super(mMvpView);
    }

    @Override
    public void queryUserByTelPhone(String tel) {
        mMvpView.showDialog();
        userModel.queryUserByTelPhone(tel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> emptyGsonBaseGson) {
                        mMvpView.hideDialog();
                        if (emptyGsonBaseGson.isStatus()) {
                            mMvpView.getUserByTel(true);
                        } else {
                            mMvpView.getUserByTel(false);
                        }
                    }

                    @Override
                    public void onError(String error) {
                        mMvpView.hideDialog();
                    }
                });
    }

    @Override
    public void userLoginByUserName(String username, String password) {
        mMvpView.showDialog();
        userModel.userLoginByUserName(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> emptyGsonBaseGson) {
                        mMvpView.hideDialog();
                        if (emptyGsonBaseGson.isStatus()) {
                            mMvpView.userLoginByUserName(emptyGsonBaseGson.getSingleData());
                        } else {
                            mMvpView.showError(emptyGsonBaseGson.getMsg());
                        }
                    }

                    @Override
                    public void onError(String error) {
                        mMvpView.hideDialog();
                    }
                });
    }
}

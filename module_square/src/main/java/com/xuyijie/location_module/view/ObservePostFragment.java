package com.xuyijie.location_module.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.module_library.base.BaseFragment;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.weight.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuyijie.location_module.R;
import com.xuyijie.location_module.R2;
import com.xuyijie.location_module.adapter.UserPostAdapter;
import com.xuyijie.location_module.contract.UserPostContract;
import com.xuyijie.location_module.presenter.UserPostPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import nico.stytool.gson_module.PostGson;

public class ObservePostFragment extends BaseFragment<UserPostPresenter> implements UserPostContract.View {
    @BindView(R2.id.ry_purse)
    RecyclerView ryPurse;
    @BindView(R2.id.sml_purse)
    SmartRefreshLayout smlPurse;
    Unbinder unbinder;
    private UserPostAdapter userPostAdapter;
    @Override
    public void initData() {
        mPresenter.queryUserPost("", "");
        ryPurse.setLayoutManager(new LinearLayoutManager(getContext()));
        userPostAdapter = new UserPostAdapter(null,getContext());
        ryPurse.setAdapter(userPostAdapter);
    }

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_observepost;
    }

    @Override
    public UserPostPresenter initPresenter() {
        return new UserPostPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void queryUserPost(List<PostGson> postGsonList) {
        userPostAdapter.addData(postGsonList);
    }

    @Override
    public void submitUserPostByUid(boolean postGsonList) {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showDialog() {
        mshowDialog();
    }

    @Override
    public void hideDialog() {
        mhideDialog();
    }
}

package com.xuyijie.home_module.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.module_library.base.BaseFragment;
import com.example.module_library.util.SharePreferenceUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xuyijie.home_module.R;
import com.xuyijie.home_module.R2;
import com.xuyijie.home_module.adapter.HotUserAdapter;
import com.xuyijie.home_module.adapter.LocationAdapter;
import com.xuyijie.home_module.contract.HomePageFragmentContract;
import com.xuyijie.home_module.presenter.HomePageFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import nico.stytool.gson_module.UserGson;

public class PurseUserFragment extends BaseFragment<HomePageFragmentPresenter> implements HomePageFragmentContract.View {
    HotUserAdapter locationAdapter;
    @BindView(R2.id.ry_purse)
    RecyclerView ryPurse;
    @BindView(R2.id.sml_purse)
    SmartRefreshLayout smlLocation;
    Unbinder unbinder;
    private int page = 1;
    @Override
    public void initData() {

    }
    private boolean isFirst = true;
    private static final String TAG = "LocationFragment";
    private List<UserGson> userGsonLists = new ArrayList<>();
    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        locationAdapter = new HotUserAdapter(null);
        ryPurse.setAdapter(locationAdapter);
        locationAdapter.bindToRecyclerView(ryPurse);
        ryPurse.hasFixedSize();
        ryPurse.setLayoutManager(new LinearLayoutManager(getContext()));
        smlLocation.autoRefresh();
        smlLocation.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 1;
                isFirst = true;
                userGsonLists.clear();
                locationAdapter.notifyDataSetChanged();
                mPresenter.queryHotUserByLocation(String.valueOf(page));
            }
        });
        smlLocation.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page++;
                mPresenter.queryHotUserByLocation(String.valueOf(page));
            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_samecity;
    }

    @Override
    public HomePageFragmentPresenter initPresenter() {
        return new HomePageFragmentPresenter(this);
    }

    @Override
    public void queryAroundUserByLocation(List<UserGson> userGsonList) {

    }

    @Override
    public void queryHotUserByLocation(List<UserGson> userGsonList) {
        if (isFirst) {
            userGsonLists.addAll(userGsonList);
            locationAdapter.addData(userGsonLists);
            smlLocation.finishRefresh();
            Log.i(TAG, "queryAroundUserByLocation: " + userGsonList.size());
            isFirst = false;
        } else {
            userGsonLists.addAll(userGsonLists.size(), userGsonList);
            locationAdapter.replaceData(userGsonLists);
            smlLocation.finishLoadMore();
        }
    }
    @Override
    public void showError(String msg) {
        smlLocation.finishLoadMore();
        smlLocation.finishRefresh();
    }

    @Override
    public void showDialog() {
        mshowDialog();
    }

    @Override
    public void hideDialog() {
        mhideDialog();
        smlLocation.finishLoadMore();
        smlLocation.finishRefresh();
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
}

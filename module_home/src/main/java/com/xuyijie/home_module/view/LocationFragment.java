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
import com.xuyijie.home_module.adapter.LocationAdapter;
import com.xuyijie.home_module.contract.HomePageFragmentContract;
import com.xuyijie.home_module.presenter.HomePageFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import nico.stytool.gson_module.UserGson;

public class LocationFragment extends BaseFragment<HomePageFragmentPresenter> implements HomePageFragmentContract.View {
    LocationAdapter locationAdapter;
    @BindView(R2.id.ry_location)
    RecyclerView ryLocation;
    @BindView(R2.id.sml_location)
    SmartRefreshLayout smlLocation;
    Unbinder unbinder;
    private int page = 1;

    @Override
    public void initData() {
        locationAdapter = new LocationAdapter(null);
    }

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        ryLocation.hasFixedSize();
        ryLocation.setLayoutManager(new LinearLayoutManager(getContext()));
        locationAdapter = new LocationAdapter(null);
        smlLocation.autoRefresh();
        smlLocation.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 1;
                isFirst = true;
                userGsonLists.clear();
                ryLocation.removeAllViews();
                locationAdapter.notifyDataSetChanged();
                mPresenter.queryAroundUserByLocation(String.valueOf(SharePreferenceUtil.getUser("city","String")), String.valueOf(page));
            }
        });
        smlLocation.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page++;
                mPresenter.queryAroundUserByLocation(String.valueOf(SharePreferenceUtil.getUser("city","String")), String.valueOf(page));
            }
        });
    }

    private boolean isFirst = true;

    @Override
    public int initLayout() {
        return R.layout.fragment_location;
    }

    @Override
    public HomePageFragmentPresenter initPresenter() {
        return new HomePageFragmentPresenter(this);
    }

    private static final String TAG = "LocationFragment";
    private List<UserGson> userGsonLists = new ArrayList<>();

    @Override
    public void queryAroundUserByLocation(List<UserGson> userGsonList) {
        if (isFirst) {
            userGsonLists.addAll(userGsonList);
            locationAdapter.replaceData(userGsonLists);
            smlLocation.finishRefresh();
            ryLocation.setAdapter(locationAdapter);
            Log.i(TAG, "queryAroundUserByLocation: " + userGsonList.size());
            isFirst = false;
        } else {
            userGsonLists.addAll(userGsonLists.size(), userGsonList);
            locationAdapter.replaceData(userGsonLists);
            smlLocation.finishLoadMore();
        }

    }

    @Override
    public void queryHotUserByLocation(List<UserGson> userGsonList) {

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

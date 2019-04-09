package com.xuyijie.home_module.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_library.adapter.HomePagerAdapter;
import com.example.module_library.base.BaseFragment;
import com.example.module_library.config.RouterConfig;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.weight.ColorFlipPagerTitleView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.xuyijie.home_module.R;
import com.xuyijie.home_module.R2;


import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//@Route(path = RouterConfig.HOMEPAGE)
public class HomeFragment extends BaseFragment<EmptyPresenter> {
    MagicIndicator mgHome;
    ViewPager vpHome;
    Unbinder unbinder;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] title = {"附近的人", "同城交友"};



    private HomePagerAdapter homePagerAdapter;

    @Override
    public void initData() {
        fragmentList.add(new LocationFragment());
        fragmentList.add(new SameCityFragment());
        homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(), fragmentList);
        vpHome.setAdapter(homePagerAdapter);
        mgHome.setBackgroundColor(Color.parseColor("#ffffff"));
        CommonNavigator commonNavigator7 = new CommonNavigator(getContext());
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdjustMode(true);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return title == null ? 0 : title.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(title[index]);
                TextPaint tp = simplePagerTitleView.getPaint();
                tp.setFakeBoldText(true);
                simplePagerTitleView.setTextSize(17);
                simplePagerTitleView.setNormalColor(Color.parseColor("#8a8a8a"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#000000"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vpHome.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 6));
                indicator.setLineWidth(UIUtil.dip2px(context, 30));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#FDDF4D"));
                return indicator;
            }
        });
        mgHome.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(mgHome, vpHome);
    }

    @Override
    public void initView(View view) {
        mgHome = view.findViewById(R.id.mg_home);
        vpHome = view.findViewById(R.id.vp_home);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public EmptyPresenter initPresenter() {
        return null;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

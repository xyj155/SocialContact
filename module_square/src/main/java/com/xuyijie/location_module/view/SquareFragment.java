package com.xuyijie.location_module.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.module_library.adapter.HomePagerAdapter;
import com.example.module_library.base.BaseFragment;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.weight.ColorFlipPagerTitleView;
import com.example.module_library.weight.StickScrollView;
import com.xuyijie.location_module.R;
import com.xuyijie.location_module.R2;
import com.zhouwei.mzbanner.holder.MZViewHolder;

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
public class SquareFragment extends BaseFragment<EmptyPresenter> implements StickScrollView.OnScrollListener {
    @BindView(R2.id.mg_square)
    MagicIndicator mgSquare;
    @BindView(R2.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] title = {"推荐", "附件动态"};
    private LinearLayout mTopTabViewLayout;
    /**
     * 跟随ScrollView的TabviewLayout
     */
    private LinearLayout mTabViewLayout;

    /**
     * 要悬浮在顶部的View的子View
     */
    private LinearLayout mTopView;


    @Override
    public void initData() {
        fragmentList.add(new PursePostFragment());
        fragmentList.add(new SameCityFragment());
        homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(), fragmentList);
        viewpager.setAdapter(homePagerAdapter);
        mgSquare.setBackgroundColor(Color.parseColor("#ffffff"));
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
                        viewpager.setCurrentItem(index);
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
        mgSquare.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(mgSquare, viewpager);
//        mzSquare.setDelayedTime(5000);
//        List<String> stringList = new ArrayList<>();
//        stringList.add("https://img.zcool.cn/community/019c745ca5c33ca8012141686fc0ca.jpg@1280w_1l_2o_100sh.jpg");
//        stringList.add("https://img.zcool.cn/community/0174fd5ca5c33ca801208f8b098680.jpg@1280w_1l_2o_100sh.jpg");
//        stringList.add("https://img.zcool.cn/community/0115995ca5c3e4a80121416894cd6b.jpg@1280w_1l_2o_100sh.jpg");
//        stringList.add("https://img.zcool.cn/community/0165975ca5c3e4a801208f8bccac21.png@1280w_1l_2o_100sh.png");
//        stringList.add("https://img.zcool.cn/community/0150bb5ca5c3e4a80121416842c21e.jpg@1280w_1l_2o_100sh.jpg");
//        mzSquare.setPages(stringList, new MZHolderCreator() {
//            @Override
//            public MZViewHolder createViewHolder() {
//                return new MZBannerHolder();
//            }
//        });
//        mzSquare.start();

    }
    private HomePagerAdapter homePagerAdapter;
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

    private class MZBannerHolder implements MZViewHolder<String> {
        ImageView imageView;

        @Override
        public View createView(Context context) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.mz_banner_layout, null);
            imageView = inflate.findViewById(R.id.iv_banner);
            return inflate;
        }

        @Override
        public void onBind(Context context, int i, String s) {
            Glide.with(context).asBitmap().load(s).into(imageView);
        }
    }


    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
//        StickScrollView mMyScrollView = (StickScrollView) view.findViewById(R.id.my_scrollview);
//        mTabViewLayout = (LinearLayout) view.findViewById(R.id.ll_tabView);
//        mTopTabViewLayout = (LinearLayout) view.findViewById(R.id.ll_tabTopView);
//        mTopView = (LinearLayout) view.findViewById(R.id.tv_topView);
//        //滑动监听
//        mMyScrollView.setOnScrollListener(this);
//        GlideUtil.loadRoundCornerImage(R.drawable.img_home_entry_couple,(ImageView) view.findViewById(R.id.iv_voice));
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_square;
    }

    @Override
    public EmptyPresenter initPresenter() {
        return null;
    }

    @Override
    public void onScroll(int scrollY) {
//        int mHeight = mTabViewLayout.getTop();
//        //判断滑动距离scrollY是否大于0，因为大于0的时候就是可以滑动了，此时mTabViewLayout.getTop()才能取到值。
//        if (scrollY > 0 && scrollY >= mHeight) {
//            if (mTopView.getParent() != mTopTabViewLayout) {
//                mTabViewLayout.removeView(mTopView);
//                mTopTabViewLayout.addView(mTopView);
//            }
//
//        } else {
//            if (mTopView.getParent() != mTabViewLayout) {
//                mTopTabViewLayout.removeView(mTopView);
//                mTabViewLayout.addView(mTopView);
//            }
//
//        }

    }
}

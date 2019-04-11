package com.xuyijie.location_module.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
public class SquareFragment extends BaseFragment<EmptyPresenter> implements View.OnTouchListener, StickScrollView.OnScrollListener, View.OnClickListener {
    @BindView(R2.id.mg_square)
    MagicIndicator mgSquare;
    @BindView(R2.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;
    @BindView(R2.id.rl_couple)
    RelativeLayout rlCouple;
    @BindView(R2.id.rl_circle)
    RelativeLayout rlCircle;
    @BindView(R2.id.rl_quiz)
    RelativeLayout rlQuiz;
    @BindView(R2.id.rl_voice)
    RelativeLayout rlVoice;
    Unbinder unbinder1;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] title = {"推荐", "附件动态", "关注"};

    private CoordinatorLayout cdSquare;
    private AppBarLayout alSquare;
    private SimplePagerTitleView simplePagerTitleView;

    @Override
    public void initData() {
        fragmentList.add(new PursePostFragment());
        fragmentList.add(new SameCityFragment());
        fragmentList.add(new ObservePostFragment());
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
                simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(title[index]);
                TextPaint tp = simplePagerTitleView.getPaint();
                tp.setFakeBoldText(true);
                simplePagerTitleView.setTextSize(22);
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
        viewpager.setOffscreenPageLimit(3);

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
        rlCouple.setOnClickListener(this);
        rlCircle.setOnClickListener(this);
        rlQuiz.setOnClickListener(this);
        rlVoice.setOnClickListener(this);
//        rlCouple.setOnTouchListener(this);
//        rlCircle.setOnTouchListener(this);
//        rlQuiz.setOnTouchListener(this);
//        rlVoice.setOnTouchListener(this);
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                int id1 = view.getId();
                if ( id1==  R.id.rl_couple) {
                    rlCouple.setScaleX((float) 0.85);
                    rlCouple.setScaleY((float) 0.85);
                }else if (id1==R.id.rl_circle){
                    rlCircle.setScaleX((float) 0.85);
                    rlCircle.setScaleY((float) 0.85);
                }else if (id1==R.id.rl_quiz){
                    rlQuiz.setScaleX((float) 0.85);
                    rlQuiz.setScaleY((float) 0.85);
                }else if (id1==R.id.rl_voice){
                    rlVoice.setScaleX((float) 0.85);
                    rlVoice.setScaleY((float) 0.85);
                }
                break;
            case MotionEvent.ACTION_UP:
                int id = view.getId();
                if (id == R.id.rl_couple) {
                    rlCouple.setScaleX(1);
                    rlCouple.setScaleY(1);
                }else if (id==R.id.rl_circle){
                    rlCircle.setScaleX(1);
                    rlCircle.setScaleY(1);
                }else if (id==R.id.rl_quiz){
                    rlQuiz.setScaleX(1);
                    rlQuiz.setScaleY(1);
                }else if (id==R.id.rl_voice){
                    rlVoice.setScaleX(1);
                    rlVoice.setScaleY(1);
                }
                break;
        }
        return false;
    }
    private void viewUp(final View v, final Class c){
        v.setScaleX((float) 0.85);
        v.setScaleY((float) 0.85);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setScaleX(1);
                v.setScaleY(1);
                startActivity(new Intent(getContext(),c));
            }
        },100);
    }




    private HomePagerAdapter homePagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        int id1 = v.getId();
        if ( id1==  R.id.rl_couple) {
//            rlCouple.setScaleX((float) 0.85);
//            rlCouple.setScaleY((float) 0.85);
            viewUp(rlCouple,UserMatchActivity.class);
        }else if (id1==R.id.rl_circle){
//            rlCircle.setScaleX((float) 0.85);
            viewUp(rlCircle,UserMatchActivity.class);
//            rlCircle.setScaleY((float) 0.85);
        }else if (id1==R.id.rl_quiz){
//            rlQuiz.setScaleX((float) 0.85);
            viewUp(rlQuiz,UserMatchActivity.class);
//            rlQuiz.setScaleY((float) 0.85);
        }else if (id1==R.id.rl_voice){
            viewUp(rlVoice,UserMatchActivity.class);
//            rlVoice.setScaleX((float) 0.85);
//            rlVoice.setScaleY((float) 0.85);
        }
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

    private static final String TAG = "SquareFragment";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView(View view) {
        cdSquare = view.findViewById(R.id.cd_square);
        alSquare = view.findViewById(R.id.al_square);
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

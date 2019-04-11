package com.xuyijie.user_module.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.module_library.base.BaseFragment;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.util.GlideUtil;
import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_library.weight.CircleImageView;
import com.xuyijie.user_module.R;
import com.xuyijie.user_module.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class UserFragment extends BaseFragment<EmptyPresenter> {
    @BindView(R2.id.iv_setting)
    ImageView ivSetting;
    @BindView(R2.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R2.id.tv_username)
    TextView tvUsername;
    @BindView(R2.id.rl_user)
    RelativeLayout rlUser;
    @BindView(R2.id.tv_id)
    TextView tvId;
    @BindView(R2.id.tv_signature)
    TextView tvSignature;
    @BindView(R2.id.view_line)
    View viewLine;
    @BindView(R2.id.ll_post)
    LinearLayout llPost;
    @BindView(R2.id.ry_post)
    RecyclerView ryPost;
    Unbinder unbinder;
    @BindView(R2.id.iv_sex)
    ImageView ivSex;
    @BindView(R2.id.tv_sex)
    TextView tvSex;
    Unbinder unbinder1;

    @Override
    public void initData() {

    }

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        GlideUtil.loadRoundCornerImage(SharePreferenceUtil.getUser("avatar", "String"), ivAvatar);
        tvUsername.setText((String) SharePreferenceUtil.getUser("username", "String"));
        tvSignature.setText((String) SharePreferenceUtil.getUser("signature", "String"));
        String user = (String) SharePreferenceUtil.getUser("sex", "String");
        if (user.equals("1")) {
            GlideUtil.loadRoundCornerImage(R.mipmap.ic_male_blue, ivSex);
            tvSex.setText("男  ·  "+ SharePreferenceUtil.getUser("constellation", "String"));
        }else {
            GlideUtil.loadRoundCornerImage(R.mipmap.ic_female_pink, ivSex);
            tvSex.setText("女  ·  "+ SharePreferenceUtil.getUser("constellation", "String"));
        }

    }

    @Override
    public int initLayout() {
        return R.layout.fragment_user;
    }

    @Override
    public EmptyPresenter initPresenter() {
        return null;
    }

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

    @OnClick({R2.id.iv_setting, R2.id.tv_signature})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.iv_setting) {
            startActivity(new Intent(getContext(), UserSettingActivity.class));
        } else if (i == R.id.tv_signature) {
        }
    }
}

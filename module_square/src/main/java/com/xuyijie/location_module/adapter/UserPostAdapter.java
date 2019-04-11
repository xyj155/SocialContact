package com.xuyijie.location_module.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_library.util.GlideUtil;
import com.xuyijie.location_module.R;

import java.util.List;

import nico.stytool.gson_module.PostGson;

public class UserPostAdapter extends BaseQuickAdapter<PostGson, BaseViewHolder> {
    private Context context;

    public UserPostAdapter(@Nullable List<PostGson> data, Context context) {
        super(R.layout.square_post_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PostGson item) {
        PictureAdapter pictureAdapter=new PictureAdapter(item.getPicture());
        RecyclerView ryPicture = helper.getView(R.id.ry_picture);
        ryPicture.setLayoutManager(new GridLayoutManager(context, 3));
        ryPicture.setAdapter(pictureAdapter);
        helper.setText(R.id.tv_username, item.getUser().getUsername())
                .setText(R.id.tv_age, item.getUser().getAge());
        GlideUtil.loadRoundCornerImage(item.getUser().getAvatar(), (ImageView) helper.getView(R.id.iv_avatar));
        if (item.getUser().getSex().equals("1")) {
            GlideUtil.loadRoundCornerImage(R.mipmap.ic_male_blue, (ImageView) helper.getView(R.id.iv_sex));
        } else {
            GlideUtil.loadRoundCornerImage(R.mipmap.ic_female_pink, (ImageView) helper.getView(R.id.iv_sex));
        }
        helper.setText(R.id.tv_time, item.getDateTime())
                .setText(R.id.tv_content, item.getPostTitle());
    }

    private class PictureAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PictureAdapter(@Nullable List<String> data) {
            super(R.layout.square_image_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            GlideUtil.loadRoundCornerAvatarImage(item,(ImageView) helper.getView(R.id.iv_picture),10);
        }
    }
}

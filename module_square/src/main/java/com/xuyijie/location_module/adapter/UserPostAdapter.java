package com.xuyijie.location_module.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_library.MyApp;
import com.example.module_library.baseactivity.MPhotoPreviewActivity;
import com.example.module_library.entity.PhotoEntity;
import com.example.module_library.util.GlideUtil;
import com.xuyijie.location_module.R;
import com.zzti.fengyongge.imagepicker.model.PhotoModel;
import com.zzti.fengyongge.imagepicker.util.CommonUtils;

import java.util.ArrayList;
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
        PictureAdapter pictureAdapter = new PictureAdapter(item.getPicture());
        RecyclerView ryPicture = helper.getView(R.id.ry_picture);
        if (item.getPicture().size()>0){
            ryPicture.setLayoutManager(new GridLayoutManager(context, item.getPicture().size() > 2 ? 3 : 2));
            ryPicture.setAdapter(pictureAdapter);

        }

        helper.setText(R.id.tv_username, item.getUser().getUsername())
                .setText(R.id.tv_age, item.getUser().getAge());
        GlideUtil.loadRoundCornerImage(item.getUser().getAvatar(), (ImageView) helper.getView(R.id.iv_avatar));
        if (item.getUser().getSex().equals("1")) {
            GlideUtil.loadRoundCornerImage(R.mipmap.ic_male_blue, (ImageView) helper.getView(R.id.iv_sex));
        } else {
            GlideUtil.loadRoundCornerImage(R.mipmap.ic_female_pink, (ImageView) helper.getView(R.id.iv_sex));
        }
        helper.setText(R.id.tv_time, item.getDateTime())
                .setText(R.id.tv_content, item.getPostTitle())
                .setText(R.id.tv_comment, item.getCommentCount())
                .setText(R.id.tv_like, item.getThumbCount());
    }

    private class PictureAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        ArrayList<PhotoEntity> selected = new ArrayList<PhotoEntity>();
        public PictureAdapter(@Nullable List<String> data) {
            super(R.layout.square_image_item, data);
            for (String path: data ) {
                selected.add(new PhotoEntity(path));
            }
        }

        @Override
        protected void convert(BaseViewHolder helper, final String item) {
            RoundedCorners roundedCorners = new RoundedCorners(10);
            RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300).centerCrop();
            Glide.with(MyApp.getInstance()).asBitmap().apply(options).load(item).into((ImageView) helper.getView(R.id.iv_picture));
            helper.setOnClickListener(R.id.iv_picture, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected.add(new PhotoEntity(item));
                    Log.i(TAG, "onClick: "+selected);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("photos", selected);
                    CommonUtils.launchActivity(context, MPhotoPreviewActivity.class, bundle);
                }
            });
        }
    }
}

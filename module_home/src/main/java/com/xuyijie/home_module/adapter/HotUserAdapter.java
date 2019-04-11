package com.xuyijie.home_module.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_library.entity.GeoPoint;
import com.example.module_library.util.DistanceUtil;
import com.example.module_library.util.GlideUtil;
import com.example.module_library.util.SharePreferenceUtil;
import com.xuyijie.home_module.R;

import java.io.Serializable;
import java.util.List;

import nico.stytool.gson_module.UserGson;

public class HotUserAdapter extends BaseQuickAdapter<UserGson, BaseViewHolder> {
    public HotUserAdapter(@Nullable List<UserGson> data) {
        super(R.layout.purseuser_ry_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserGson item) {
        String  serializable = Integer.valueOf(item.getHot())  > 1000 ? String.valueOf(Integer.valueOf(item.getHot())/1000)+"k"  : String.valueOf(Integer.valueOf(item.getHot()) );
        helper.setText(R.id.tv_signature, item.getSignature())
                .setText(R.id.tv_username, item.getUsername())
                .setText(R.id.iv_rank,"+ "+ serializable+"热度")
                .setText(R.id.tv_city,item.getCity())
                .setText(R.id.tv_signature, item.getSignature().isEmpty() ? "该用户还没有任何自我评价！" : item.getSignature())
                .setText(R.id.tv_age, item.getAge() + "\t·\t");
        Log.i(TAG, "convert:getLayoutPosition "+helper.getPosition());

        GlideUtil.BaseGlide(item.getAvatar(), (ImageView) helper.getView(R.id.iv_avatar));
        if (item.getSex().equals("1")) {
            GlideUtil.loadGeneralImageWithAnim(R.mipmap.ic_boy, (ImageView) helper.getView(R.id.iv_sex));
        } else {
            GlideUtil.loadGeneralImageWithAnim(R.mipmap.ic_girl, (ImageView) helper.getView(R.id.iv_sex));
        }
    }
}

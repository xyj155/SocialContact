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

import java.util.List;

import nico.stytool.gson_module.UserGson;

public class LocationAdapter extends BaseQuickAdapter<UserGson, BaseViewHolder> {
    public LocationAdapter(@Nullable List<UserGson> data) {
        super(R.layout.location_ry_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserGson item) {
        helper.setText(R.id.tv_signature, item.getSignature())
                .setText(R.id.tv_username, item.getUsername())

                .setText(R.id.tv_signature, item.getSignature().isEmpty() ? "该用户还没有任何自我评价！" : item.getSignature())
                .setText(R.id.tv_age, item.getAge() + "\t·\t");
        String  latitude = (String) SharePreferenceUtil.getUser("latitude", "String");
        String  longitude = (String) SharePreferenceUtil.getUser("longitude", "String");
        Log.i(TAG, "convert: "+latitude+"----"+longitude);
        double distance = DistanceUtil.getDistance(new GeoPoint(Double.valueOf(latitude),Double.valueOf(longitude) ), new GeoPoint( Double.valueOf(item.getLatitude()),Double.valueOf(item.getLongitude()) ));
        helper.setText(R.id.tv_city, (int)distance<=1000?(int)distance+" m":(int)(distance/1000)+" km");
        GlideUtil.BaseGlide(item.getAvatar(), (ImageView) helper.getView(R.id.iv_avatar));
        if (item.getSex().equals("1")) {
            GlideUtil.loadGeneralImageWithAnim(R.mipmap.ic_boy, (ImageView) helper.getView(R.id.iv_sex));
        } else {
            GlideUtil.loadGeneralImageWithAnim(R.mipmap.ic_girl, (ImageView) helper.getView(R.id.iv_sex));
        }
    }
}

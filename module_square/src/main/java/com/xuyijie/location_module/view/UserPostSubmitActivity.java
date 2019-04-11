package com.xuyijie.location_module.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.module_library.base.BaseActivity;
import com.example.module_library.logic.contract.EmptyContract;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.xuyijie.location_module.R;
import com.xuyijie.location_module.R2;
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.MainActivity;

public class UserPostSubmitActivity extends BaseActivity<EmptyContract.View, EmptyPresenter> {


    @BindView(R2.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R2.id.et_content)
    EditText etContent;
    @BindView(R2.id.iv_camera)
    ImageView ivCamera;
    @BindView(R2.id.iv_audio)
    ImageView ivAudio;

    @Override
    public boolean isSetStatusBarTranslucent() {
        return true;
    }

    @Override
    public EmptyPresenter getPresenter() {
        return null;
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_user_post_submit;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.iv_camera, R2.id.iv_audio})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.iv_camera) {
            Intent intent = new Intent(UserPostSubmitActivity.this, PhotoSelectorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra("limit", 6 );//number是选择图片的数量
            startActivityForResult(intent, 0);
        } else if (i == R.id.iv_audio) {
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data != null) {
                    List<String> paths = (List<String>) data.getExtras().getSerializable("photos");//path是选择拍照或者图片的地址数组
                    //处理代码
                    Log.i(TAG, "onActivityResult: "+paths.get(0));
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

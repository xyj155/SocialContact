package com.xuyijie.location_module.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.module_library.base.BaseActivity;
import com.example.module_library.logic.contract.EmptyContract;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.util.FullyGridLayoutManager;
import com.example.module_library.util.RxPartMapUtils;
import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_library.weight.toast.ToastUtils;
import com.xuyijie.location_module.R;
import com.xuyijie.location_module.R2;
import com.xuyijie.location_module.adapter.GridImageAdapter;
import com.xuyijie.location_module.contract.UserPostContract;
import com.xuyijie.location_module.presenter.UserPostPresenter;
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.MainActivity;
import nico.stytool.gson_module.PostGson;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserPostSubmitActivity extends BaseActivity<UserPostContract.View, UserPostPresenter> implements UserPostContract.View {
    private List<String> selectList = new ArrayList<>();
    @BindView(R2.id.ry_picture)
    RecyclerView recyclerView;
    private GridImageAdapter adapter;

    @BindView(R2.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R2.id.et_content)
    EditText etContent;
    @BindView(R2.id.tv_submit)
    TextView tvSubmit;
    @BindView(R2.id.iv_camera)
    ImageView ivCamera;
    @BindView(R2.id.iv_audio)
    ImageView ivAudio;

    @Override
    public boolean isSetStatusBarTranslucent() {
        return true;
    }

    @Override
    public UserPostPresenter getPresenter() {
        return new UserPostPresenter(this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_user_post_submit;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(UserPostSubmitActivity.this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(UserPostSubmitActivity.this, new GridImageAdapter.onAddPicClickListener() {
            @Override
            public void onAddPicClick() {
                Intent intent = new Intent(UserPostSubmitActivity.this, PhotoSelectorActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("limit", 9);//number是选择图片的数量
                startActivityForResult(intent, 0);
            }
        });
        adapter.setList(selectList);
        adapter.setSelectMax(9);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    String media = selectList.get(position);
                    Intent intent = new Intent(UserPostSubmitActivity.this, PhotoSelectorActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.putExtra("limit", 9);//number是选择图片的数量
                    startActivityForResult(intent, 0);
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R2.id.tv_submit, R2.id.iv_camera, R2.id.iv_audio})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.iv_camera) {
            Intent intent = new Intent(UserPostSubmitActivity.this, PhotoSelectorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra("limit", 9);//number是选择图片的数量
            startActivityForResult(intent, 0);
        } else if (i == R.id.iv_audio) {

        } else if (i == R.id.tv_submit) {
            final Map<String, RequestBody> partMap = new HashMap<>();
            partMap.put("uid", RxPartMapUtils.toRequestBodyOfText(String.valueOf(SharePreferenceUtil.getUser("uid", "String"))));
            partMap.put("content", RxPartMapUtils.toRequestBodyOfText(etContent.getText().toString()));
            partMap.put("mood", RxPartMapUtils.toRequestBodyOfText("不错"));
            partMap.put("location",RxPartMapUtils.toRequestBodyOfText(String.valueOf(SharePreferenceUtil.getUser("city","String"))) );
            final List<MultipartBody.Part> Imagelist = new ArrayList<>();
            if (paths.size() > 0) {
                partMap.put("ispic", RxPartMapUtils.toRequestBodyOfText("1"));
                for (Object media : paths) {
                    File file = new File(media.toString());
                    Log.i(TAG, "onViewClicked: " + file.getName());
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                    MultipartBody.Part formData = MultipartBody.Part.createFormData("image[]", file.getName(), requestBody);
                    Imagelist.add(formData);
                }
            } else {
                partMap.put("ispic", RxPartMapUtils.toRequestBodyOfText("0"));
            }

            mPresenter.submitUserPostByUid(partMap, Imagelist);
        }
    }

    private List<String> paths = new ArrayList<>();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data != null) {
                    List<String> photos = (List<String>) data.getExtras().getSerializable("photos");
                    paths.addAll(photos);//path是选择拍照或者图片的地址数组
                    adapter.setList(paths);
                    adapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void queryUserPost(List<PostGson> postGsonList) {

    }

    @Override
    public void submitUserPostByUid(boolean postGsonList) {
        if (postGsonList) {
            ToastUtils.show("提交成功，等待审核");
            finish();
        } else {
            ToastUtils.show("提交失败");
        }
    }

    @Override
    public void showError(String msg) {
        ToastUtils.show("提交错误：" + msg);
    }

    @Override
    public void showDialog() {
        createDialog();
    }

    @Override
    public void hideDialog() {
        mhideDialog();
    }
}

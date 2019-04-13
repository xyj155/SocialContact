package com.example.module_library.baseactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.module_library.entity.PhotoEntity;
import com.example.module_library.util.GlideUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zzti.fengongge.imagepicker.R;
import com.zzti.fengyongge.imagepicker.model.PhotoModel;
import com.zzti.fengyongge.imagepicker.util.ImageUtils;


public class BasePhotoPreview extends LinearLayout implements OnClickListener {
    private ProgressBar pbLoading;
    private ImageView ivContent;
    private OnClickListener l;
    private View save_bt;
    private String path;
    private Context cxt;
    private Bitmap loadedBitamap;
    private View inflate;

    public BasePhotoPreview(Context context) {
        super(context);
        this.cxt = context;
        inflate = LayoutInflater.from(context).inflate(
                R.layout.view_photopreview, this, true);

        pbLoading = (ProgressBar) findViewById(R.id.pb_loading_vpp);
        ivContent = (ImageView) findViewById(R.id.iv_content_vpp);
        save_bt = findViewById(R.id.save_bt);
        ivContent.setOnClickListener(this);
        save_bt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ImageUtils.loadImage(cxt, path);
                Toast.makeText(cxt, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public BasePhotoPreview(Context context, AttributeSet attrs, int defStyle) {
        this(context);
    }

    public BasePhotoPreview(Context context, AttributeSet attrs) {
        this(context);
    }

    public void loadImage(PhotoEntity photoModel) {
        Log.i(TAG, "loadImage: "+photoModel.getOriginalPath());
		GlideUtil.BaseGlide(photoModel.getOriginalPath(),ivContent);
//		if (is_save) {
//			save_bt.setVisibility(View.VISIBLE);
//			loadImage(photoModel.getOriginalPath());
//		} else {
//			save_bt.setVisibility(View.GONE);
//        loadImage(photoModel.getOriginalPath());
//		}

    }

    private static final String TAG = "BasePhotoPreview";

    private void loadImage(String path) {
        this.path = path;
		Log.i(TAG, "loadImage: "+path);
//		DisplayImageOptions options = new DisplayImageOptions.Builder()
//				.cacheInMemory(true)//让图片进行内存缓存
//				.cacheOnDisk(true)//让图片进行sdcard缓存
//				.build();
//		ImageLoader.getInstance().displayImage(path,ivContent,options);
        GlideUtil.BaseGlide(path, ivContent);
//		ImageLoader.getInstance().loadImage(path,
//				new SimpleImageLoadingListener() {
//					@Override
//					public void onLoadingComplete(String imageUri, View view,
//							Bitmap loadedImage) {
//						pbLoading.setVisibility(View.GONE);
//						loadedBitamap = loadedImage;
//						ivContent.setImageBitmap(loadedImage);
//					}
//
//					@Override
//					public void onLoadingFailed(String imageUri, View view,
//							FailReason failReason) {
//						ivContent.setImageDrawable(getResources().getDrawable(
//								R.drawable.ic_picture_loadfailed));
//						pbLoading.setVisibility(View.GONE);
//					}
//				});
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        this.l = l;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_content_vpp && l != null) {
            l.onClick(ivContent);
        }
    }

}

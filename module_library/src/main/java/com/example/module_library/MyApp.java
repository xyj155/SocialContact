package com.example.module_library;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;

import com.example.module_library.config.ApplicationInitial;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

import io.rong.imkit.MainActivity;

public class MyApp extends Application {
    public static MyApp instance;

    public static MyApp getInstance() {
        if (instance == null) {
            return new MyApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ApplicationInitial applicationInitial = new ApplicationInitial();
        applicationInitial.initAliBabaRouter()
                .initToast()
                .initRongIM()
                .initCrash()
                .initTencentQQ()
                .initImageLoader();

    }




}

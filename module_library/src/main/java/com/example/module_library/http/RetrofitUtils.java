package com.example.module_library.http;

import android.util.Log;


import com.example.module_library.api.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtils {
    public static final String BASE_URL = "http://120.79.5.238";
    /**
     * 超时时间
     */
    public static final int TIMEOUT = 60;
    private static volatile RetrofitUtils mInstance;
    private Retrofit mRetrofit;

    public static RetrofitUtils getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils();
                }
            }
        }
        return mInstance;
    }

    private RetrofitUtils() {
        initRetrofit();
    }

    /**
     * 初始化Retrofit
     */
    private void initRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置超时
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();
        mRetrofit = new Retrofit.Builder()
                // 设置请求的域名
                .baseUrl(BASE_URL)
                // 设置解析转换工厂，用自己定义的
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
    private  synchronized Retrofit getRetrofit(String url){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

                Log.d("xxx", message);
            }
        });

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder ok = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5000, TimeUnit.SECONDS);

        if (mRetrofit==null){
            mRetrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return mRetrofit;
    }
    private static Api apiService;

    /**
     * 创建API
     */
//    public <T> T create(String url,Class<T> cl){
//        Retrofit retrofit = getRetrofit(url);
//        return retrofit.create(cl);
//    }
    public Api create() {
//        apiService = mRetrofit.create(clazz);
        apiService = mRetrofit.create(Api.class);
        return apiService;
    }
}

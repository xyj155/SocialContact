package com.example.module_library.api;



import com.example.module_library.base.BaseGson;

import nico.stytool.gson_module.EmptyGson;
import nico.stytool.gson_module.FoodsGson;
import nico.stytool.gson_module.PostGson;
import nico.stytool.gson_module.SnackChildGson;
import nico.stytool.gson_module.SnackGson;
import nico.stytool.gson_module.SnackKindGson;
import nico.stytool.gson_module.SnackShopCarGson;
import nico.stytool.gson_module.UserGson;
import nico.stytool.gson_module.UserReceiveAddressGson;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface Api {

    @GET("/SocialConnect/public/index.php/index/Social/queryAroundUserByLocation")
    Observable<BaseGson<UserGson>> queryAroundUserByLocation(@Query("city") String  city,@Query("page") String  page);

    @GET("/SocialConnect/public/index.php/index/Social/queryHotUserByLocation")
    Observable<BaseGson<UserGson>> queryHotUserByLocation(@Query("page") String  page);
    @GET("/SocialConnect/public/index.php/index/Post/queryUserPost")
    Observable<BaseGson<PostGson>> queryUserPost(@Query("city") String  city, @Query("page") String  page);

    @GET("/SocialConnect/public/index.php/index/User/queryUserByTelPhone")
    Observable<BaseGson<EmptyGson>> queryUserByTelPhone(@Query("telphone") String  telphone);

    @GET("/SocialConnect/public/index.php/index/User/userLoginByUserName")
    Observable<BaseGson<UserGson>> userLoginByUserName(@Query("username") String  username,@Query("password") String  password);

}

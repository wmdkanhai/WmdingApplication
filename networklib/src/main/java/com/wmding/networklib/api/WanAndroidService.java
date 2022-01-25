package com.wmding.networklib.api;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author wmding
 * @date 1/25/22 9:45 PM
 * @describe
 */
public interface WanAndroidService {

    //获取公众号列表，https://wanandroid.com/wxarticle/chapters/json
    @GET("wxarticle/chapters/json")
    Call<HashMap> getwxArticleData();

    @POST("user/login")
    Call<HashMap> login(@Body HashMap data);
}

package com.wmding.networklib.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author wmding
 * @date 1/25/22 6:06 PM
 * @describe 访问百度，返回String
 */
public interface BaiDuService {
    @GET("/")
    Call<ResponseBody> getData();
}

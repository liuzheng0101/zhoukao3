package com.example.mature.zhoukao1.net;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface RetrofitService {
    @POST
    @FormUrlEncoded
    Call<ResponseBody> doPost(@Url String url, HashMap<String,String> params);

    @GET
    Call<ResponseBody> doGet(@Url String url);
}

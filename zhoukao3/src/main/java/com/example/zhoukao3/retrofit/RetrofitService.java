package com.example.zhoukao3.retrofit;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitService {
    @GET
    Call<ResponseBody> doGet(@Url String url, @QueryMap HashMap<String,String> params);
    @GET
    Call<ResponseBody> doGetGet(@Url String url, @HeaderMap HashMap<String,String> params);
    @GET
    Call<ResponseBody> GetGet(@Url String url, @HeaderMap HashMap<String,String> params,@QueryMap HashMap<String,String> bparams);
}

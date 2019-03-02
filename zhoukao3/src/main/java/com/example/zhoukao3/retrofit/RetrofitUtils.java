package com.example.zhoukao3.retrofit;

import com.example.zhoukao3.api.Api;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static RetrofitUtils instance;
    private Retrofit retrofit;
    private OkHttpClient ok;
    private RetrofitUtils(){
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        ok=new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(loggingInterceptor)
                .readTimeout(5000,TimeUnit.SECONDS)
                .connectTimeout(5000,TimeUnit.SECONDS)
                .writeTimeout(5000,TimeUnit.SECONDS)
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl(Api.Base_Api)
                .addConverterFactory(GsonConverterFactory.create())
                .client(ok)
                .build();
    }
    public static RetrofitUtils getInstance() {
        if (instance==null){
            synchronized (RetrofitUtils.class){
                if (instance==null){
                    instance=new RetrofitUtils();
                }
            }
        }
        return instance;
    }
    public void GetGet(String url, HashMap<String,String> params,final RetrofitCallback callback){
        RetrofitService service=retrofit.create(RetrofitService.class);
        service.doGetGet(url,params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code()==200) {
                        String result = response.body().string();
                        if (callback!=null){
                            callback.onSuccess(result);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callback!=null){
                    callback.onFailure("网络异常");
                }
            }
        });
    }
    public void doGet(String url, HashMap<String,String> params, final RetrofitCallback callback){
        RetrofitService service=retrofit.create(RetrofitService.class);
        service.doGet(url,params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code()==200) {
                        String result = response.body().string();
                        if (callback!=null){
                            callback.onSuccess(result);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callback!=null){
                    callback.onFailure("网络异常");
                }
            }
        });
    }
}

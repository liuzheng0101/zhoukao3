package com.example.zk1.net;

import com.example.zk1.api.Api;

import java.io.IOException;

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
    private final Retrofit retrofit;
    private RetrofitUtils(){
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient ok=new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl(Api.BaseUrl)
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

    public void doGet(String url, final RetrofitCallabck callabck){
        RestrofitService service=retrofit.create(RestrofitService.class);
        service.doGet(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    if (callabck!=null){
                        try {
                            String string = response.body().string();
                            callabck.OnSuccess(string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callabck!=null){
                    callabck.OnFailure("网络不稳定,请稍后重试");
                }
            }
        });
    }
}

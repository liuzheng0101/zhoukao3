package com.example.day08.net;

import com.example.day08.api.Api;

import org.greenrobot.greendao.annotation.Id;

import java.io.IOException;

import javax.xml.parsers.FactoryConfigurationError;

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
    private OkHttpClient ok;
    private Retrofit retrofit;
    private RetrofitUtils(){
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        ok=new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .addInterceptor(loggingInterceptor)
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
    public void doGet(String url, final RetrofitCallback callback){
        RetrofitService service=retrofit.create(RetrofitService.class);
        service.doGet(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        String result = response.body().string();
                        if (callback != null) {
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

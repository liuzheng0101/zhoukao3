package com.example.zhoukao3.model;

import com.example.zhoukao3.api.Api;
import com.example.zhoukao3.contract.UserContract;
import com.example.zhoukao3.retrofit.RetrofitCallback;
import com.example.zhoukao3.retrofit.RetrofitUtils;

import java.util.HashMap;

public class UserModel implements UserContract.IUserModel {
    @Override
    public void getList(HashMap<String, String> params, final ModelCallback callback) {
        RetrofitUtils.getInstance().doGet(Api.List_Api, params, new RetrofitCallback() {
            @Override
            public void onSuccess(String result) {
                if (callback!=null){
                    callback.onSuccess(result);
                }
            }
            @Override
            public void onFailure(String error) {
                if (callback!=null){
                    callback.onFailure(error);
                }
            }
        });
    }

    public interface ModelCallback{
        void onSuccess(String result);
        void onFailure(String error);
    }
}

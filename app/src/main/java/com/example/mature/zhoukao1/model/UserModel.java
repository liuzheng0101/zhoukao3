package com.example.mature.zhoukao1.model;

import com.example.mature.zhoukao1.api.Api;
import com.example.mature.zhoukao1.contract.UserContract;
import com.example.mature.zhoukao1.net.RetrofitCallback;
import com.example.mature.zhoukao1.net.RetrofitUtils;

import java.util.HashMap;

public class UserModel implements UserContract.IUserModel {

    @Override
    public void getList(HashMap<String, String> params, final ModelCallback callback) {
        RetrofitUtils.getInstance().doGet(Api.Shop_Api, new RetrofitCallback() {
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
    @Override
    public void bannerList(HashMap<String, String> params, final ModelCallback callback) {
        RetrofitUtils.getInstance().doGet(Api.Banner_Api, new RetrofitCallback() {
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

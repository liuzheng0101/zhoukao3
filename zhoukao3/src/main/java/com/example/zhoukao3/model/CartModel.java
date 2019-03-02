package com.example.zhoukao3.model;

import com.example.zhoukao3.api.Api;
import com.example.zhoukao3.contract.CartContract;
import com.example.zhoukao3.retrofit.RetrofitCallback;
import com.example.zhoukao3.retrofit.RetrofitUtils;

import java.util.HashMap;

public class CartModel implements CartContract.ICartModel {
    @Override
    public void getList(HashMap<String, String> params, final ModelCallback callback) {
        RetrofitUtils.getInstance().GetGet(Api.Cart_Api, params, new RetrofitCallback() {
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
    public void getshopYiang(String id, ModelCallback callback) {

    }

    public interface ModelCallback{
        void onSuccess(String result);
        void onFailure(String error);
    }
}

package com.example.zk1.model;

import com.example.zk1.api.Api;
import com.example.zk1.contract.UserContract;
import com.example.zk1.net.RetrofitCallabck;
import com.example.zk1.net.RetrofitUtils;

public class UserModel implements UserContract.IUserModel {
    @Override
    public void getShopList(final ModelCallback callback) {
        RetrofitUtils.getInstance().doGet(Api.ShopListUrl, new RetrofitCallabck() {
            @Override
            public void OnSuccess(String result) {
                if (callback!=null){
                    callback.success(result);
                }
            }
            @Override
            public void OnFailure(String error) {
                if (callback!=null){
                    callback.failure(error);
                }
            }
        });
    }

    @Override
    public void getBannerList(final ModelCallback callback) {
        RetrofitUtils.getInstance().doGet(Api.BannerUrl, new RetrofitCallabck() {
            @Override
            public void OnSuccess(String result) {
                if (callback!=null){
                    callback.success(result);
                }
            }
            @Override
            public void OnFailure(String error) {
                if (callback!=null){
                    callback.failure(error);
                }
            }
        });
    }

    public interface ModelCallback{
        void success(String result);
        void failure(String error);
    }
}

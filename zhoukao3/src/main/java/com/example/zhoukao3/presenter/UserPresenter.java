package com.example.zhoukao3.presenter;

import com.example.zhoukao3.bean.UserInfo;
import com.example.zhoukao3.contract.UserContract;
import com.example.zhoukao3.model.UserModel;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class UserPresenter extends UserContract.IUserPresenter {
    private WeakReference<UserContract.IUserView> weakReference;
    private UserContract.IUserView view;
    private UserModel model;
    public UserPresenter(UserContract.IUserView view) {
        this.view = view;
        model=new UserModel();
        weakReference=new WeakReference<>(view);
    }
    @Override
    public void getList(HashMap<String, String> params) {
        model.getList(params, new UserModel.ModelCallback() {
            @Override
            public void onSuccess(String result) {
                if (view!=null){
                    UserInfo info=new Gson().fromJson(result,UserInfo.class);
                    view.onShopSuccess(info);
                }
            }
            @Override
            public void onFailure(String error) {
                if (view!=null){
                    view.onFailure(error);
                }
            }
        });
    }
}

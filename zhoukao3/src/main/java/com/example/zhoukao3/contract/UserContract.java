package com.example.zhoukao3.contract;

import com.example.zhoukao3.bean.UserInfo;
import com.example.zhoukao3.model.UserModel;

import java.util.HashMap;

public interface UserContract {
    public abstract class IUserPresenter{
        public abstract void getList(HashMap<String,String> params);
    }
    public interface IUserModel{
        void getList(HashMap<String,String> params, UserModel.ModelCallback callback);
    }
    public interface IUserView{
        void onShopSuccess(UserInfo info);
        void onFailure(String error);
    }
}

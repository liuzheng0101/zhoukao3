package com.example.day08.contract;

import com.example.day08.model.UserModel;

import java.util.HashMap;

public interface UserContract {
    public abstract class IUserPresenter{
        public abstract void getList(HashMap<String,String> params);
    }
    public interface IUserModel{
        void getList(HashMap<String,String> params, UserModel.ModelCallback callback);
    }
    public interface IUseView{
        void onSuccess(String result);
        void onFailure(String error);
    }
}

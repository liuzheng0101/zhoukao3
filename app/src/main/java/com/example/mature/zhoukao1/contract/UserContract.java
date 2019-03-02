package com.example.mature.zhoukao1.contract;

import com.example.mature.zhoukao1.bean.BannerInfo;
import com.example.mature.zhoukao1.bean.ShopInfo;
import com.example.mature.zhoukao1.model.UserModel;

import java.util.HashMap;
import java.util.List;

public interface UserContract {
    public abstract class UserPresenter{
        public abstract void getList(HashMap<String,String> params);
        public abstract void bannerList(HashMap<String,String> params);
    }
    public interface IUserModel{
        public void getList(HashMap<String,String> params, UserModel.ModelCallback callback);
        public void bannerList(HashMap<String,String> params, UserModel.ModelCallback callback);
    }
    public interface IUserView{
        void ShopSuccess(List<ShopInfo.ResultBean.listBean> info);
        void BannerSuccess(BannerInfo info);
        void failure(String error);
    }
}

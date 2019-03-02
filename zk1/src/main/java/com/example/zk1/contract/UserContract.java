package com.example.zk1.contract;

import com.example.zk1.bean.BannerInfo;
import com.example.zk1.bean.Shopinfo;
import com.example.zk1.model.UserModel;

import java.util.List;

public interface UserContract {
    public abstract class IUserPresenter{
        public abstract void ShopList();
        public abstract void BannerList();
    }
    public interface IUserModel{
        void getShopList(UserModel.ModelCallback callback);
        void getBannerList(UserModel.ModelCallback callback);
    }
    public interface IUserView{
        void shopSuccess(List<Shopinfo.ResultBean.listBean> shopBean);
        void bannerSuccess(BannerInfo bannerBean);
        void Failure(String msg);
    }
}

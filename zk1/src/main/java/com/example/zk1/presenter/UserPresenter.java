package com.example.zk1.presenter;

import com.example.zk1.bean.BannerInfo;
import com.example.zk1.bean.Shopinfo;
import com.example.zk1.contract.UserContract;
import com.example.zk1.model.UserModel;
import com.google.gson.Gson;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class UserPresenter extends UserContract.IUserPresenter {

    private WeakReference<UserContract.IUserView> weakReference;
    private UserContract.IUserView view;
    private UserModel model;

    public UserPresenter(UserContract.IUserView view) {
        this.view = view;
        weakReference=new WeakReference<>(view);
        model=new UserModel();
    }
    @Override
    public void ShopList() {
        model.getShopList(new UserModel.ModelCallback() {
            @Override
            public void success(String result) {
                if (view!=null){
                    Shopinfo info=new Gson().fromJson(result,Shopinfo.class);
                    List<Shopinfo.ResultBean.listBean> list=new ArrayList<>();
                    list.addAll(info.result.mlss);
                    list.addAll(info.result.pzsh);
                    list.addAll(info.result.rxxp);
                    view.shopSuccess(list);
                }
            }
            @Override
            public void failure(String error) {
                if (view!=null){
                    view.Failure(error);
                }
            }
        });
    }
    @Override
    public void BannerList() {
        model.getBannerList(new UserModel.ModelCallback() {
            @Override
            public void success(String result) {
                if (view!=null){
                    BannerInfo info=new Gson().fromJson(result,BannerInfo.class);
                    view.bannerSuccess(info);
                }
            }
            @Override
            public void failure(String error) {
                if (view!=null){
                    view.Failure(error);
                }
            }
        });
    }
    public void onbind(){
        if (view!=null){
            weakReference.clear();
            weakReference=null;
            view=null;
        }
    }
}

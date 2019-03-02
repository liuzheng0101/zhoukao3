package com.example.mature.zhoukao1.presenter;
import com.example.mature.zhoukao1.bean.BannerInfo;
import com.example.mature.zhoukao1.bean.ShopInfo;
import com.example.mature.zhoukao1.contract.UserContract;
import com.example.mature.zhoukao1.model.UserModel;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserPresenter extends UserContract.UserPresenter {
    private WeakReference<UserContract.IUserView> weakReference;
    private UserContract.IUserView view;
    private UserModel model;
    public UserPresenter(UserContract.IUserView view) {
        this.view = view;
        weakReference=new WeakReference<>(view);
        model=new UserModel();
    }
    @Override
    public void getList(HashMap<String, String> params) {
        model.getList(params, new UserModel.ModelCallback() {
            @Override
            public void onSuccess(String result) {
                if (view!=null){
                    ShopInfo info=new Gson().fromJson(result,ShopInfo.class);
                    List<ShopInfo.ResultBean.listBean> list=new ArrayList<>();
                    list.addAll(info.result.mlss);
                    list.addAll(info.result.pzsh);
                    list.addAll(info.result.rxxp);
                    view.ShopSuccess(list);
                }
            }
            @Override
            public void onFailure(String error) {
                if (view!=null){
                    view.failure(error);
                }
            }
        });
    }
    @Override
    public void bannerList(HashMap<String, String> params) {
        model.bannerList(params, new UserModel.ModelCallback() {
            @Override
            public void onSuccess(String result) {
                BannerInfo info=new Gson().fromJson(result,BannerInfo.class);
                view.BannerSuccess(info);
            }
            @Override
            public void onFailure(String error) {
                if (view!=null){
                    view.failure(error);
                }
            }
        });
    }
    public void onUbinder(){
        if (view!=null){
            weakReference.clear();
            weakReference=null;
            view=null;
        }
    }
}

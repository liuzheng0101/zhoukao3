package com.example.zhoukao3.presenter;

import com.example.zhoukao3.bean.CartInfo;
import com.example.zhoukao3.bean.UserInfo;
import com.example.zhoukao3.contract.CartContract;
import com.example.zhoukao3.contract.UserContract;
import com.example.zhoukao3.model.CartModel;
import com.example.zhoukao3.model.UserModel;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class CartPresenter extends UserContract.IUserPresenter {
    private WeakReference<CartContract.ICartView> weakReference;
    private CartContract.ICartView view;
    private CartModel model;
    public CartPresenter(CartContract.ICartView view) {
        this.view = view;
        model=new CartModel();
        weakReference=new WeakReference<>(view);
    }
    @Override
    public void getList(HashMap<String, String> params) {
        model.getList(params, new CartModel.ModelCallback() {
            @Override
            public void onSuccess(String result) {
                if (view!=null){
                    CartInfo info=new Gson().fromJson(result,CartInfo.class);
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

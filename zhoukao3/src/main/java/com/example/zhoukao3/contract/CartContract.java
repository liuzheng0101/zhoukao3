package com.example.zhoukao3.contract;

import com.example.zhoukao3.bean.CartInfo;
import com.example.zhoukao3.bean.ShopYiang;
import com.example.zhoukao3.model.CartModel;

import java.util.HashMap;

public interface CartContract {
    public abstract class ICartPresenter{
        public abstract void getList(HashMap<String,String> params);
        public abstract void getshopYiang(String id);
    }
    public interface ICartModel{
        void getList(HashMap<String,String> params, CartModel.ModelCallback callback);
        void getshopYiang(String id,CartModel.ModelCallback callback);
    }
    public interface ICartView{
        void onShopSuccess(CartInfo info);
        void shopYiangSuccess(ShopYiang shopYiang);
        void onFailure(String error);
    }
}

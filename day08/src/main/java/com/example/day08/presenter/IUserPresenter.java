package com.example.day08.presenter;

import com.example.day08.contract.UserContract;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class IUserPresenter extends UserContract.IUserPresenter {
    private WeakReference<UserContract.IUseView> weakReference;
    private UserContract.IUseView view;
    @Override
    public void getList(HashMap<String, String> params) {

    }
}

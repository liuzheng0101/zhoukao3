package com.example.day08.net;

public interface RetrofitCallback {
    void onSuccess(String result);
    void onFailure(String error);
}

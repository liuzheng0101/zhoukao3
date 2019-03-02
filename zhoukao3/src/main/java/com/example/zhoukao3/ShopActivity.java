package com.example.zhoukao3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zhoukao3.bean.CartInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShopActivity extends AppCompatActivity {
    private Unbinder bind;
    @BindView(R.id.rv)
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        bind=ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }
    @Subscribe(sticky = true)
    public void getShopData(CartInfo.Result result){

    }
}

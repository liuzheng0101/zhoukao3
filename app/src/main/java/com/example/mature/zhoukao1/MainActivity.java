package com.example.mature.zhoukao1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mature.zhoukao1.adapter.UserAdapter;
import com.example.mature.zhoukao1.bean.BannerInfo;
import com.example.mature.zhoukao1.bean.ShopInfo;
import com.example.mature.zhoukao1.contract.UserContract;
import com.example.mature.zhoukao1.presenter.UserPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements UserContract.IUserView {
    private Unbinder bind;
    private UserPresenter presenter;
    private UserAdapter adapter;
    @BindView(R.id.banner)
    FlyBanner banner;
    @BindView(R.id.rv)
    XRecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind=ButterKnife.bind(this);
        initData();
    }
    private void initData() {
        presenter=new UserPresenter(this);
        adapter=new UserAdapter(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String,String> params=new HashMap<>();
        presenter.getList(params);
        rv.setAdapter(adapter);
    }
    @Override
    public void ShopSuccess(List<ShopInfo.ResultBean.listBean> info) {
        adapter.setShopData(info);
    }
    @Override
    public void BannerSuccess(BannerInfo info) {
        adapter.setBannerData(info.result);
    }
    @Override
    public void failure(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
        presenter.onUbinder();
    }
}

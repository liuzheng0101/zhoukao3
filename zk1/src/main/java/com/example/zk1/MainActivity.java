package com.example.zk1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.zk1.adapter.UserAdapter;
import com.example.zk1.bean.BannerInfo;
import com.example.zk1.bean.Shopinfo;
import com.example.zk1.contract.UserContract;
import com.example.zk1.presenter.UserPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements UserContract.IUserView {
    private Unbinder bind;
    private UserPresenter presenter;
    private UserAdapter adapter;
    @BindView(R.id.rv)
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind=ButterKnife.bind(this);
        initData();
    }
    private void initData() {
        presenter = new UserPresenter(this);
        presenter.ShopList();
        presenter.BannerList();
        adapter = new UserAdapter(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @Override
    public void shopSuccess(List<Shopinfo.ResultBean.listBean> shopBean) {
        adapter.setSlist(shopBean);
    }
    @Override
    public void bannerSuccess(BannerInfo bannerBean) {
        adapter.setBlist(bannerBean.result);
    }
    @Override
    public void Failure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
        presenter.onbind();
    }
}

package com.example.zhoukao3.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.zhoukao3.R;
import com.example.zhoukao3.adapter.CartAdapter;
import com.example.zhoukao3.bean.CartInfo;
import com.example.zhoukao3.bean.ShopYiang;
import com.example.zhoukao3.contract.CartContract;
import com.example.zhoukao3.presenter.CartPresenter;
import com.example.zhoukao3.PriceNum;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment_Cart extends Fragment implements CartContract.ICartView,CartAdapter.onclickList,PriceNum{
    private Unbinder bind;
    private RecyclerView rv;
    private CartAdapter adapter;
    private CartPresenter presenter;
    private CheckBox checkBox;
    private String userId="982";
    private List<CartInfo.Result> cart;
    private String sessionId="1551495953167982";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cart,container,false);
        bind=ButterKnife.bind(getActivity());
        initView(view);
        initData();
        initcheck();
        return view;
    }

    private void initcheck() {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    for (CartInfo.Result list : cart) {
                        list.isChecked=true;
                    }
                }else{
                    for (CartInfo.Result list : cart) {
                        list.isChecked=false;
                    }
                }
                adapter.notifyDataSetChanged();
                getTotalPrice();
            }
        });
    }
    private void getTotalPrice() {
        double total=0;
        for (CartInfo.Result result : cart) {
            if (result.isChecked) {
                total += result.price * result.productNum;
            }
        }
        checkBox.setText("￥:"+total);
    }

    private void initData() {
        presenter=new CartPresenter(this);
        adapter=new CartAdapter(getActivity());
        adapter.setOnclickList(this);
        HashMap<String,String> params=new HashMap<>();
        params.put("userId",userId);
        params.put("sessionId",sessionId);
        presenter.getList(params);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
    }
    private void initView(View view) {
        rv=view.findViewById(R.id.rv);
        checkBox=view.findViewById(R.id.checkbox);
    }
    @Override
    public void onShopSuccess(CartInfo info) {
        cart=info.result;
        adapter.setPriceNum(this);
        for (CartInfo.Result result : cart) {
            result.productNum=1;
        }
        adapter.setData(info.result);
    }

    @Override
    public void shopYiangSuccess(ShopYiang shopYiang) {

    }

    @Override
    public void onFailure(String error){
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
    @Override
    public void getList(CartInfo.Result result) {
       String id=result.commodityId;
    }

    @Override
    public void pricenumCallback() {
        getTotalPrice();
    }
}

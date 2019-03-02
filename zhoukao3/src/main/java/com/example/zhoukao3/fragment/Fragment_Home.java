package com.example.zhoukao3.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhoukao3.R;
import com.example.zhoukao3.adapter.UserAdapter;
import com.example.zhoukao3.bean.UserInfo;
import com.example.zhoukao3.contract.UserContract;
import com.example.zhoukao3.presenter.UserPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment_Home extends Fragment implements UserContract.IUserView {
    private Unbinder bind;
    private EditText name;
    private Button button;
    private UserPresenter presenter;
    private XRecyclerView rv;
    private UserAdapter adapter;
    private String keyword;
    private int page=1;
    private String count="5";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        bind=ButterKnife.bind(getActivity());
        initView(view);
        initData();
        return view;
    }
    private void initView(View view) {
        name=view.findViewById(R.id.names);
        button=view.findViewById(R.id.select);
        rv=view.findViewById(R.id.rv);
    }
    private void initData() {
        presenter=new UserPresenter(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter=new UserAdapter(getActivity());
                final HashMap<String,String> params=new HashMap<>();
                params.put("keyword",name.getText().toString());
                params.put("page",page+"");
                params.put("count",count);
                presenter.getList(params);
                rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv.setAdapter(adapter);
            }
        });
    }
    @Override
    public void onShopSuccess(UserInfo info) {
        adapter.setData(info.result);
    }
    @Override
    public void onFailure(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}

package com.example.zk1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zk1.R;
import com.example.zk1.bean.BannerInfo;
import com.example.zk1.bean.Shopinfo;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BannerInfo.ResultBean> blist;
    private List<Shopinfo.ResultBean.listBean> slist;
    private Context context;


    public UserAdapter(Context context) {
        this.context = context;
        slist = new ArrayList<>();
        blist = new ArrayList<>();
    }

    public void setBlist(List<BannerInfo.ResultBean> blist) {
        this.blist = blist;
        notifyDataSetChanged();
    }

    public void setSlist(List<Shopinfo.ResultBean.listBean> slist) {
        this.slist = slist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (getItemViewType(viewType)==0){
            view = LayoutInflater.from(context).inflate(R.layout.banner, parent, false);
            BannerHolder bannerHolder = new BannerHolder(view);
            return bannerHolder;
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            ShopHolder shopHolder = new ShopHolder(view);
            return shopHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position)==0){
            if(blist.size()==0){
                return;
            }
            List<String> list = new ArrayList<>();
            for (BannerInfo.ResultBean b : blist) {
                list.add(b.imageUrl);
            }
            ((BannerHolder)holder).banner.setImagesUrl(list);
        }else{
            ((ShopHolder)holder).title.setText(slist.get(position-1).name);
            ((ShopHolder)holder).rv.setLayoutManager(new LinearLayoutManager(context));
            ((ShopHolder)holder).rv.setAdapter(new ShopAdapter(context,slist.get(position-1).commodityList));
        }
    }

    @Override
    public int getItemCount() {
        return slist.size()==0?0:slist.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }
        return 1;
    }



    class BannerHolder extends RecyclerView.ViewHolder{
        private FlyBanner banner;
        public BannerHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    public class ShopHolder extends RecyclerView.ViewHolder {
        private RecyclerView rv;
        private TextView title;
        public ShopHolder(View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
            title = itemView.findViewById(R.id.title);
        }
    }
}

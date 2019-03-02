package com.example.mature.zhoukao1.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mature.zhoukao1.R;
import com.example.mature.zhoukao1.bean.ShopInfo;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private Context context;
    private List<ShopInfo.ResultBean.listBean.CommodityListBean> list;
    public ShopAdapter(Context context, List<ShopInfo.ResultBean.listBean.CommodityListBean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.shopitem,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).commodityName);
        holder.price.setText(list.get(position).price);
        Uri uri=Uri.parse(list.get(position).masterPic);
        DraweeController controller=Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.icon.setController(controller);
    }
    @Override
    public int getItemCount() {
        return list.size()==0?0:list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView icon;
        private TextView name,price;
        public ViewHolder(View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
        }
    }
}

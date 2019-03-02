package com.example.zhoukao3.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhoukao3.R;
import com.example.zhoukao3.bean.UserInfo;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends XRecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private List<UserInfo.Result> list;
    public UserAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.home_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.price.setText("￥:"+list.get(position).price);
        holder.name.setText(list.get(position).commodityName);
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

    public void setData(List<UserInfo.Result> result) {
        if (result!=null){
            this.list=result;
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;
        SimpleDraweeView icon;
        public ViewHolder(View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.icon);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
        }
    }
}

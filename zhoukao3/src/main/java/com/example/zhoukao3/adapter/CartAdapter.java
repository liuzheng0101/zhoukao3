package com.example.zhoukao3.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.zhoukao3.R;
import com.example.zhoukao3.bean.CartInfo;
import com.example.zhoukao3.view.MyView;
import com.example.zhoukao3.PriceNum;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private List<CartInfo.Result> list;
    private PriceNum priceNum;
    public void setPriceNum(PriceNum priceNum) {
        this.priceNum = priceNum;
    }
    public CartAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final CartInfo.Result cart=list.get(position);
        //全选
        holder.checkBox.setChecked(list.get(position).isChecked);
        holder.price.setText("￥:"+list.get(position).price);
        holder.name.setText(list.get(position).commodityName);
        Uri uri=Uri.parse(list.get(position).pic);
        DraweeController controller=Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.icon.setController(controller);

        //自定义
        holder.addMinusView.setAddMinusCallback(new MyView.AddMinusCallback() {
            @Override
            public void numCallback(int num) {
                    cart.productNum = num;
                if (cart.isChecked==true) {
                    priceNum.pricenumCallback();
                }
            }
        });
        //计算小计
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    for (CartInfo.Result result : list) {
                        result.isChecked = holder.checkBox.isChecked();
                    }
                    notifyDataSetChanged();
                    priceNum.pricenumCallback();
                }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickList.getList(cart);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size()==0?0:list.size();
    }

    public void setData(List<CartInfo.Result> result) {
        if (result!=null){
            this.list=result;
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;
        SimpleDraweeView icon;
        CheckBox checkBox;
        MyView addMinusView;
        public ViewHolder(View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            checkBox = itemView.findViewById(R.id.check);
            addMinusView=itemView.findViewById(R.id.addminusView);
        }
    }
    public interface onclickList{
        void getList(CartInfo.Result result);
    }
    private onclickList onclickList;
    public void setOnclickList(CartAdapter.onclickList onclickList) {
        this.onclickList = onclickList;
    }
}

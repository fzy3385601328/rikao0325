package com.example.myappfan20200326.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myappfan20200326.R;

import com.example.myappfan20200326.bean.NewsResultBean;


import java.util.List;

public class RxxpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<NewsResultBean.ResultBean.RxxpBean.CommodityListBean> commodityList;
    public RxxpAdapter(Context context, List<NewsResultBean.ResultBean.RxxpBean.CommodityListBean> commodityList) {
        this.context = context;
        this.commodityList = commodityList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_item1, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(context)
                .load(commodityList.get(position).getMasterPic())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(((ViewHolder)holder).itemImage);
        ((ViewHolder)holder).itemName.setText(commodityList.get(position).getCommodityName());
        ((ViewHolder)holder).itemPrice.setText(commodityList.get(position).getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView itemImage;
        private final TextView itemName;
        private final TextView itemPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
        }
    }


}

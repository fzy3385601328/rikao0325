package com.example.myappdemo02.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myappdemo02.R;
import com.example.myappdemo02.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

public class NewsResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<NewsBean> list = new ArrayList<>();

    public void addAll(List<NewsBean> data){
        list.addAll(data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(((ViewHolder)holder).itemIm1.getContext())
                .load(list.get(position).headPic).into(((ViewHolder)holder).itemIm1);
        Glide.with(((ViewHolder)holder).itemIm2.getContext())
                .load(list.get(position).image).into(((ViewHolder)holder).itemIm2);
        ((ViewHolder)holder).nickName.setText(list.get(position).nickName);
        ((ViewHolder)holder).itemText.setText(list.get(position).content);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView itemIm1;
        private final ImageView itemIm2;
        private final TextView nickName;
        private final TextView itemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemIm1 = itemView.findViewById(R.id.item_im1);
            itemIm2 = itemView.findViewById(R.id.item_im2);
            nickName = itemView.findViewById(R.id.nick_name);
            itemText = itemView.findViewById(R.id.item_text);
        }
    }
}

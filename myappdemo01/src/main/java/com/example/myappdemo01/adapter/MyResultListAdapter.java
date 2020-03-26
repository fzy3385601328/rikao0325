package com.example.myappdemo01.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myappdemo01.R;
import com.example.myappdemo01.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

public class MyResultListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<NewsBean> list = new ArrayList<>();

    public void addAll(List<NewsBean> data){
        if (data!=null)
          list.addAll(data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.laytout_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.abu)
                .fallback(R.drawable.abw)
                .error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(60)));


        //加载第一种图片
        Glide.with(((ViewHolder)holder).itemImg1.getContext())
                .load(list.get(position).headPic).into(((ViewHolder)holder)
        .itemImg1);
        //加载第二种图片
        Glide.with(((ViewHolder)holder).itemImg2.getContext())
                .load(list.get(position).image).into(((ViewHolder)holder).itemImg2);

        ((ViewHolder)holder).nickName.setText(list.get(position).nickName);
        ((ViewHolder)holder).itemText.setText(list.get(position).content);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView itemImg1;
        private final ImageView itemImg2;
        private final TextView nickName;
        private final TextView itemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImg1 = itemView.findViewById(R.id.item_img1);
            itemImg2 = itemView.findViewById(R.id.item_img2);
            nickName = itemView.findViewById(R.id.nick_name);
            itemText = itemView.findViewById(R.id.item_text);
        }
    }

    public void clear(){
        list.clear();
    }
}

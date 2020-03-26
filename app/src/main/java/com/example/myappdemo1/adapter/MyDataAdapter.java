package com.example.myappdemo1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.myappdemo1.R;
import com.example.myappdemo1.bean.NewsInfo;
import java.util.ArrayList;
import java.util.List;

public class MyDataAdapter extends BaseAdapter {

    Context context;
    private List<NewsInfo> list = new ArrayList<>();

    public MyDataAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<NewsInfo> data){
        list.addAll(data);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (holder==null){
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_item1, parent, false);
            holder = new ViewHolder();
            holder.item_img = convertView.findViewById(R.id.item_img);
            holder.item_name = convertView.findViewById(R.id.item_name);
            holder.item_price = convertView.findViewById(R.id.item_price);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewsInfo newsInfo = list.get(position);
        Glide.with(context).load(newsInfo.goods_thumb)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.item_img);
        holder.item_name.setText(newsInfo.goods_name);
        holder.item_price.setText(newsInfo.currency_price);
        return convertView;
    }

    class ViewHolder{
        private ImageView item_img;
        private TextView item_name;
        private TextView item_price;
    }

    public void clear(){
        list.clear();
    }
}

package com.example.myappfan20200325.activity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myappfan20200325.R;
import com.example.myappfan20200325.adapter.MlssAdapter;
import com.example.myappfan20200325.base.CommodityListCall;
import com.example.myappfan20200325.base.BaseActivity;

import com.example.myappfan20200325.bean.commodityListBean;
import com.example.myappfan20200325.bean.mlssBean;
import com.example.myappfan20200325.presenter.NewsPresenter;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

public class MainActivity extends BaseActivity implements CommodityListCall<commodityListBean> {


    private RecyclerView recyclerView,recyclerView2,recyclerView3;
    private LinearLayoutManager linearLayoutManager;
    private MlssAdapter mlssAdapter;
    private int page = 1;
    private XBanner xBanner;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        xBanner = findViewById(R.id.x_Banner);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView2 = findViewById(R.id.recycler_view2);
        recyclerView3 = findViewById(R.id.recycler_view3);
        //设置布局管理器
        linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView2.setLayoutManager(linearLayoutManager1);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView3.setLayoutManager(gridLayoutManager);
        mlssAdapter = new MlssAdapter();
        recyclerView.setAdapter(mlssAdapter);

        NewsPresenter presenter = new NewsPresenter(this);
        presenter.request(String.valueOf(page));
    }

    @Override
    public void CommoditySuccess(final mlssBean<commodityListBean> result) {
        Toast.makeText(this, "吐司数据"+result, Toast.LENGTH_SHORT).show();
        final List<commodityListBean> commodityList = result.commodityList;

        //轮播展示

        xBanner.setData(commodityList,null);
        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                String masterPic = commodityList.get(position).masterPic;
                //加载轮播图
                Glide.with(MainActivity.this)
                        .load(masterPic)
                        .error(R.mipmap.abw)
                        .placeholder(R.mipmap.abx)
                        .into((ImageView) view);
            }
        });

        //展示第一个列表
        mlssAdapter.addAll(commodityList);
        mlssAdapter.notifyDataSetChanged();

    }

    @Override
    public void CommodityError(String msg) {
        Toast.makeText(this, "打印失败", Toast.LENGTH_SHORT).show();
    }
}

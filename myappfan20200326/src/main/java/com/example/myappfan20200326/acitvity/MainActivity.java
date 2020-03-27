package com.example.myappfan20200326.acitvity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myappfan20200326.R;
import com.example.myappfan20200326.adapter.RxxpAdapter;
import com.example.myappfan20200326.base.BaseActivity;
import com.example.myappfan20200326.base.BasePresenter;
import com.example.myappfan20200326.bean.NewsResultBean;
import com.example.myappfan20200326.bean.RxxpBean;
import com.example.myappfan20200326.bean.XBannerBean;
import com.example.myappfan20200326.bean.commodityListBean;
import com.example.myappfan20200326.contract.IHomePagerContract;
import com.example.myappfan20200326.presenter.IHomePagerPresenter;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

public class MainActivity extends BaseActivity implements IHomePagerContract.IHomePagerView {


    private XBanner xBanner;
    private RecyclerView rRxxp;
    private RecyclerView rMlss;
    private RecyclerView rPzsh;
    /*private List<XBannerBean.ResultBean> result;*/


    @Override
    protected BasePresenter initPresenter() {
        return new IHomePagerPresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        xBanner = findViewById(R.id.xBanner);
        rRxxp = findViewById(R.id.recycler_rxxp);
        rMlss = findViewById(R.id.recycler_mlss);
        rPzsh = findViewById(R.id.recycler_pzsh);

        //设置第一个RecyclerView布局管理器
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false);
        rRxxp.setLayoutManager(linearLayoutManager1);
        //设置第二个RecyclerView布局管理器
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        rMlss.setLayoutManager(linearLayoutManager2);
        //设置第三个RecyclerView布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        rPzsh.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void getData() {
        BasePresenter presenter = getmPresenter();

        if (presenter instanceof IHomePagerPresenter){
            ((IHomePagerPresenter) presenter).IHomePresenterMethod("http://mobile.bwstudent.com/small/commodity/v1/bannerShow");
            ((IHomePagerPresenter)presenter).IHomePresenterMethod("http://mobile.bwstudent.com/small/commodity/v1/commodityList");

        }
    }

    @Override
    public void IHomeViewSuccess(String json) {
          //轮播图
        Gson gson = new Gson();
 /*       XBannerBean xBannerBean = gson.fromJson(json, XBannerBean.class);
//        result = xBannerBean.getResult();
//
//        xBanner.setBannerData(result);
//        //设置轮播
//        xBanner.loadImage(new XBanner.XBannerAdapter() {
//            @Override
//            public void loadBanner(XBanner banner, Object model, View view, int position) {
//                XBannerBean.ResultBean resultBean = result.get(position);
//                //Gilde加载
//                Glide.with(MainActivity.this)
//                        .load(resultBean.getImageUrl())
//                        .error(R.mipmap.ic_launcher)
//                        .placeholder(R.mipmap.ic_launcher)
//                        .into((ImageView) view);
//            }
//        });*/

        //三个Recycler列表
        RxxpBean rxxpBean = gson.fromJson(json, RxxpBean.class);
        List<NewsResultBean.ResultBean.RxxpBean.CommodityListBean> commodityList = rxxpBean.commodityList;
        RxxpAdapter adapter = new RxxpAdapter(MainActivity.this, commodityList);
        rRxxp.setAdapter(adapter);
    }

    @Override
    public void IHomeViewError(String msg) {
        Toast.makeText(this, "展示失败", Toast.LENGTH_SHORT).show();
    }
}

package com.example.myappdemo1.acivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myappdemo1.adapter.MyDataAdapter;
import com.example.myappdemo1.R;
import com.example.myappdemo1.base.BaseActivity;
import com.example.myappdemo1.base.DataBack;
import com.example.myappdemo1.bean.DataInfo;
import com.example.myappdemo1.bean.NewsInfo;
import com.example.myappdemo1.persenter.DataPresenter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MainActivity extends BaseActivity implements DataBack<NewsInfo> {

    private DataPresenter presenter;
    private PullToRefreshListView PullList;
    private int page=1;
    private MyDataAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        presenter = new DataPresenter(this);
        presenter.request();

        PullList = findViewById(R.id.pull_list);


        adapter = new MyDataAdapter(MainActivity.this);
        PullList.setAdapter(adapter);

        PullList.setMode(PullToRefreshBase.Mode.BOTH);
        PullList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                presenter.request(page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                 page++;
                 presenter.request(page);
            }

        });
        presenter.request(page);

    }

    @Override
    public void DataSuccess(DataInfo<NewsInfo> data) {
        PullList.onRefreshComplete();
        Toast.makeText(this, "吐司成功"+data.data.get(0).goods_name, Toast.LENGTH_SHORT).show();
        if (data!=null){
            if (page==1){
                adapter.clear();
            }
            adapter.addAll(data.data);
            //刷新适配器
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void DataError(String error) {
        PullList.onRefreshComplete();
        Log.i("xxx","打印失败");
    }
}

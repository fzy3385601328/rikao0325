package com.example.mydemo02.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mydemo02.R;
import com.example.mydemo02.adapter.MyNewsAdapter;
import com.example.mydemo02.base.BaseActivity;
import com.example.mydemo02.base.BasePresenter;
import com.example.mydemo02.bean.MyDataInfo;
import com.example.mydemo02.contract.IHomeDataContract;
import com.example.mydemo02.presenter.IHomeDataPresenter;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

public class MainActivity extends BaseActivity implements IHomeDataContract.onDataViewIBackCall {


    private PullToRefreshListView pullList;
    private int page=1;
    private List<MyDataInfo.DataBean> data;


    @Override
    protected BasePresenter initPresenter() {
        return new IHomeDataPresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        pullList = findViewById(R.id.pull_list);
    }

    @Override
    protected void getDate() {
        final BasePresenter presenter = getmPresenter();
        if (presenter instanceof IHomeDataPresenter){
            ((IHomeDataPresenter) presenter).onUrlBack("http://blog.zhaoliang5156.cn/api/shop/shop"+page+".json");
        }


        pullList.setMode(PullToRefreshBase.Mode.BOTH);
        pullList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                pullList.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                pullList.onRefreshComplete();

            }
        });
    }

    @Override
    public void onViewSuccess(String json) {
        Log.i("xxx",json);
        Toast.makeText(this, "解析成功"+json, Toast.LENGTH_SHORT).show();

        Gson gson = new Gson();
        MyDataInfo myDataInfo = gson.fromJson(json, MyDataInfo.class);
        data = myDataInfo.getData();
        MyNewsAdapter adapter = new MyNewsAdapter(MainActivity.this, data);
        pullList.setAdapter(adapter);
    }

    @Override
    public void onViewError(String msg) {
        Log.i("xxx",msg);
        Toast.makeText(this, "解析失败", Toast.LENGTH_SHORT).show();
    }
}

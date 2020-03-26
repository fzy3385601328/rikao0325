package com.example.myappdemo02.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myappdemo02.R;
import com.example.myappdemo02.adapter.NewsResultAdapter;
import com.example.myappdemo02.base.BaseActivity;
import com.example.myappdemo02.base.ResultCall;
import com.example.myappdemo02.bean.NewsBean;
import com.example.myappdemo02.bean.ResultBean;
import com.example.myappdemo02.presenter.ResultPresenter;

public class MainActivity extends BaseActivity implements ResultCall<NewsBean> {


    private RecyclerView recyclerView;
    private NewsResultAdapter adapter;
    private ResultPresenter presenter;
    private int page = 1;

    @Override
    protected int getLayoutById() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //找控件
        recyclerView = findViewById(R.id.recycler_view);
        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                (MainActivity.this, RecyclerView.VERTICAL, false);
        //设置布局管理器
        recyclerView.setLayoutManager(linearLayoutManager);
        //创建适配器
        adapter = new NewsResultAdapter();
        //设置适配器
        recyclerView.setAdapter(adapter);

        //
        presenter = new ResultPresenter(this);

        presenter.sendRequest(String.valueOf(page));
    }

    @Override
    public void resultSuccess(ResultBean<NewsBean> result) {
         if (result.status.equals("0000")){
             adapter.addAll(result.result);
             adapter.notifyDataSetChanged();
         }
        Toast.makeText(this, "请求成功"+result.message+"   "+result.status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void resultError(String msg) {
         //否则就是请求失败了
        Toast.makeText(this, "抱歉，程序停止运行了", Toast.LENGTH_SHORT).show();
    }
}

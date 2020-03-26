package com.example.myappdemo01.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myappdemo01.R;
import com.example.myappdemo01.adapter.MyResultListAdapter;
import com.example.myappdemo01.base.BaseActivity;
import com.example.myappdemo01.base.ResultCall;
import com.example.myappdemo01.bean.NewsBean;
import com.example.myappdemo01.bean.ResultBean;
import com.example.myappdemo01.presenter.ResultPresenter;

public class MainActivity extends BaseActivity implements ResultCall<NewsBean>
       ,View.OnClickListener{


    private RecyclerView recyclerView;
    private ResultPresenter rPresenter;
    //创建一个int类型的变量
    private int page = 1;
    private MyResultListAdapter adapter;
    private ImageButton button_style;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
       //找控件
        button_style = findViewById(R.id.button_style);
        recyclerView = findViewById(R.id.recycler_view);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        //存放布局管理器的对象
        recyclerView.setLayoutManager(linearLayoutManager);
        //创建适配器
        adapter = new MyResultListAdapter();
        //设置适配器
        recyclerView.setAdapter(adapter);

        //最后初始化Presenter
        rPresenter = new ResultPresenter(this);

        //发送
        rPresenter.request(String.valueOf(page));
        button_style.setOnClickListener(this);
    }

    @Override
    public void ResultSuccess(ResultBean<NewsBean> result) {
         if (result.status.equals("0000")){
             adapter.addAll(result.result);
             adapter.notifyDataSetChanged();
         }
        Toast.makeText(this, result.message+""+result.status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ResultError(String error) {
        Toast.makeText(this, "错误提示信息"+error, Toast.LENGTH_SHORT).show();
    }

    private int flag = R.drawable.abu;

    @Override
    public void onClick(View v) {


        if (v.getId()==R.id.button_style){
            if (flag==R.drawable.abu){
                flag=R.drawable.abw;
                recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(),2));
            }else {
                flag=R.drawable.abu;
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                         RecyclerView.VERTICAL,false));
            }
            button_style.setImageResource(flag);
        }


    }
}

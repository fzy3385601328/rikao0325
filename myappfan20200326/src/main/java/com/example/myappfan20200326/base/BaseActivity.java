package com.example.myappfan20200326.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<V extends BasePresenter>extends AppCompatActivity implements IBaseView {

    BasePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();

        setContentView(getLayoutResId());

        initView();

        getData();
    }

    public BasePresenter getmPresenter() {
        return mPresenter;
    }

    protected abstract BasePresenter initPresenter();

    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected abstract void getData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.onDeatchView();
            mPresenter=null;
        }
    }
}

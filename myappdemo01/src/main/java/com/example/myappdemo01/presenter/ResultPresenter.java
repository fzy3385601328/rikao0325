package com.example.myappdemo01.presenter;

import com.example.myappdemo01.base.BasePresenter;
import com.example.myappdemo01.base.ResultCall;
import com.example.myappdemo01.bean.NewsBean;
import com.example.myappdemo01.bean.ResultBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
//这个Presenter需要继承BasePresenter然后重写BasePresenter的抽象的方法
public class ResultPresenter extends BasePresenter {

    public ResultPresenter(ResultCall resultCall) {
        super(resultCall);
    }

    //重写BasePresenter的两个抽象的方法
    @Override
    protected String pathUrl(String... args) {
        return "http://mobile.bwstudent.com/small/circle/v1/findCircleList?page="
                +args[0]+"&count=10";
    }

    //这个抽象的方法是找到Bean类的数据进行解析
    @Override
    protected ResultBean pathData(String json) {
        Type type = new TypeToken<ResultBean<NewsBean>>() {
        }.getType();
        return gson.fromJson(json,type);
    }
}

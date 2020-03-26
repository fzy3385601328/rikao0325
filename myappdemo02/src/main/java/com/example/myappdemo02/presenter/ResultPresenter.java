package com.example.myappdemo02.presenter;

import com.example.myappdemo02.base.BasePresenter;
import com.example.myappdemo02.base.ResultCall;
import com.example.myappdemo02.bean.NewsBean;
import com.example.myappdemo02.bean.ResultBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ResultPresenter extends BasePresenter {
    public ResultPresenter(ResultCall resultCall) {
        super(resultCall);
    }

    @Override
    protected String getUrl(String... args) {
        return "http://mobile.bwstudent.com/small/circle/v1/findCircleList?page="+args[0]+"&count=10";
    }

    @Override
    protected ResultBean getData(String json) {
        Type type = new TypeToken<ResultBean<NewsBean>>() {
        }.getType();
        return gson.fromJson(json,type);
    }
}

package com.example.myappdemo01.base;

import com.android.volley.Request;
import com.example.myappdemo01.bean.ResultBean;
import com.example.myappdemo01.utils.VolleyUtils;
import com.google.gson.Gson;

import java.util.Map;

public abstract class BasePresenter {

    //创建Gson对象
    public final static Gson gson = new Gson();

    //创建ResultCall接口的对象
    private ResultCall resultCall;

    public BasePresenter(ResultCall resultCall) {
        this.resultCall = resultCall;
    }

    //获取get请求的方式
    public void request(String...args){
        VolleyUtils.request(Request.Method.GET, pathUrl(args), null
                , new IBaseCall() {
                    @Override
                    public void onSuccess(String json) {
                        //用ResultCall的对象去调用VolleyUtils的成功的方法
                        ResultBean resultbean = pathData(json);
                        //通过resultCall去调用成功的方法
                        resultCall.ResultSuccess(resultbean);
                    }

                    @Override
                    public void onError(String msg) {
                         //这边直接调用失败的方法
                        resultCall.ResultError(msg);
                    }
                });
    }


    //获取的是post请求的方式
    public void request(Map<String,String> map, String...args){
        VolleyUtils.request(Request.Method.POST, pathUrl(args), map
                , new IBaseCall() {
                    @Override
                    public void onSuccess(String json) {
                        ResultBean resultBean = pathData(json);
                        resultCall.ResultSuccess(resultBean);
                    }

                    @Override
                    public void onError(String msg) {
                        resultCall.ResultError(msg);
                    }
                });
    }

    protected abstract String pathUrl(String...args);

    protected abstract ResultBean pathData(String json);
}

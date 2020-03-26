package com.example.myappdemo02.base;

import com.android.volley.Request;
import com.example.myappdemo02.bean.ResultBean;
import com.example.myappdemo02.utils.VolleyUtils;
import com.google.gson.Gson;

import java.util.Map;

public abstract class BasePresenter {
    public final static Gson gson = new Gson();
    //??????????
    private static ResultCall resultCall;

    public BasePresenter(ResultCall resultCall) {
        this.resultCall = resultCall;
    }

    public void sendRequest(String...args){
        //调用VolleyUtils工具类
        VolleyUtils.request(Request.Method.GET, getUrl(args)
                , null, new IBaseCall() {
                    @Override
                    public void onSuccess(String json) {
                        ResultBean result = getData(json);
                        resultCall.resultSuccess(result);
                    }

                    @Override
                    public void onError(String error) {
                        resultCall.resultError(error);
                    }
                });
    }


    public void sendRequest(Map<String,String> map, String...args){
        VolleyUtils.request(Request.Method.POST, getUrl(args)
                , map, new IBaseCall() {
                    @Override
                    public void onSuccess(String json) {
                        ResultBean result = getData(json);
                        resultCall.resultSuccess(result);
                    }

                    @Override
                    public void onError(String error) {
                        resultCall.resultError(error);
                    }
                });
    }

    protected abstract String getUrl(String...args);

    protected abstract ResultBean getData(String json);
}

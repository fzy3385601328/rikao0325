package com.example.myappdemo02.base;

public interface IBaseCall {

    //创建接口的成功或者是失败的方法
    void onSuccess(String json);
    void onError(String error);
}

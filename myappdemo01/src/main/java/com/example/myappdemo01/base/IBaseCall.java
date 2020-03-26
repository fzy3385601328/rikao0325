package com.example.myappdemo01.base;
//定义一个成功的或者是失败的接口，以供VolleyUtils调用
public interface IBaseCall {
    //成功
    void onSuccess(String json);
    //失败
    void onError(String msg);
}

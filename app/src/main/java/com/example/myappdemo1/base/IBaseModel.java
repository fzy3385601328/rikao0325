package com.example.myappdemo1.base;
//创建外部接口，在里面定义成功或者失败的方法
public interface IBaseModel {

    //在这里创建成功或者是失败的方法
    void onSuccess(String json);
    void onError(String error);
}

package com.example.myappdemo02.base;

import com.example.myappdemo02.bean.ResultBean;

import javax.xml.transform.Result;

public interface ResultCall<T> {
    //请求成功的方法
    void resultSuccess(ResultBean<T> result);
    //请求失败的方法
    void resultError(String msg);
}

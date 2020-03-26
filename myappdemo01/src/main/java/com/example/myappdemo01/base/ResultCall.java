package com.example.myappdemo01.base;

import com.example.myappdemo01.bean.ResultBean;

public interface ResultCall<T> {
    //把数据存进去，并判断成功或者失败的方法
    void ResultSuccess(ResultBean<T> result);
    //失败的方法
    void ResultError(String error);
}

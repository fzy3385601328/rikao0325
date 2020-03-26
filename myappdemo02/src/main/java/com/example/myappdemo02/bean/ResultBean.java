package com.example.myappdemo02.bean;

import java.io.Serializable;
import java.util.List;

public class ResultBean<T> implements Serializable {
    public List<T> result;
    public String message;
    public String status;
}

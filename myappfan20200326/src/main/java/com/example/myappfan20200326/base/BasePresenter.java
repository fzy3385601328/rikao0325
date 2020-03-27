package com.example.myappfan20200326.base;

import java.lang.ref.WeakReference;
//编写基类的Presenter
public abstract class BasePresenter<V extends IBaseView> {

    private WeakReference<V> weakReference;

    public BasePresenter(V v){
        weakReference = new WeakReference<>(v);
        getModel();
    }

    protected abstract void getModel();

    public V getView(){
        if (weakReference!=null){
            return weakReference.get();
        }
        return null;
    }

    protected void onDeatchView(){
        if (weakReference!=null){
            weakReference.clear();
            weakReference=null;
        }
    }
}

package com.example.myappfan20200326.model;

import com.example.myappfan20200326.base.ICallBack;
import com.example.myappfan20200326.contract.IHomePagerContract;
import com.example.myappfan20200326.utils.VolleyUtils;

public class IHomePagerModel implements IHomePagerContract.IHomePagerModel {
    @Override
    public void IHomeModelMethod(String url, final IHomeModelBack iHomeModelBack) {
        VolleyUtils.getInstance().doGet(url, new ICallBack() {
            @Override
            public void onSuccess(String json) {
                iHomeModelBack.ModelSuccess(json);
            }

            @Override
            public void onError(String msg) {
                iHomeModelBack.ModelError(msg);
            }
        });
    }
}

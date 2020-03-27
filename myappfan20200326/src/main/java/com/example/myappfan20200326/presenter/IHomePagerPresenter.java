package com.example.myappfan20200326.presenter;

import com.example.myappfan20200326.base.BasePresenter;
import com.example.myappfan20200326.base.IBaseView;
import com.example.myappfan20200326.contract.IHomePagerContract;
import com.example.myappfan20200326.model.IHomePagerModel;

public class IHomePagerPresenter extends BasePresenter implements IHomePagerContract.IHomePagerPresenter {
    IHomePagerContract.IHomePagerModel model;
    public IHomePagerPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void getModel() {
        model = new IHomePagerModel();
    }

    @Override
    public void IHomePresenterMethod(String url) {
        model.IHomeModelMethod(url, new IHomePagerContract.IHomePagerModel.IHomeModelBack() {
            @Override
            public void ModelSuccess(String json) {
                //P层调用成功的方法
                IBaseView iBaseView = getView();
                if (iBaseView instanceof IHomePagerContract.IHomePagerView){
                    IHomePagerContract.IHomePagerView iBaseView1 = (IHomePagerContract.IHomePagerView) iBaseView;
                    iBaseView1.IHomeViewSuccess(json);
                }
            }

            @Override
            public void ModelError(String msg) {
                //P层调用失败的方法
                IBaseView iBaseView = getView();
                if (iBaseView instanceof IHomePagerContract.IHomePagerView){
                    IHomePagerContract.IHomePagerView iBaseView1 = (IHomePagerContract.IHomePagerView) iBaseView;
                    iBaseView1.IHomeViewError(msg);
                }
            }
        });
    }
}

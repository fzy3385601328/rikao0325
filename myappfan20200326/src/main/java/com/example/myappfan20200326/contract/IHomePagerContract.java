package com.example.myappfan20200326.contract;
//契约类接口
public interface IHomePagerContract {

    //V层接口
    interface IHomePagerView{
        void IHomeViewSuccess(String json);

        void IHomeViewError(String msg);
    }

    //P层接口
    interface IHomePagerPresenter{
        void IHomePresenterMethod(String url);
    }

    //M层接口
    interface IHomePagerModel{
        void IHomeModelMethod(String url,IHomeModelBack iHomeModelBack);

        interface IHomeModelBack{
            void ModelSuccess(String json);
            void ModelError(String msg);
        }
    }
}

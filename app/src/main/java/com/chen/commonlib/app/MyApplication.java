package com.chen.commonlib.app;


import com.chen.api.base.BaseApplication;
import com.chen.api.http.RequestManager;


public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        RequestManager.getInstance().init(this);// TODO: 2020/5/9  用volley的时候需要初始化,不用就不用初始化
    }
}

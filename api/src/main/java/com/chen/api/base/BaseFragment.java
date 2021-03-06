package com.chen.api.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment {
    protected View rootView;
    private boolean isVisibleToUser = false;//fragment是否可见
    private boolean isViewCreated = false;//View是否加载完成


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(layoutResId(), container, false);
            initView(rootView);
        }
        initButterKnife(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        lazyLoad();
    }

    /**
     * 懒加载
     */
    private void lazyLoad(){
        if (!isVisibleToUser || !isViewCreated) {
            return;
        }
        afterCreated();
    }

    protected abstract void initButterKnife(View rootView);

    protected abstract int layoutResId();

    /**
     * 初始化View
     */
    protected abstract void initView(View rootView);


    /**
     * 做网络请求获取传值等操作
     */
    protected abstract void afterCreated();


    @Override
    public void onDestroyView() {
        isViewCreated = false;
        isVisibleToUser = false;
        if (rootView != null) {
            //Android不允许在容器中添加已有父容器的view
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
        super.onDestroyView();
    }
}

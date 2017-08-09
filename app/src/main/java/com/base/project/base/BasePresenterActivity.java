package com.base.project.base;

import android.os.Bundle;

/**
 * Created by pradmin on 2017/7/26.
 */

public abstract class BasePresenterActivity<V,T extends BasePresenter<V>> extends BaseActivity{
    protected T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onBaseCreate(Bundle savedInstanceState) {
        presenter = createPresenter();
        if (null != presenter) {
            presenter.attachView((V) this);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != presenter) {
            presenter.detachView();
        }
    }

    protected abstract T createPresenter();
}

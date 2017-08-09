/* Top Secret */
/* 绝密 TOP SECRET, COPYRIGHT © AFMOBI GROUP */
package com.base.project.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/11/7.
 */

public abstract class BasePrsenterFragment<V, T extends BasePresenter<V>> extends BaseFragment {
    protected T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        if (null != presenter) {
            presenter.attachView((V) this);
        }
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

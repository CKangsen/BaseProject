/* Top Secret *//* 绝密 TOP SECRET, COPYRIGHT © AFMOBI GROUP */
package com.base.project.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Administator on 2016/10/26.
 */

public abstract class


BasePresenter<T> {
    protected Reference<T> mReference;

    public void attachView(T view) {
        mReference = new WeakReference<T>(view);
    }

    public void detachView() {
        if (mReference != null) {
            mReference.clear();
            mReference = null;
        }
    }

    /**
     * 判断界面是否销毁
     */
    protected boolean isViewAttached() {
        return mReference != null && mReference.get() != null;
    }


    /**
     * 获取界面的引用
     *
     * @return
     */

    protected T getView() {
        return mReference == null ? null : mReference.get();
    }


}

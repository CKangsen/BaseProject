package com.base.project.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by hj on 2017/5/15.
 */

public abstract class BaseFragment extends Fragment {
    protected BackHandledInterface mBackHandledInterface;

    /**
     * 在baseFragement中实现返回按键
     *
     * @return
     */
    public abstract boolean onBackPressed();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof BackHandledInterface)) {
            throw new ClassCastException("Hosting Activity must implement BackHandledInterface");
        } else {
            this.mBackHandledInterface = (BackHandledInterface) getActivity();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //告诉FragmentActivity，当前Fragment在栈顶
        mBackHandledInterface.setSelectedFragment(this);
    }
}

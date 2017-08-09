package com.base.project.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Window;

import com.afmobi.statInterface.statsdk.core.TcStatInterface;
import com.afmobi.wakacloud.ui.ActivityManager;

/**
 * Created by pradmin on 2017/7/24.
 */

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
         //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (!this.isTaskRoot()) { //判断该Activity是不是任务空间的源Activity，“非”也就是说是被系统重新实例化出来
            //如果你就放在launcher Activity中话，这里可以直接return了
            Intent mainIntent = getIntent();
            if (mainIntent != null) {
                String action = mainIntent.getAction();
                if (!TextUtils.isEmpty(action) && mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                    finish();
                    return;//finish()之后该活动会继续执行后面的代码，你可以logCat验证，加return避免可能的exception
                }
            }
        }

        onBaseCreate(savedInstanceState);
          ActivityManager.getActivityManager().pushActivity(this);
         initView(savedInstanceState);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getActivityStack().remove(this);
    }
    /**
     * 设置一个ContentView及其他的一些初始化
     *
     * @param savedInstanceState
     */
    protected abstract void onBaseCreate(Bundle savedInstanceState);

    /**
     * findviewbyid
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);
    public void onResume() {
        super.onResume();
        TcStatInterface.recordPageStart(this); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
        TcStatInterface.onResume(this);
    }
    public void onPause() {
        super.onPause();
        TcStatInterface.recordPageEnd(); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
        TcStatInterface.onPause( );
    }
}

package com.wanshare.wscomponent.logger;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


/**
 * Created by huqj on  2018/8/1.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.init();
    }
}

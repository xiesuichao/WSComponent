package com.wanshare.wscomponent.share;

import android.app.Application;

import com.mob.MobSDK;

/**
 *
 * </br>
 * Date: 2018/7/30 15:07
 *
 * @author hemin
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ShareManager.getInstance().init(this);

    }
}

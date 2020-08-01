package com.wanshare.wscomponent.image;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * 图片加载
 * Created by xiesuichao on 2018/8/4.
 */

public class ImageLoader {

    public static LoadBuilder with(Context context){
        return new ImageBuilder(context);
    }

    public static LoadBuilder with(Activity activity){
        return new ImageBuilder(activity);
    }

    public static LoadBuilder with(FragmentActivity activity){
        return new ImageBuilder(activity);
    }

    public static LoadBuilder with(Fragment fragment){
        return new ImageBuilder(fragment);
    }

}

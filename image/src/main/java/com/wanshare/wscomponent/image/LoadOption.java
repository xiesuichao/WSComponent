package com.wanshare.wscomponent.image;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


/**
 * Created by xiesuichao on 2018/8/7.
 */

public interface LoadOption {

    Object getOptions();

    LoadOption placeHolder(int resourceId);

    LoadOption placeHolder(Drawable drawable);

    LoadOption error(int resourceId);

    LoadOption error(Drawable drawable);

    LoadOption diskCacheStrategy(DiskCacheStrategy diskCacheStrategy);

    LoadOption centerInside();

    LoadOption centerCrop();

    LoadOption fitCenter();

}

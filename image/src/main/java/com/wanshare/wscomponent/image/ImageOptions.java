package com.wanshare.wscomponent.image;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by xiesuichao on 2018/8/7.
 */

public class ImageOptions implements LoadOption {

    private RequestOptions options;

    public ImageOptions() {
        options = new RequestOptions();
    }

    @Override
    public Object getOptions() {
        return this.options;
    }

    public void setOptions(Object object) {
        this.options = (RequestOptions) object;
    }

    @Override
    public LoadOption placeHolder(int resourceId) {
        options.placeholder(resourceId);
        return this;
    }

    @Override
    public LoadOption placeHolder(Drawable drawable) {
        options.placeholder(drawable);
        return this;
    }

    @Override
    public LoadOption error(int resourceId) {
        options.error(resourceId);
        return this;
    }

    @Override
    public LoadOption error(Drawable drawable) {
        options.error(drawable);
        return this;
    }

    @Override
    public LoadOption diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
        options.diskCacheStrategy(diskCacheStrategy);
        return this;
    }

    @Override
    public LoadOption centerInside() {
        options.centerInside();
        return this;
    }

    @Override
    public LoadOption centerCrop() {
        options.centerCrop();
        return this;
    }

    @Override
    public LoadOption fitCenter() {
        options.fitCenter();
        return this;
    }


}

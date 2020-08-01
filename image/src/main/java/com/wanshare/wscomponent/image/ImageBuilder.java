package com.wanshare.wscomponent.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;

import java.io.File;

/**
 * Created by xiesuichao on 2018/8/7.
 */

public class ImageBuilder implements LoadBuilder{

    private RequestManager requestManager;
    private RequestBuilder requestBuilder;

    ImageBuilder(Context context){
        requestManager = Glide.with(context);
    }

    ImageBuilder(Activity activity){
        requestManager = Glide.with(activity);
    }

    ImageBuilder(FragmentActivity activity){
        requestManager = Glide.with(activity);
    }

    ImageBuilder(Fragment fragment){
        requestManager = Glide.with(fragment);
    }

    @Override
    public ImageBuilder load(Uri uri){
        requestBuilder = requestManager.load(uri);
        return this;
    }

    @Override
    public ImageBuilder load(File file){
        requestBuilder = requestManager.load(file);
        return this;
    }

    @Override
    public ImageBuilder load(Bitmap bitmap){
        requestBuilder = requestManager.load(bitmap);
        return this;
    }

    @Override
    public ImageBuilder load(String string){
        requestBuilder = requestManager.load(string);
        return this;
    }

    @Override
    public ImageBuilder load(Drawable drawable){
        requestBuilder = requestManager.load(drawable);
        return this;
    }

    @Override
    public ImageBuilder load(int resourceId){
        requestBuilder = requestManager.load(resourceId);
        return this;
    }

    @Override
    public ImageBuilder load(byte[] bytes){
        requestBuilder = requestManager.load(bytes);
        return this;
    }

    @Override
    public ImageBuilder load(Object obj){
        requestBuilder = requestManager.load(obj);
        return this;
    }

    @Override
    public ImageBuilder into(ImageView iv){
        requestBuilder.into(iv);
        return this;
    }

    @Override
    public ImageBuilder apply(ImageOptions options){
        requestBuilder.apply((RequestOptions) options.getOptions());
        return this;
    }

    @Override
    public ImageBuilder listener(final LoadListener requestListener){
        requestBuilder.listener(new RequestListener(){

            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                requestListener.onLoadFailed();
                return true;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                requestListener.onResourceReady();
                return true;
            }
        });
        return this;
    }


}

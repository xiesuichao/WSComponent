package com.wanshare.wscomponent.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestListener;

import java.io.File;

/**
 * Created by xiesuichao on 2018/8/8.
 */

public interface LoadBuilder {

    LoadBuilder load(Uri uri);

    LoadBuilder load(File file);

    LoadBuilder load(Bitmap bitmap);

    LoadBuilder load(String string);

    LoadBuilder load(Drawable drawable);

    LoadBuilder load(int resourceId);

    LoadBuilder load(byte[] bytes);

    LoadBuilder load(Object obj);

    LoadBuilder into(ImageView iv);

    LoadBuilder apply(ImageOptions options);

    LoadBuilder listener(LoadListener loadListener);

}

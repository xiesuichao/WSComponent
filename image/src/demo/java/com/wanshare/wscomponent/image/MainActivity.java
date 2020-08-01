package com.wanshare.wscomponent.image;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv1 = findViewById(R.id.iv1);
        ImageView iv2 = findViewById(R.id.iv2);

        String imgUrl = "http://seopic.699pic.com/photo/00026/7248.jpg_wh1200.jpg";

        //Glide 4.0 使用方式
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.bch).fitCenter();
        Glide.with(this)
                .load(imgUrl)
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        PrintUtil.log("Glide onLoadFailed");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        PrintUtil.log("Glide onResourceReady");
                        return false;
                    }
                })
                .into(iv1);




        //ImageLoader 使用方式
        ImageOptions options = new ImageOptions();
        options.placeHolder(R.drawable.bch);
        ImageLoader.with(this)
                .load(imgUrl)
                .apply(options)
                .listener(new LoadListener() {
                    @Override
                    public boolean onLoadFailed() {
                        PrintUtil.log("ImageLoader onLoadFailed");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady() {
                        PrintUtil.log("ImageLoader onResourceReady");
                        return false;
                    }
                })
                .into(iv2);




    }




}

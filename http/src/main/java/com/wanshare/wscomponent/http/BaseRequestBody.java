package com.wanshare.wscomponent.http;

import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.annotations.Nullable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public abstract class BaseRequestBody extends RequestBody {

    private static MediaType MEDIA_TYPE = MediaType.parse("application/json");

    public static RequestBody create(Object object){
        return create(MEDIA_TYPE, new Gson().toJson(object));
    }

    public static RequestBody create(MediaType type, Object object){
        return create(type, new Gson().toJson(object));
    }

}

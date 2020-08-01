package com.wanshare.wscomponent.websocket;


import com.wanshare.wscomponent.websocket.bean.DepthEntity;

/**
 * 深度数据响应包装类
 * Created by Eric on 2018/9/25
 */
public class DepthResponse implements Response<DepthEntity>{

    private int mType;
    private DepthEntity mDepth;

    public DepthResponse(DepthEntity depth){
        this.mDepth = depth;
    }

    @Override
    public String getResponseText() {
        return null;
    }

    @Override
    public void setResponseText(String responseText) {

    }

    @Override
    public int getType() {
        return mType;
    }

    @Override
    public void setType(int type) {
        this.mType = type;
    }

    @Override
    public DepthEntity getResponseEntity() {
        return mDepth;
    }

    @Override
    public void setResponseEntity(DepthEntity depth) {
        this.mDepth = depth;
    }
}

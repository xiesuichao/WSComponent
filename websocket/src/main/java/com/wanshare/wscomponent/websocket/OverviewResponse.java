package com.wanshare.wscomponent.websocket;


import com.wanshare.wscomponent.websocket.bean.OverviewEntity;

/**
 * 行情概要数据响应包装类
 * Created by Eric on 2018/9/25
 */
public class OverviewResponse implements Response<OverviewEntity>{

    private int mType;
    private OverviewEntity mOverview;

    public OverviewResponse(OverviewEntity overview){
        this.mOverview = overview;
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
    public OverviewEntity getResponseEntity() {
        return mOverview;
    }

    @Override
    public void setResponseEntity(OverviewEntity overview) {
        this.mOverview = overview;
    }
}

package com.wanshare.wscomponent.websocket;


import com.wanshare.wscomponent.websocket.bean.KlineEntity;

/**
 * K线数据响应包装类
 * Created by Eric on 2018/9/25
 */
public class KlineResponse implements Response<KlineEntity>{

    private int mType;
    private KlineEntity mKline;

    public KlineResponse(KlineEntity kline){
        this.mKline = kline;
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
        mType = type;
    }

    @Override
    public KlineEntity getResponseEntity() {
        return mKline;
    }

    @Override
    public void setResponseEntity(KlineEntity kline) {
        this.mKline = kline;
    }
}

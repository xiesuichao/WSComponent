package com.wanshare.wscomponent.websocket;

import com.wanshare.wscomponent.websocket.bean.SummaryEntity;

import java.util.List;

/**
 * 单一交易所下单一分区的行情概要数据 响应包装类
 * Created by Eric on 2018/9/25
 */
public class SummaryResponse implements Response<List<SummaryEntity>>{

    private int mType;
    private List<SummaryEntity> mSummary;

    public SummaryResponse(List<SummaryEntity> summary){
        this.mSummary = summary;
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
    public List<SummaryEntity> getResponseEntity() {
        return mSummary;
    }

    @Override
    public void setResponseEntity(List<SummaryEntity> summary) {
        this.mSummary = summary;
    }
}

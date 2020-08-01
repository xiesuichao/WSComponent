package com.wanshare.wscomponent.websocket;

import com.wanshare.wscomponent.websocket.bean.TradeHistoryEntity;

import java.util.List;

/**
 * 成交记录数据 响应包装类
 * Created by Eric on 2018/9/25
 */
public class TradeHistoryResponse implements Response<List<TradeHistoryEntity>>{

    private int mType;
    private List<TradeHistoryEntity> mTradeHistory;

    public TradeHistoryResponse(List<TradeHistoryEntity> tradeHistory){
        this.mTradeHistory = tradeHistory;
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
    public List<TradeHistoryEntity> getResponseEntity() {
        return mTradeHistory;
    }

    @Override
    public void setResponseEntity(List<TradeHistoryEntity> tradeHistory) {
        this.mTradeHistory = tradeHistory;
    }
}

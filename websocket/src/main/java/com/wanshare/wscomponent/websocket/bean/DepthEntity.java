package com.wanshare.wscomponent.websocket.bean;

import java.util.List;

/**
 * 深度数据体
 * Created by Eric on 2018/9/26
 */
public class DepthEntity {


    private List<List<String>> asks;
    private List<List<String>> bids;

    public List<List<String>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<String>> asks) {
        this.asks = asks;
    }

    public List<List<String>> getBids() {
        return bids;
    }

    public void setBids(List<List<String>> bids) {
        this.bids = bids;
    }
}

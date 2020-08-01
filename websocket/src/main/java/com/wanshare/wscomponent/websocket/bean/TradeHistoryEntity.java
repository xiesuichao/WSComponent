package com.wanshare.wscomponent.websocket.bean;

/**
 * 成交记录数据体
 * Created by Eric on 2018/9/26
 */
public class TradeHistoryEntity {

    /**
     * price : 6270.89507906
     * volume : 616.26555344
     * createdAt : 2018-09-25T09:36:48.894Z
     */

    private double price;
    private double volume;
    private String createdAt;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}

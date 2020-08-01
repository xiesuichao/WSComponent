package com.wanshare.wscomponent.websocket.bean;

/**
 * K线数据体
 * Created by Eric on 2018/9/26
 */
public class KlineEntity {

    /**
     * time : 1538105929
     * highestPrice : 19
     * openPrice : 0
     * lowestPrice : 6
     * closePrice : 10
     * volume : 60
     * period : 1d
     */

    private int time;
    private int highestPrice;
    private int openPrice;
    private int lowestPrice;
    private int closePrice;
    private int volume;
    private String period;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(int highestPrice) {
        this.highestPrice = highestPrice;
    }

    public int getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(int openPrice) {
        this.openPrice = openPrice;
    }

    public int getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(int lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public int getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(int closePrice) {
        this.closePrice = closePrice;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}

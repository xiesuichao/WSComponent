package com.wanshare.wscomponent.websocket.bean;

/**
 * 单一交易所下单一分区的行情概要数据体
 * Created by Eric on 2018/9/26
 */
public class SummaryEntity {


    /**
     * highestPrice : 6432.8763456
     * lowestPrice : 6324.56789765
     * amount : 2134565.3456752
     * sellerFee : 32.2345678
     * cnyPrice : 43481.5505779
     * openPrice : 6323.2345
     * buyerCoinName : USDT
     * sellerCoinUrl : logo/btc.png
     * changePct : 0.3636
     * sellerCoinName : BTC
     * marketId : 11111
     * volume : 342.345678
     * exchangeId : 100000
     * buyerFee : 345.23456
     * exchangeName : wanshare
     * closePrice : 6394.34567322
     */

    private double highestPrice;
    private double lowestPrice;
    private double amount;
    private double sellerFee;
    private double cnyPrice;
    private double openPrice;
    private String buyerCoinName;
    private String sellerCoinUrl;
    private double changePct;
    private String sellerCoinName;
    private String marketId;
    private double volume;
    private int exchangeId;
    private double buyerFee;
    private String exchangeName;
    private double closePrice;

    public double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getSellerFee() {
        return sellerFee;
    }

    public void setSellerFee(double sellerFee) {
        this.sellerFee = sellerFee;
    }

    public double getCnyPrice() {
        return cnyPrice;
    }

    public void setCnyPrice(double cnyPrice) {
        this.cnyPrice = cnyPrice;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public String getBuyerCoinName() {
        return buyerCoinName;
    }

    public void setBuyerCoinName(String buyerCoinName) {
        this.buyerCoinName = buyerCoinName;
    }

    public String getSellerCoinUrl() {
        return sellerCoinUrl;
    }

    public void setSellerCoinUrl(String sellerCoinUrl) {
        this.sellerCoinUrl = sellerCoinUrl;
    }

    public double getChangePct() {
        return changePct;
    }

    public void setChangePct(double changePct) {
        this.changePct = changePct;
    }

    public String getSellerCoinName() {
        return sellerCoinName;
    }

    public void setSellerCoinName(String sellerCoinName) {
        this.sellerCoinName = sellerCoinName;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(int exchangeId) {
        this.exchangeId = exchangeId;
    }

    public double getBuyerFee() {
        return buyerFee;
    }

    public void setBuyerFee(double buyerFee) {
        this.buyerFee = buyerFee;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }
}

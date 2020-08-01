package com.wanshare.wscomponent.websocket;

/**
 * 负责 WebSocketService 消息订阅和响应处理操作
 * Created by Eric on 2018/10/8
 */
public class WebSocketServiceManager {


    private ResponseDelivery mResponseDelivery = new ResponseDelivery();

    public WebSocketServiceManager(SocketListener socketListener){
        mResponseDelivery.addListener(socketListener);
    }

    public void removeSocketListener(SocketListener socketListener){
        mResponseDelivery.removeListener(socketListener);
    }

    /**
     * 订阅K 线数据
     * @param marketId  市场id
     * @param period    K线周期
     */
    public void sendSubscribeKline(String marketId,String period){

    }

    /**
     * 订阅深度数据
     * @param marketId  市场id
     */
    public void sendSubscribeDepth(String marketId){

    }

    /**
     * 订阅行情概要数据
     * @param marketId  市场id
     */
    public void sendSubscribeOverview(String marketId){

    }

    /**
     * 订阅成交记录数据
     * @param marketId  市场id
     */
    public void sendSubscribeTradeHistory(String marketId){

    }

    /**
     * 订阅单一交易所下单一分区行情概要数据
     * @param exchangeId   交易所id
     * @param buyerCoinId  买方币种id
     */
    public void sendSubscribeSummary(String exchangeId, String buyerCoinId){

    }

}

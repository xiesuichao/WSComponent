package com.wanshare.wscomponent.websocket;

/**
 * 通用消息调度器，没做任何数据处理
 * Created by Eric on 2018/8/13
 */
public class DefaultResponseDispatcher implements IResponseDispatcher {

    @Override
    public void onConnected(ResponseDelivery delivery) {
        delivery.onConnected();
    }

    @Override
    public void onConnectError(Throwable cause, ResponseDelivery delivery) {
        delivery.onConnectError(cause);
    }

    @Override
    public void onDisconnected(ResponseDelivery delivery) {
        delivery.onDisconnected();
    }

    @Override
    public void onMessageResponse(Response message, ResponseDelivery delivery) {
        delivery.onMessageResponse(message);
    }

    @Override
    public void onSendMessageError(ErrorResponse error, ResponseDelivery delivery) {
        delivery.onSendMessageError(error);
    }
}

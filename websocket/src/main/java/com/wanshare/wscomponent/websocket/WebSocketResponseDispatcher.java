package com.wanshare.wscomponent.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.wanshare.wscomponent.websocket.bean.DepthEntity;
import com.wanshare.wscomponent.websocket.bean.KlineEntity;
import com.wanshare.wscomponent.websocket.bean.OverviewEntity;
import com.wanshare.wscomponent.websocket.bean.SummaryEntity;
import com.wanshare.wscomponent.websocket.bean.TradeHistoryEntity;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Eric on 2018/9/27.
 */
public class WebSocketResponseDispatcher implements IResponseDispatcher {

    private static final String LOGTAG = "WebSocketResponseDispatcher";

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

    /**
     * 统一处理响应的数据
     */
    @Override
    public void onMessageResponse(Response message, ResponseDelivery delivery) {

        int type = message.getType();
        Gson gson = new Gson();

        switch (type) {
            case MessageType.DEPTH:
                try {
                    DepthEntity depthEntity = gson.fromJson(message.getResponseText(),DepthEntity.class);
                    DepthResponse depthResponse = new DepthResponse(depthEntity);
                    depthResponse.setType(MessageType.DEPTH);
                    delivery.onMessageResponse(depthResponse);
                }catch (JsonSyntaxException e){
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setResponseText(message.getResponseText());
                    errorResponse.setErrorCode(MessageType.JASON_DECODE_ERROR);
                    errorResponse.setCause(e);
                    onSendMessageError(errorResponse, delivery);
                }
                break;
            case MessageType.KLINE:
                try {
                    KlineEntity klineEntity = gson.fromJson(message.getResponseText(),KlineEntity.class);
                    KlineResponse klineResponse = new KlineResponse(klineEntity);
                    klineResponse.setType(MessageType.KLINE);
                    delivery.onMessageResponse(klineResponse);
                }catch (JsonSyntaxException e){
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setResponseText(message.getResponseText());
                    errorResponse.setErrorCode(MessageType.JASON_DECODE_ERROR);
                    errorResponse.setCause(e);
                    onSendMessageError(errorResponse, delivery);
                }
                break;
            case MessageType.OVERVIEW:
                try {
                    OverviewEntity overviewEntity = gson.fromJson(message.getResponseText(), OverviewEntity.class);
                    OverviewResponse overviewResponse = new OverviewResponse(overviewEntity);
                    overviewResponse.setType(MessageType.OVERVIEW);
                    delivery.onMessageResponse(overviewResponse);
                }catch (JsonSyntaxException e){
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setResponseText(message.getResponseText());
                    errorResponse.setErrorCode(MessageType.JASON_DECODE_ERROR);
                    errorResponse.setCause(e);
                    onSendMessageError(errorResponse, delivery);
                }
                break;
            case MessageType.SUMMARY:
                try {
                    Type listType = new TypeToken<List<SummaryEntity>>(){}.getType();
                    List<SummaryEntity> summaryEntity = gson.fromJson(message.getResponseText(), listType);
                    SummaryResponse summaryResponse = new SummaryResponse(summaryEntity);
                    summaryResponse.setType(MessageType.SUMMARY);
                    delivery.onMessageResponse(summaryResponse);
                }catch (JsonSyntaxException e){
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setResponseText(message.getResponseText());
                    errorResponse.setErrorCode(MessageType.JASON_DECODE_ERROR);
                    errorResponse.setCause(e);
                    onSendMessageError(errorResponse, delivery);
                }
                break;
            case MessageType.TRADEHISTORY:
                try {
                    Type listType = new TypeToken<List<TradeHistoryEntity>>(){}.getType();
                    List<TradeHistoryEntity> tradehistoryEntity = gson.fromJson(message.getResponseText(), listType);
                    TradeHistoryResponse tradehistoryResponse = new TradeHistoryResponse(tradehistoryEntity);
                    tradehistoryResponse.setType(MessageType.TRADEHISTORY);
                    delivery.onMessageResponse(tradehistoryResponse);
                }catch (JsonSyntaxException e){
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setResponseText(message.getResponseText());
                    errorResponse.setErrorCode(MessageType.JASON_DECODE_ERROR);
                    errorResponse.setCause(e);
                    onSendMessageError(errorResponse, delivery);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 统一处理错误信息，
     * 界面上可使用 ErrorResponse#getDescription() 来当做提示语
     */
    @Override
    public void onSendMessageError(ErrorResponse error, ResponseDelivery delivery) {
        switch (error.getErrorCode()) {
            case MessageType.SERVICE_BIND_ERROR:
                error.setDescription("websocket 服务未绑定");
                break;
            case MessageType.JASON_DECODE_ERROR:
                error.setDescription("数据格式异常");
                break;
        }
        delivery.onSendMessageError(error);
    }
}

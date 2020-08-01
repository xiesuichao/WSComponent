package com.wanshare.wscomponent.websocket;

/**
 * 消息类型
 * Created by Eric on 2018/8/13
 */
public class MessageType {

    //websocket 状态与数据收发类型
    public static final int CONNECT = 0;//连接Socket
    public static final int DISCONNECT = 1;//断开连接，主动关闭或被动关闭
    public static final int QUIT = 2;//结束线程
    public static final int SEND_MESSAGE = 3;//通过Socket连接发送数据
    public static final int RECEIVE_MESSAGE = 4;//通过Socket获取到数据

    //websocket 解析数据类型
    public static final int DEPTH = 1001;//深度
    public static final int KLINE = 1002;//k线
    public static final int OVERVIEW = 1003;//行情概要
    public static final int SUMMARY = 1004; //单一交易所下单一分区的行情概要
    public static final int TRADEHISTORY = 1005; //成交记录

    //websocket 错误信息编码
    public static final int SERVICE_BIND_ERROR = 10001; //websocket 服务未绑定
    public static final int JASON_DECODE_ERROR = 10002; //json 数据解析错误

    //websocket 订阅数据基本模版
    public static final String MESSAGE_ENCODE_MODLE = "[\"SUBSCRIBE\\nid:sub-0\\ndestination:/topic/crush/%s\\n\\n\\u0000\"]";

    //websocket 订阅数据类型、解析数据同样可以用到
    public static final String MESSAGE_KLINE = "crush/k-line";  //k线
    public static final String MESSAGE_OVERVIEW = "crush/overview";  //行情概要
    public static final String MESSAGE_DEPTH = "crush/depth"; //深度
    public static final String MESSAGE_TRADEHISTORY = "crush/trade-history"; //成交记录
    public static final String MESSAGE_SUMMARY = "crush/partition-summary"; //单一交易所下单一分区的行情概要


}

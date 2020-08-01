package com.wanshare.wscomponent.websocket;

import android.app.Application;
import android.content.Intent;


/**
 * Created by ZhangKe on 2018/6/27.
 */
public class MyApp extends Application {

    String webUrl = "ws://192.168.80.211:8888/v1/crush/k-line/331/13atbidg/websocket";//"ws://192.168.21.68:8888/v1/crush/k-line/707/ghboehys/websocket";//"ws://192.168.21.14:8888/v1/depth/612/uiryleuy/websocket";
    String testUrl = "wss://www.wanshare.com/socket.io/?EIO=3&transport=websocket";

    @Override
    public void onCreate() {
        super.onCreate();

        //配置 WebSocket，必须在 WebSocket 服务启动前设置
        WebSocketSetting.setConnectUrl(webUrl);//必选
        WebSocketSetting.setResponseProcessDelivery(new WebSocketResponseDispatcher());
        WebSocketSetting.setReconnectWithNetworkChanged(true);

        //启动 WebSocket 服务
        startService(new Intent(this, WebSocketService.class));
    }
}

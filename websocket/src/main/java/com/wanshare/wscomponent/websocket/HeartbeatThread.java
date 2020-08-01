package com.wanshare.wscomponent.websocket;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;

/**
 * WebSocket 心跳线程，
 * 负责 WebSocket 心跳发送。
 * <p>
 * Created by Eric on 2018/9/20
 */
public class HeartbeatThread extends Thread {

    //发送心跳包参数
    private final String WEBSOCKET_HEART_SEND = "2";
    //心跳时间
    private final int HEART_TIME = 20000;

    private WebSocketClient mWebSocket;

    public void setWebSocketClient(WebSocketClient webSocketClient){
        this.mWebSocket = webSocketClient;
    }


    @Override
    public void run(){

        while (true){
            Log.d("HeartbeatThread", "发送心跳");
            if (mWebSocket != null && mWebSocket.isOpen()) {
                mWebSocket.send(WEBSOCKET_HEART_SEND);
            }

            try {
                Thread.sleep(HEART_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

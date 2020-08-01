package com.wanshare.wscomponent.websocket;

/**
 * WebSocket 响应数据接口
 * Created by Eric on 2018/8/13
 */
public interface Response<T> {

    /**
     * 获取响应的文本数据
     */
    String getResponseText();

    /**
     * 设置响应的文本数据
     */
    void setResponseText(String responseText);

    /**
     * 获取响应数据类型
     */
    int getType();

    /**
     * 设置响应数据类型
     * @param type
     */
    void setType(int type);

    /**
     * 获取该数据的实体，可能为空，具体看实现类
     */
    T getResponseEntity();

    /**
     * 设置数据实体
     */
    void setResponseEntity(T responseEntity);
}

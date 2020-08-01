package com.wanshare.wscomponent.websocket;

/**
 * 默认的消息响应事件包装类，
 * 只包含文本，不包含数据实体
 * Created by Eric on 2018/8/13
 */
public class TextResponse implements Response<String> {

    private int mType;
    private String mResponseText;

    public TextResponse(String responseText,int type) {
        this.mResponseText = responseText;
        this.mType = type;
    }

    public String getResponseText() {
        return mResponseText;
    }

    public void setResponseText(String responseText) {
        this.mResponseText = responseText;
    }

    @Override
    public int getType() {
        return mType;
    }

    @Override
    public void setType(int type) {
        this.mType = type;
    }

    public String getResponseEntity() {
        return null;
    }

    public void setResponseEntity(String responseEntity) {
    }
}

package com.wanshare.wscomponent.share;

/**
 * 分享参数
 * </br>
 * Date: 2018/7/30 10:43
 *
 * @author hemin
 */
public class ShareParam {
    // 分享的标题
    private String title;
    // 分享的url
    private String url;
    // 分享的文本信息
    private String text;
    // 分享的网络图片地址
    private String imageUrl;
    // 分享本地图片的地址
    private String imagePath;

    public String getTitle() {
        return title;
    }

    public ShareParam setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ShareParam setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getText() {
        return text;
    }

    public ShareParam setText(String text) {
        this.text = text;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShareParam setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public ShareParam setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

}

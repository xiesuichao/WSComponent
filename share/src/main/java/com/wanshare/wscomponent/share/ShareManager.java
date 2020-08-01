package com.wanshare.wscomponent.share;

import android.content.Context;
import android.text.TextUtils;
import android.view.TextureView;

import com.mob.MobSDK;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.linkedin.LinkedIn;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.twitter.Twitter;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * 分享管理类
 * </br>
 * Date: 2018/7/30 10:50
 *
 * @author hemin
 */
public class ShareManager {

    private Context mContext;

    private static class SingletonHolder {
        private static ShareManager INSTANCE = new ShareManager();
    }

    public static ShareManager getInstance() {
        return ShareManager.SingletonHolder.INSTANCE;
    }

    /**
     * 需要在Manifest清单文件中配置：tools:replace=”android:name”
     *
     * @param appContext 使用ApplicationContext
     */
    public void init(Context appContext) {
        MobSDK.init(appContext);
        this.mContext = appContext;
    }


    public void shareTo(ShareParam param) {

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        if(param!=null && !TextUtils.isEmpty(param.getTitle())){
            oks.setTitle(param.getTitle());
        }

        // titleUrl QQ和QQ空间跳转链接
        if(param!=null && !TextUtils.isEmpty(param.getUrl())){
            oks.setTitleUrl(param.getUrl());
        }

        // text是分享文本，所有平台都需要这个字段
        if(param!=null && !TextUtils.isEmpty(param.getText())){
            oks.setText(param.getText());
        }
        if(param!=null && !TextUtils.isEmpty(param.getImageUrl())){
            oks.setImageUrl(param.getImageUrl());
        }
        // url在微信、微博，Facebook等平台中使用
        if(param!=null && !TextUtils.isEmpty(param.getUrl())){
            oks.setUrl(param.getUrl());
        }

        if(param!=null && !TextUtils.isEmpty(param.getImagePath())){
            oks.setImagePath(param.getImagePath());
        }

        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                if (Twitter.NAME.equalsIgnoreCase(platform.getName())
                        || LinkedIn.NAME.equalsIgnoreCase(platform.getName())
                        ) {
                    String fmessage = paramsToShare.getText();
                    String url = paramsToShare.getUrl();
                    //LinkedIn Twitter下只支持 text 字段
                    paramsToShare.setText(fmessage + url);
                    paramsToShare.setTitle(null);
                    paramsToShare.setTitleUrl(null);
                    paramsToShare.setImagePath(null);
                    paramsToShare.setImageUrl(null);
                } else if (Wechat.NAME.equalsIgnoreCase(platform.getName())) {
                    paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                }
            }
        });

        // 启动分享GUI
        oks.show(this.mContext);
    }

}

package com.wanshare.wscomponent.gfw.core;

import android.graphics.drawable.Drawable;

/**
 * app 信息类模版
 * Created by Eric on 2018/8/28.
 */
public class AppInfo {
    private Drawable appIcon;
    private String appLabel;
    private String pkgName;

    public AppInfo() {
    }

    public Drawable getAppIcon() {
        return this.appIcon;
    }

    public String getAppLabel() {
        return this.appLabel;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public void setAppIcon(Drawable var1) {
        this.appIcon = var1;
    }

    public void setAppLabel(String var1) {
        this.appLabel = var1;
    }

    public void setPkgName(String var1) {
        this.pkgName = var1;
    }
}

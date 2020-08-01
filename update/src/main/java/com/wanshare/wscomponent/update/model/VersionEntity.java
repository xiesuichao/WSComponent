package com.wanshare.wscomponent.update.model;

/**
 * Created by Venn on 2017/7/27.
 */

public class VersionEntity {


    //平台
    private String equip;
    //版本
    private String version;
    //升级url
    private String addressUrl;
    //强制升级
    private String isCompel;
    //更新时间
    private String updatedAt;
    //创建时间
    private String createdAt;
    //描述
    private String describe;
    //安装包大小 单位字节
    public String fileSize = "0";

    public String locaVersionName;

    public VersionEntity() {
    }

    public VersionEntity(String version, String addressUrl, String isCompel, String describe, String fileSize) {
        this.version = version;
        this.addressUrl = addressUrl;
        this.isCompel = isCompel;
        this.describe = describe;
        this.fileSize = fileSize;
    }

    public boolean getIsCompel(){
        return "true".equals(isCompel);
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAddressUrl() {
        return addressUrl;
    }

    public void setAddressUrl(String addressUrl) {
        this.addressUrl = addressUrl;
    }

    public String getMustUpdate() {
        return isCompel;
    }

    public void setIsCompel(String isCompel) {
        this.isCompel = isCompel;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }


    public String getFileSize() {
        return fileSize;
    }

    public void setLocaVersionName(String locaVersionName) {
        this.locaVersionName = locaVersionName;
    }

    public String getLocaVersionName() {
        return locaVersionName;
    }
}

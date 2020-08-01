package com.wanshare.wscomponent;

import java.util.List;

/**
 * 权限申请回调接口
 *
 * @author wangdunwei
 * @date 2018/8/7
 */
public interface PermissionCallback {
    /**
     * 权限通过回调
     *
     * @param data 通过的权限列表
     */
    void yes(List<String> data);

    /**
     * 权限拒绝回调
     *
     * @param data 拒绝的权限列表
     */
    void no(List<String> data);
}

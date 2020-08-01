package com.wanshare.wscomponent.datamanager;

/**
 * Created by xiesuichao on 2018/7/25.
 */

public interface DataCacheAble {

    String getCacheKey();

    long getSaveTime();

    byte[] toByteArr();

    void fromByteArr(byte[] bytes);

}

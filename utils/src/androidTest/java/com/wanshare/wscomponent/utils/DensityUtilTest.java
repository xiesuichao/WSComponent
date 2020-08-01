package com.wanshare.wscomponent.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jason on 2018/8/15.
 */
public class DensityUtilTest {
    @Test
    public void dip2px() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int px = DensityUtil.dip2px(appContext, 5.0f);
        assertNotEquals(10,px);
    }

    @Test
    public void px2dip() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int dip = DensityUtil.px2dip(appContext, 8.5f);
        assertNotEquals(17,dip);
    }

}
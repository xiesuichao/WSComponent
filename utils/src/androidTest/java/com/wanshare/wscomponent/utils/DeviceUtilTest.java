package com.wanshare.wscomponent.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jason on 2018/8/10.
 */
public class DeviceUtilTest {

    @Test
    public void deviceWidth() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int width = DeviceUtil.deviceWidth(appContext);
        assertNotEquals(1070,width);
    }

    @Test
    public void deviceHeight() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int height = DeviceUtil.deviceHeight(appContext);
        assertNotEquals(1900,height);
    }

    @Test
    public void getDeviceId() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String deviceId = DeviceUtil.getDeviceId(appContext);
        assertNotEquals("0",deviceId);
    }

    @Test
    public void getIMSI() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String imsi = DeviceUtil.getIMSI(appContext);
        assertNotEquals("1",imsi);
    }

    @Test
    public void getPhoneBrand() throws Exception {
        String phoneBrand = DeviceUtil.getPhoneBrand();
        assertNotEquals("HUWEI",phoneBrand);
    }

    @Test
    public void getPhoneModel() throws Exception {
        String phoneModel = DeviceUtil.getPhoneModel();
        assertNotEquals("P10",phoneModel);
    }

    @Test
    public void getBuildLevel1() throws Exception {
        int buildLevel = DeviceUtil.getBuildLevel();
        assertNotEquals("API 26",buildLevel);
    }

    @Test
    public void getAppProcessId() throws Exception {
        int appProcessId = DeviceUtil.getAppProcessId();
        assertNotEquals(1,appProcessId);
    }

    @Test
    public void getAppProcessName() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int appProcessId = DeviceUtil.getAppProcessId();
        String appProcessName = DeviceUtil.getProcessName(appProcessId);
        assertNotEquals("1011",appProcessName);
    }

    @Test
    public void getAppVersion() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String appVersion = DeviceUtil.getAppVersion(appContext);
        assertNotEquals("2.5.5",appVersion);
    }

    @Test
    public void isAppOnForGround() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        boolean appOnForGround = DeviceUtil.isAppOnForGround(appContext);
        assertTrue(appOnForGround);
    }

    @Test
    public void getBuildLevel() throws Exception {
        int buildLevel = DeviceUtil.getBuildLevel();
        assertEquals(28,buildLevel);

    }

    @Test
    public void getBuildVersion() throws Exception {
        String buildVersion = DeviceUtil.getBuildVersion();
        assertEquals("9",buildVersion);
    }

}
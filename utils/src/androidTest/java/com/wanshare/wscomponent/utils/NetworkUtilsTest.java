package com.wanshare.wscomponent.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jason on 2018/8/15.
 */
public class NetworkUtilsTest {
    @Test
    public void isNetworkAvailable() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        boolean networkAvailable = NetworkUtils.isNetworkAvailable(appContext);
        assertTrue(networkAvailable);
    }

    @Test
    public void isGpsEnabled() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        boolean gpsEnabled = NetworkUtils.isGpsEnabled(appContext);
        assertFalse(gpsEnabled);
    }

    @Test
    public void isWifiEnabled() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        boolean wifiEnabled = NetworkUtils.isWifiEnabled(appContext);
        assertTrue(wifiEnabled);
    }

    @Test
    public void isWifi() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        boolean wifi = NetworkUtils.isWifi(appContext);
        assertTrue(wifi);
    }

    @Test
    public void is3G() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        boolean g = NetworkUtils.is3G(appContext);
        assertFalse(g);
    }

}
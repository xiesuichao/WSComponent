package com.wanshare.wscomponent.utils;

import com.wanshare.wscomponent.utils.DateUtil;
import com.wanshare.wscomponent.utils.DeviceUtil;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Jason on 2018/8/3.
 */

public class DateUtilTest {

    @Test
    public void testDataFormat() {
        Date date = new Date();
        String s = DateUtil.dateFormat(date, DateUtil.FORMAT_DATE);
        String currentTime = DateUtil.longToString(System.currentTimeMillis(), DateUtil.FORMAT_DATE);
        assertEquals(currentTime, s);
    }

    @Test
    public void testCurrentDate() {
        String currentDate = DateUtil.getCurrentDate();
        String currentTime = DateUtil.longToString(System.currentTimeMillis(), DateUtil.FORMAT_DATE_TIME);
        assertEquals(currentTime, currentDate);
    }

    @Test
    public void testLongToString() {
        String s = DateUtil.longToString(System.currentTimeMillis(), DateUtil.FORMAT_DATE_TIME);
        String currentTime = DateUtil.getCurrentTime(DateUtil.FORMAT_DATE_TIME);
        assertEquals(currentTime, s);
    }

    @Test
    public void testLongToData() {
        long currentTimeMillis = System.currentTimeMillis();
        Date date = DateUtil.longToDate(currentTimeMillis, DateUtil.FORMAT_DATE_BEJI);
        Date currenData = new Date(currentTimeMillis);
        assertEquals(currenData.toString(), date.toString());
    }


    @Test
    public void testUtcTimeToBeijingTime() {
        String s = DateUtil.utcTimeToBeijingTime("2018-09-23T14:05:26Z");
        assertEquals("2018-09-23 22:05:26", s);
    }

    @Test
    public void testCurrentTime() {
        String currentTime = DateUtil.getCurrentTime(DateUtil.FORMAT_DATE_TIME);
        String s = DateUtil.longToString(System.currentTimeMillis(), DateUtil.FORMAT_DATE_TIME);
        assertEquals(s, currentTime);
    }

}

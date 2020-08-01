package com.guoziwei.klinelib.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * DoubleUtil单元测试
 * </br>
 * Date: 2018/8/21 14:16
 *
 * @author hemin
 */
public class DoubleUtilTest {

    @Test
    public void testFormatYaxisValueMoreThanMax() {
        String result= DoubleUtil.formatValue(12345.245d);
        assertEquals("1.23万",result);
    }

    @Test
    public void testFormatNan() {
        String result= DoubleUtil.formatValue(Double.NaN);
        assertEquals("",result);
    }

    @Test
    public void testFormatYaxisValueMoreThanMaxRound() {
        String result= DoubleUtil.formatValue(12351.245d);
        assertEquals("1.23万",result);
    }

    @Test
    public void testFormatYaxisValueMoreThanMaxTail() {
        String result= DoubleUtil.formatValue(12000d);
        assertEquals("1.2万",result);
    }

    @Test
    public void testFormatYaxisValueMoreThan100() {
        String result= DoubleUtil.formatValue(123.244d);
        assertEquals("123.24",result);
    }

    @Test
    public void testFormatYaxisValueMoreThan100Round() {
        String result= DoubleUtil.formatValue(123.245d);
        assertEquals("123.24",result);
    }

    @Test
    public void testFormatYaxisValueMoreThan100Tail() {
        String result= DoubleUtil.formatValue(123.200d);
        assertEquals("123.2",result);
    }

    @Test
    public void testFormatYaxisValueMoreThan10() {
        String result= DoubleUtil.formatValue(12.2444d);
        assertEquals("12.244",result);
    }

    @Test
    public void testFormatYaxisValueMoreThan10Round() {
        String result= DoubleUtil.formatValue(12.2445d);
        assertEquals("12.244",result);
    }

    @Test
    public void testFormatYaxisValueMoreThan10Tail() {
        String result= DoubleUtil.formatValue(12.200d);
        assertEquals("12.2",result);
    }

    @Test
    public void testFormatYaxisValueMoreThan1() {
        String result= DoubleUtil.formatValue(1.24444d);
        assertEquals("1.2444",result);
    }

    @Test
    public void testFormatYaxisValueMoreThan1Round() {
        String result= DoubleUtil.formatValue(1.24445d);
        assertEquals("1.2444",result);
    }

    @Test
    public void testFormatYaxisValueMoreThan1Tail() {
        String result= DoubleUtil.formatValue(1.24000d);
        assertEquals("1.24",result);
    }

    @Test
    public void testFormatYaxisValueLessThan1() {
        String result= DoubleUtil.formatValue(0.2444444d);
        assertEquals("0.244444",result);
    }

    @Test
    public void testFormatYaxisValueLessThan1Round() {
        String result= DoubleUtil.formatValue(0.2444445d);
        assertEquals("0.244444",result);
    }

    @Test
    public void testFormatYaxisValueLessThan1Tail() {
        String result= DoubleUtil.formatValue(0.2444d);
        assertEquals("0.2444",result);
    }

    @Test
    public void testformatValueWithSignPlus(){
        String result= DoubleUtil.formatValueWithSign(0.2444d);
        assertEquals("+0.2444",result);
    }

    @Test
    public void testformatValueWithSignMinus(){
        String result= DoubleUtil.formatValueWithSign(-0.2444d);
        assertEquals("-0.2444",result);
    }

    @Test
    public void testFormatFloatValueMoreThanMax() {
        String result= DoubleUtil.formatValue(12345.245f);
        assertEquals("1.23万",result);
    }

    @Test
    public void testFormatFloatValueMoreThanMaxRound() {
        String result= DoubleUtil.formatValue(12351.245f);
        assertEquals("1.23万",result);
    }

    @Test
    public void testFormatFloatValueMoreThanMaxTail() {
        String result= DoubleUtil.formatValue(12000f);
        assertEquals("1.2万",result);
    }

    @Test
    public void testFormatFloatValueMoreThan100() {
        String result= DoubleUtil.formatValue(123.244f);
        assertEquals("123.24",result);
    }

    @Test
    public void testFormatFloatNaN() {
        String result= DoubleUtil.formatValue(Float.NaN);
        assertEquals("",result);
    }

}
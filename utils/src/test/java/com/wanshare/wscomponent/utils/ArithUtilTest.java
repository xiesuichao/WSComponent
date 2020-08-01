package com.wanshare.wscomponent.utils;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Jason on 2018/8/3.
 */

public class ArithUtilTest {

    @Test
    public void testAdd(){
        String add = ArithUtil.add("0.102", "0.003");
        assertEquals("0.105", add);
    }

    @Test
    public void testSub(){
        String sub = ArithUtil.sub("0.120000000002", "0.00000003");
        assertEquals("0.119999970002", sub);
    }

    @Test
    public void testMul(){
        String mul = ArithUtil.mul("0.120002", "0.00000003");
        assertEquals("0.00000000360006", mul);
    }

    @Test
    public void testMulScale(){
        String mul = ArithUtil.mul("0.120002", "0.00000003",10);
        assertEquals("0.0000000036", mul);
    }

    @Test
    public void testDiv(){
        String div = ArithUtil.div("0.120002", "0.00000003");
        assertEquals("4000066.6666666667", div);
    }

    @Test
    public void testDivScal(){
        String div = ArithUtil.div("0.120002", "0.00000003",8);
        assertEquals("4000066.66666667", div);
    }

    @Test
    public void testAround(){
        String div = ArithUtil.round("0.1200550562", 8);
        assertEquals("0.12005506", div);
    }
}

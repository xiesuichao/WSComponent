package com.wanshare.wscomponent.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jason on 2018/8/15.
 */
public class FileUtilTest {
    @Test
    public void getSDCardPath() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String sdCardPath = FileUtil.getSDCardPath();
        assertNotEquals("123",sdCardPath);
    }

    @Test
    public void getSDCardAppPath() throws Exception {
        String sdCardAppPath = FileUtil.getSDCardAppPath();
        assertNotEquals("123",sdCardAppPath);
    }

    @Test
    public void getLogPath() throws Exception {
        String logPath = FileUtil.getLogPath();
        assertNotEquals("123",logPath);
    }

    @Test
    public void isSDCardAvailable() throws Exception {
        boolean sdCardAvailable = FileUtil.isSDCardAvailable();
        assertTrue(sdCardAvailable);
    }

    @Test
    public void isFileExists() throws Exception {
        boolean fileExists = FileUtil.isFileExists(new File(""));
        assertFalse(fileExists);
    }

    @Test
    public void getFileName() throws Exception {
        List<String> fileName = FileUtil.getFileName("123");
        assertEquals(null,fileName);
    }

    @Test
    public void createSDDirectory() throws Exception {
        File sdDirectory = FileUtil.createSDDirectory("123");
        assertNotEquals("123",sdDirectory);
    }

    @Test
    public void readSDFile() throws Exception {
        String s = FileUtil.readSDFile("123");
        assertNotEquals("123",s);
    }

    @Test
    public void deleteFile() throws Exception {
        boolean b = FileUtil.deleteFile("123");
        assertEquals(false,b);
    }

    @Test
    public void deleteDirectory() throws Exception {
        boolean b = FileUtil.deleteDirectory("123");
        assertFalse(b);
    }

    @Test
    public void changeFile() throws Exception {
        File file = FileUtil.changeFile("123", "123");
        assertNotEquals(null,file);
    }
}
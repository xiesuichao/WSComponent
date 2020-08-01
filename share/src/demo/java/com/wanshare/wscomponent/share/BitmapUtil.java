/******************************************************
 * Copyrights @ 2016，Chanxa Technology Co., Ltd.
 * 全潮电商
 * All rights reserved.
 * <p/>
 * Filename：
 * BitmapUtil.java
 * Description：
 * 图片工具类
 * Author:
 * Venn
 * Finished：
 * 2016年03月14日
 ********************************************************/
package com.wanshare.wscomponent.share;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BitmapUtil {
    /***
     * 保存图片至SD卡
     *
     * @param bm
     * @param url
     * @param quantity
     */
    private static int FREE_SD_SPACE_NEEDED_TO_CACHE = 1;
    private static int MB = 1024 * 1024;
    public final static String DIR = "/sdcard/hypers";

    public static void saveBmpToSd(Bitmap bm, File file) {
        // 判断sdcard上的空间
        if (FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd()) {
            return;
        }
        if (!Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState()))
            return;
        OutputStream outStream = null;
        try {
            outStream = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算sdcard上的剩余空间 * @return
     */
    private static int freeSpaceOnSd() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
                .getPath());
        double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat
                .getBlockSize()) / MB;

        return (int) sdFreeMB;
    }




}


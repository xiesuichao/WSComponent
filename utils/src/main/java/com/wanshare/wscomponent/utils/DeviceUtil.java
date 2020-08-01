package com.wanshare.wscomponent.utils;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * 获取手机相关参数的工具类
 */
public class DeviceUtil {

    /**
     * 获取设备宽度（px）
     *
     * @param context
     * @return
     */
    public static int deviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取设备高度（px）
     *
     * @param context
     * @return
     */
    public static int deviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(@NonNull final Context context) {
        final Resources resources = context.getResources();
        int result = 0;
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取设备的唯一标识(IMEI)
     *
     * @param context
     * @return
     */
    @Deprecated
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }
        String deviceId = tm.getDeviceId();
        if (deviceId == null) {
            return "";
        } else {
            return deviceId;
        }
    }

    /**
     * 获取手机IMSI号
     * MCC+MNC+MIN MCC：Mobile Country Code，移动国家码，共3位，中国为460;
     * MNC:Mobile Network Code，移动网络码，共2位，电信03，移动02，联通GSM 01，
     * 一个典型的IMSI号码为460030912121001; MIN共有10位
     */
    @RequiresPermission(android.Manifest.permission.READ_PHONE_STATE)
    public static String getIMSI(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }
        String imsi = mTelephonyMgr.getSubscriberId();
        if (imsi == null) {
            return "";
        } else {
            return imsi;
        }
    }

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机Android API等级（22、23 ...）
     *
     * @return
     */
    public static int getBuildLevel() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机Android 版本（4.4、5.0、5.1 ...）
     *
     * @return
     */
    public static String getBuildVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取当前App进程的id
     *
     * @return
     */
    public static int getAppProcessId() {
        return android.os.Process.myPid();
    }

//    /**
//     * 获取当前App进程的Name
//     *
//     * @param context
//     * @param processId
//     * @return
//     */
//    public static String getAppProcessName(Context context, int processId) {
//        String processName = null;
//        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        // 获取所有运行App的进程集合
//        List l = am.getRunningAppProcesses();
//        Iterator i = l.iterator();
//        PackageManager pm = context.getPackageManager();
//        while (i.hasNext()) {
//            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
//            try {
//                if (info.pid == processId) {
//                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
//
//                    processName = info.processName;
//                    return processName;
//                }
//            } catch (Exception e) {
//                Log.e(DeviceUtil.class.getName(), e.getMessage(), e);
//            }
//        }
//        return processName;
//    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取当前app的version
     * @param context
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    public static String getAppVersion(Context context) throws PackageManager.NameNotFoundException {
        String versionName = context.getPackageManager()
                .getPackageInfo(context.getPackageName(), 0)
                .versionName;
        return versionName;
    }

    /**
     * 判断是否是后台进程
     *
     * @param context
     * @return
     */
    public static boolean isAppOnForGround(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
package com.wanshare.wscomponent.update.update;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import java.io.File;

public class UpdateHelper {

    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    public static String getPackageName(Context context) {
        String packName = "";
        PackageInfo packInfo = getPackInfo(context);
        if (packInfo != null) {
            packName = packInfo.packageName;
        }
        return packName;
    }

    /**
     * 获得apkinfo
     * @param context
     * @return
     */
    public static PackageInfo getPackInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo;
    }

    /**
     * 截取版本号
     * @param versionName 版本号
     * */
    public static String parseVersionName(String versionName) {
        if (TextUtils.isEmpty(versionName)) {
            return "100";
        }
        String name = versionName.replace("D", "");
        String[] vStr = name.split("\\.");
        String str0 = vStr[0];
        String str1 = vStr[1];
        String str2 = vStr[2];
        if (!TextUtils.isEmpty(str1)) {
            int v = Integer.parseInt(str1);
            if (v < 10) {
                str1 = vStr[1];
            }
        }

        if (!TextUtils.isEmpty(str2)) {
            int v = Integer.parseInt(str2);
            if (v < 10) {
                str2 = vStr[2];
            }
        }

        return str0 + str1 + str2;
    }


    /**
     * 检查版本，是否更新
     *
     * @param localVersionName 本地版本
     * @param versionName 目标版本好
     * */
    public static boolean checkVersion(String localVersionName, String versionName){
        try {
            int local = Integer.parseInt(parseVersionName(localVersionName));
            int remote = Integer.parseInt(parseVersionName(versionName));
            return remote > local;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 安装apk文件
     * @param context
     * @param file 安装文件
     */
    public static void installApkFile(Context context, File file) {
        if (!file.exists()) {
            return;
        }
        context.startActivity(getInstallIntent(context, file));

    }

    public static Intent getInstallIntent(Context context, File file){
        Intent install = new Intent();
        install.setAction(Intent.ACTION_VIEW);
        install.addCategory(Intent.CATEGORY_DEFAULT);
        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT>=24){
            Uri apkUri = FileProvider.getUriForFile(context, context.getPackageName()+".fileprovider", file);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            install.setType("application/vnd.android.package-archive");
            install.setData(Uri.fromFile(file));
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        return install;
    }

    public int checkUpdate(){

        return 0;
    }

}

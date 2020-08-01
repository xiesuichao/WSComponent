package com.wanshare.wscomponent.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;


public class ToastUtil {


    private ToastUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    private static Toast mToast;

    /**
     * 延迟秒数
     */
    private static final int DELAY_MILLIS = 5000;

    private static Handler mhandler = new Handler();

    private static Runnable r = new Runnable() {

        public void run() {
            mToast.cancel();
            if(mToast != null){
                mToast = null;
            }
        }
    };

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, String message) {
        showToast(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        showToast(context, message, Toast.LENGTH_SHORT);
    }


    public static void showToast(Context context, String text, int duration) {
        initToast(context, text, duration);
    }

    public static void showToast(Context context, int text, int duration) {
        initToast(context, context.getString(text), duration);
    }

    public static void initToast(Context context, String text, int duration) {
        //初始化，先remove之前的回调，再执行postDelayed任务
        mhandler.removeCallbacks(r);
        if (context == null) {
            return;
        }
        if (null != mToast) {
            mToast.setText(text);
        } else {
            mToast = Toast.makeText(context, text, duration);
        }

        mhandler.postDelayed(r, DELAY_MILLIS);
        mToast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, String message) {
        showToast(context, message, Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message getString(R.id.)
     *
     */
    public static void showLong(Context context, int message) {
        showToast(context, message, Toast.LENGTH_LONG);
    }

    /**
     * 自定义显示Toast时间
     *
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, String message, int duration) {
        showToast(context, message, duration);
    }
}

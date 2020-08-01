package com.wanshare.wscomponent.logger;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * 日志打印工具类
 *
 * @author Ltt
 */
public class LogUtil {
    /**
     * 注意：打包的时候记得设置isDebug为false
     */
    public static boolean isDebug = true;

    //使用之前这个要事先在全局里面初始化
    //Logger.init("LttAndroid").methodCount(2).hideThreadInfo();
    public static void init() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static void init(boolean isShowThreadInfo, int methodCount, String tag) {
        FormatStrategy formatStratery = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(isShowThreadInfo)
                .methodCount(methodCount)
                .tag(tag)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStratery));
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).e(msg + "");
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).d(msg + "");
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).i(msg + "");
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).v(msg + "");
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).w(msg + "");
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Logger.e(msg + "");
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Logger.d(msg + "");
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Logger.i(msg + "");
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Logger.v(msg + "");
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Logger.w(msg + "");
        }
    }

    public static void json(String json) {
        if (isDebug) {
            Logger.json(json);
        }
    }

    public static void xml(String xml) {
        if (isDebug) {
            Logger.xml(xml);
        }
    }

    public static void wrightToFile(String wtf) {
        if (isDebug) {
            Logger.wtf(wtf);
        }
    }

    public static void map(Object map) {
        if (isDebug) {
            Logger.d(map);
        }
    }

    public static void list(Object list) {
        if (isDebug) {
            Logger.d(list);
        }
    }

    public static void ex(Object ex) {
        if (isDebug) {
            Logger.d(ex);
        }
    }
}

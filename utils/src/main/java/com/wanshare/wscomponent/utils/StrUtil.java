package com.wanshare.wscomponent.utils;

/**
 * Created by Jason on 2018/9/14.
 *
 * 处理字符串的工具类
 */

public class StrUtil {

    /**
     * 获取字符串字符个数
     * @param value
     * @return
     */
    public static int getStringLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
			/* 获取一个字符 */
            String temp = value.substring(i, i + 1);
			/* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
                valueLength += 2;
            } else {
				/* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

}

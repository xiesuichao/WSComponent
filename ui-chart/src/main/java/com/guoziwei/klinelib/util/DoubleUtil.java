package com.guoziwei.klinelib.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by hou on 2015/8/14.
 */
public class DoubleUtil {

    private static final DecimalFormat DECIMAL_FORMAT_2_DIGITS = new DecimalFormat("###0.##");
    private static final DecimalFormat DECIMAL_FORMAT_2_DIGITS_PERCENT = new DecimalFormat("###0.##%");
    private static final DecimalFormat DECIMAL_FORMAT_2_DIGITS_ROUND_DOWN = new DecimalFormat("###0.##");
    private static final DecimalFormat DECIMAL_FORMAT_3_DIGITS_ROUND_DOWN = new DecimalFormat("###0.###");
    private static final DecimalFormat DECIMAL_FORMAT_4_DIGITS_ROUND_DOWN = new DecimalFormat("###0.####");
    private static final DecimalFormat DECIMAL_FORMAT_6_DIGITS_ROUND_DOWN = new DecimalFormat("###0.######");

    public static double parseDouble(String parserDouble) {
        try {
            return Double.parseDouble(parserDouble);
        } catch (Exception e) {
            return 0;
        }
    }

    public static String format2Digits(Double d) {
        return DECIMAL_FORMAT_2_DIGITS.format(d);
    }

    public static String format2Digits(float f) {
        return format2Digits((double) f);
    }

    /**
     * 返回值带正负号
     *
     * @param d
     * @return
     */
    public static String format2DigitsWithSign(Double d) {
        if (Double.isNaN(d)) {
            return "";
        }
        if (d > 0) {
            return new StringBuilder("+").append(DECIMAL_FORMAT_2_DIGITS.format(d)).toString();
        }
        return DECIMAL_FORMAT_2_DIGITS.format(d);
    }

    public static String format2DigitsPercent(Double d) {
        if (Double.isNaN(d)) {
            return "";
        }
        return DECIMAL_FORMAT_2_DIGITS_PERCENT.format(d);
    }

    public static String format2DigitsPercentWithSign(Double d) {
        if (Double.isNaN(d)) {
            return "";
        }
        if (d > 0) {
            return new StringBuilder("+").append(DECIMAL_FORMAT_2_DIGITS_PERCENT.format(d)).toString();
        }
        return DECIMAL_FORMAT_2_DIGITS_PERCENT.format(d);
    }

    public static String formatDecimal(Double d) {
        NumberFormat instance = DecimalFormat.getInstance();
        instance.setMinimumFractionDigits(0);
        instance.setMaximumFractionDigits(8);
        instance.setGroupingUsed(false);
        return instance.format(d);
    }


    /**
     * converting a double number to string by digits
     */
    public static String getStringByDigits(double num, int digits) {
       /* if (digits == 0) {
            return (int) num + "";
        } else {
            NumberFormat instance = DecimalFormat.getInstance();
            instance.setMinimumFractionDigits(digits);
            instance.setMaximumFractionDigits(digits);
            return instance.format(num).replace(",", "");
        }*/
        return String.format(Locale.getDefault(), "%." + digits + "f", num);
    }

    public static String formatValueWithSign(Double d) {
        if (Double.isNaN(d)) {
            return "";
        }
        if (d > 0) {
            return new StringBuilder("+").append(formatValue(d)).toString();
        }
        return formatValue(d);
    }

    /**
     * 有零自动舍掉,截取不做四舍五入
     * 整数位超万，则保留到万， 保留2位小数 示例：1.52万
     * 整数位小于10000大于100的，保留到整数位， 保留2位小数
     * 整数位小于100大于10的，保留到整数位， 保留3位小数
     * 整数位小于10大于1的，保留到整数位， 保留4位小数
     * 整数位小于1的，保留到整数位， 保留6位小数
     *
     * @param value
     * @return
     */
    public static String formatValue(double value) {

        if (value >= 10000f) {
            DECIMAL_FORMAT_2_DIGITS_ROUND_DOWN.setRoundingMode(RoundingMode.DOWN);
            return DECIMAL_FORMAT_2_DIGITS_ROUND_DOWN.format(new BigDecimal(value).divide(new BigDecimal("10000")))+"万";
        } else if (value >= 100) {
            DECIMAL_FORMAT_2_DIGITS_ROUND_DOWN.setRoundingMode(RoundingMode.DOWN);
            return DECIMAL_FORMAT_2_DIGITS_ROUND_DOWN.format(new BigDecimal(value+""));
        } else if (value >= 10) {
            DECIMAL_FORMAT_3_DIGITS_ROUND_DOWN.setRoundingMode(RoundingMode.DOWN);
            return DECIMAL_FORMAT_3_DIGITS_ROUND_DOWN.format(new BigDecimal(value+""));
        } else if (value >= 1) {
            DECIMAL_FORMAT_4_DIGITS_ROUND_DOWN.setRoundingMode(RoundingMode.DOWN);
            return DECIMAL_FORMAT_4_DIGITS_ROUND_DOWN.format(new BigDecimal(value+""));
        }else if(Double.isNaN(value)){
            return "";
        } else {
            DECIMAL_FORMAT_6_DIGITS_ROUND_DOWN.setRoundingMode(RoundingMode.DOWN);
            return DECIMAL_FORMAT_6_DIGITS_ROUND_DOWN.format(new BigDecimal(value+"") );
        }
    }

    public static String formatValue(float value) {

        if (value >= 10000f) {
            DECIMAL_FORMAT_2_DIGITS_ROUND_DOWN.setRoundingMode(RoundingMode.DOWN);
            return DECIMAL_FORMAT_2_DIGITS_ROUND_DOWN.format(new BigDecimal(value).divide(new BigDecimal("10000")))+"万";
        } else if (value >= 100) {
            DECIMAL_FORMAT_2_DIGITS_ROUND_DOWN.setRoundingMode(RoundingMode.DOWN);
            return DECIMAL_FORMAT_2_DIGITS_ROUND_DOWN.format(new BigDecimal(value+""));
        } else if (value >= 10) {
            DECIMAL_FORMAT_3_DIGITS_ROUND_DOWN.setRoundingMode(RoundingMode.DOWN);
            return DECIMAL_FORMAT_3_DIGITS_ROUND_DOWN.format(new BigDecimal(value+""));
        } else if (value >= 1) {
            DECIMAL_FORMAT_4_DIGITS_ROUND_DOWN.setRoundingMode(RoundingMode.DOWN);
            return DECIMAL_FORMAT_4_DIGITS_ROUND_DOWN.format(new BigDecimal(value+""));
        }else if(Float.isNaN(value)){
            return "";
        } else {
            DECIMAL_FORMAT_6_DIGITS_ROUND_DOWN.setRoundingMode(RoundingMode.DOWN);
            return DECIMAL_FORMAT_6_DIGITS_ROUND_DOWN.format(new BigDecimal(value+"") );
        }
    }

    public static void main(String[] args) {
        System.out.println("" + DoubleUtil.format2Digits(0d));
//        testNumberFormat();
//        testDecimalFormat();
//        testStringFormat();
    }


}

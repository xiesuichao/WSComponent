package com.guoziwei.klinelib.chart;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.wanshare.wscomponent.chart.kline.model.bean.HisData;
import com.guoziwei.klinelib.util.DoubleUtil;
import com.wanshare.wscomponent.chart.R;
import com.wanshare.wscomponent.utils.DateUtil;

/**
 * Created by dell on 2017/9/25.
 */

public class KLineChartInfoView extends ChartInfoView {
    protected String mDateFormat = "yyyy-MM-dd HH:mm";
    private TextView mTvOpenPrice;
    private TextView mTvClosePrice;
    private TextView mTvHighPrice;
    private TextView mTvLowPrice;
    // 涨跌额
    private TextView mTvChangeAmount;
    // 涨跌幅
    private TextView mTvChangeRate;
    private TextView mTvVol;
    private TextView mTvTime;
    private View mLLChangeRate;

    private String mVolUnit="";

    public KLineChartInfoView(Context context) {
        this(context, null);
    }

    public KLineChartInfoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KLineChartInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_kline_chart_info, this);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mTvOpenPrice = (TextView) findViewById(R.id.tv_open_price);
        mTvClosePrice = (TextView) findViewById(R.id.tv_close_price);
        mTvHighPrice = (TextView) findViewById(R.id.tv_high_price);
        mTvLowPrice = (TextView) findViewById(R.id.tv_low_price);
        mTvChangeAmount = findViewById(R.id.tv_change_amount);
        mTvChangeRate = (TextView) findViewById(R.id.tv_change_rate);
        mTvVol = (TextView) findViewById(R.id.tv_vol);
        mLLChangeRate = findViewById(R.id.ll_change_rate);
    }

    @Override
    public void setData(double lastClose, HisData data) {
        mTvTime.setText(DateUtil.formatDate(data.getDate(),mDateFormat));
        mTvClosePrice.setText(DoubleUtil.formatValue(data.getClose()));
        mTvOpenPrice.setText(DoubleUtil.formatValue(data.getOpen()));
        mTvHighPrice.setText(DoubleUtil.formatValue(data.getHigh()));
        mTvLowPrice.setText(DoubleUtil.formatValue(data.getLow()));

        mTvChangeAmount.setText(DoubleUtil.formatValueWithSign(data.getChangeAmount()));
        mTvChangeAmount.setTextColor(getChangeTextColor(data.getChangeAmount()));
        mTvChangeRate.setText(DoubleUtil.format2DigitsPercentWithSign(data.getChangeRate()));
        mTvChangeRate.setTextColor(getChangeTextColor(data.getChangeRate()));
        mTvVol.setText(DoubleUtil.formatValue(data.getVol()) + " "+(mVolUnit==null?"":mVolUnit));
//        removeCallbacks(mRunnable);
//        postDelayed(mRunnable, 2000);
    }

    private int getChangeTextColor(double value) {
        if (value >= 0) {
            return ContextCompat.getColor(getContext(), R.color.chart_increasing_color);
        } else {
            return ContextCompat.getColor(getContext(), R.color.chart_decreasing_color);
        }
    }

    public void setDateFormat(String mDateFormat) {
        this.mDateFormat = mDateFormat;
    }
    public void setVolUnit(String volUnit){
        this.mVolUnit = volUnit;
    }
}

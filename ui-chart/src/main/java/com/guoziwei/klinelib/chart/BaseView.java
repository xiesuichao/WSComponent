package com.guoziwei.klinelib.chart;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Transformer;

import com.wanshare.wscomponent.chart.kline.model.bean.HisData;
import com.guoziwei.klinelib.util.DoubleUtil;
import com.wanshare.wscomponent.chart.R;
import com.wanshare.wscomponent.utils.DateUtil;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/14 0014.
 */

public class BaseView extends LinearLayout {

    protected String mDateFormat = "yyyy-MM-dd HH:mm";

    protected int mDecreasingColor;
    protected int mIncreasingColor;
    protected int mAxisColor;
    protected int mTransparentColor;
    protected int mMainTextColor;
    protected int mGridLineColor;
    protected int mHighLightColor;

    public int MAX_COUNT = 140;
    public int MIN_COUNT = 18;
    public int INIT_COUNT = 34;
    // 右边Y轴字体大小
    public static final int AXIS_RIGHT_TEXT_SIZE = 8;
    protected final List<HisData> mData = new ArrayList<>(300);

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mAxisColor = ContextCompat.getColor(getContext(), R.color.chart_axis_color);
        mTransparentColor = ContextCompat.getColor(getContext(), android.R.color.transparent);
        mDecreasingColor = ContextCompat.getColor(getContext(), R.color.chart_decreasing_color);
        mIncreasingColor = ContextCompat.getColor(getContext(), R.color.chart_increasing_color);
        mMainTextColor = ContextCompat.getColor(getContext(),R.color.chart_main_text_color);
        mGridLineColor = ContextCompat.getColor(getContext(),R.color.chart_grid_color);
        mHighLightColor = ContextCompat.getColor(getContext(),R.color.chart_highlight_color);
    }

    protected void initBottomChart(CustomCombinedChart chart) {
        chart.setScaleEnabled(true);
        chart.setDrawBorders(false);
        chart.setBorderWidth(1);
        chart.setDragEnabled(true);
        chart.setScaleYEnabled(false);
        chart.setAutoScaleMinMaxEnabled(true);
        chart.setDragDecelerationEnabled(false);
        chart.setHighlightPerDragEnabled(false);
        Legend lineChartLegend = chart.getLegend();
        lineChartLegend.setEnabled(false);

        chart.getDescription().setTextColor(chart.getContext().getResources().getColor(R.color.chart_description_color));
        chart.getDescription().setEnabled(false);

        XAxis xAxisVolume = chart.getXAxis();
        xAxisVolume.setDrawLabels(true);
//        xAxisVolume.setDrawAxisLine(false);
//        xAxisVolume.setDrawGridLines(false);
        xAxisVolume.setTextColor(mAxisColor);
        xAxisVolume.setTextSize(AXIS_RIGHT_TEXT_SIZE);
        xAxisVolume.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisVolume.setLabelCount(5, true);
        xAxisVolume.setAvoidFirstLastClipping(true);
        xAxisVolume.setAxisMinimum(-0.5f);
        xAxisVolume.setDrawGridLines(true);
        xAxisVolume.setGridLineWidth(1);
        xAxisVolume.setGridColor(mGridLineColor);
        xAxisVolume.setDrawAxisLine(true);
        xAxisVolume.setAxisLineColor(mGridLineColor);
        xAxisVolume.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (mData.isEmpty()) {
                    return "";
                }
                if (value < 0) {
                    value = 0;
                }
                if (value < mData.size()) {
                    return  DateUtil.formatDate(mData.get((int) value).getDate(), mDateFormat);
                }
                return "";
            }
        });

        YAxis axisLeftVolume = chart.getAxisLeft();
        axisLeftVolume.setDrawLabels(false);
        axisLeftVolume.setDrawGridLines(false);
//        axisLeftVolume.setLabelCount(2, true);
//        axisLeftVolume.setDrawAxisLine(false);
//        axisLeftVolume.setTextColor(mAxisColor);
//        axisLeftVolume.setSpaceTop(10);
//        axisLeftVolume.setSpaceBottom(0);
//        axisLeftVolume.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        axisLeftVolume.setEnabled(true);
        axisLeftVolume.setDrawAxisLine(true);
        axisLeftVolume.setAxisLineColor(mGridLineColor);
//        Transformer leftYTransformer = chart.getRendererLeftYAxis().getTransformer();
//        ColorContentYAxisRenderer leftColorContentYAxisRenderer = new ColorContentYAxisRenderer(chart.getViewPortHandler(), chart.getAxisLeft(), leftYTransformer);
//        leftColorContentYAxisRenderer.setLabelInContent(true);
//        leftColorContentYAxisRenderer.setUseDefaultLabelXOffset(false);
//        chart.setRendererLeftYAxis(leftColorContentYAxisRenderer);

        //右边y
        YAxis axisRightVolume = chart.getAxisRight();
//        axisRightVolume.setDrawLabels(true);
//        axisRightVolume.setDrawGridLines(false);
        axisRightVolume.setLabelCount(3, true);
        axisRightVolume.setDrawAxisLine(false);
        axisRightVolume.setTextColor(mAxisColor);
        axisRightVolume.setTextSize(AXIS_RIGHT_TEXT_SIZE);
        axisRightVolume.setSpaceTop(35);
        axisRightVolume.setSpaceBottom(0);
        axisRightVolume.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        axisRightVolume.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return DoubleUtil.formatValue(value);
            }
        });
        axisRightVolume.setDrawGridLines(true);
        axisRightVolume.setGridLineWidth(1);
        axisRightVolume.setGridColor(mGridLineColor);
        axisRightVolume.setDrawAxisLine(true);
        axisRightVolume.setAxisLineColor(mGridLineColor);

        int[] colorArray = {mAxisColor, mAxisColor, mAxisColor, mAxisColor, mAxisColor};

        Transformer rightYTransformer = chart.getRendererRightYAxis().getTransformer();
        ColorContentYAxisRenderer rightColorContentYAxisRenderer = new ColorContentYAxisRenderer(chart.getViewPortHandler(), chart.getAxisRight(), rightYTransformer);
        rightColorContentYAxisRenderer.setLabelInContent(true);
        rightColorContentYAxisRenderer.setLabelColor(colorArray);
        rightColorContentYAxisRenderer.setUseDefaultLabelXOffset(false);
        chart.setRendererRightYAxis(rightColorContentYAxisRenderer);
    }

    protected void initMiddleChart(CustomCombinedChart chart) {
        chart.setScaleEnabled(true);
        chart.setDrawBorders(false);
        chart.setBorderWidth(1);
        chart.setDragEnabled(true);
        chart.setScaleYEnabled(false);
        chart.setAutoScaleMinMaxEnabled(true);
        chart.setDragDecelerationEnabled(false);
        chart.setHighlightPerDragEnabled(false);
        Legend lineChartLegend = chart.getLegend();
        lineChartLegend.setEnabled(false);

//        chart.setExtraTopOffset(100);
        chart.getDescription().setEnabled(false);
//        chart.getDescription().setTextColor(chart.getContext().getResources().getColor(R.color.description_color));


        XAxis xAxisVolume = chart.getXAxis();
        xAxisVolume.setDrawLabels(false);
//        xAxisVolume.setDrawAxisLine(false);
//        xAxisVolume.setDrawGridLines(false);
        xAxisVolume.setTextColor(mAxisColor);
        xAxisVolume.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisVolume.setLabelCount(5, true);
        xAxisVolume.setAvoidFirstLastClipping(true);
        xAxisVolume.setAxisMinimum(-0.5f);
        xAxisVolume.setDrawGridLines(true);
        xAxisVolume.setGridLineWidth(1);
        xAxisVolume.setGridColor(mGridLineColor);
        xAxisVolume.setDrawAxisLine(true);
        xAxisVolume.setAxisLineColor(mGridLineColor);
        xAxisVolume.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (mData.isEmpty()) {
                    return "";
                }
                if (value < 0) {
                    value = 0;
                }
                if (value < mData.size()) {
                    return DateUtil.formatDate(mData.get((int) value).getDate(), mDateFormat);
                }
                return "";
            }
        });

        xAxisVolume.setEnabled(true);

        YAxis axisLeftVolume = chart.getAxisLeft();
        axisLeftVolume.setDrawLabels(false);
        axisLeftVolume.setDrawGridLines(false);
//        axisLeftVolume.setLabelCount(2, true);
//        axisLeftVolume.setDrawAxisLine(false);
//        axisLeftVolume.setTextColor(mAxisColor);
//        axisLeftVolume.setSpaceTop(10);
//        axisLeftVolume.setSpaceBottom(0);
//        axisLeftVolume.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        axisLeftVolume.setEnabled(true);
        axisLeftVolume.setDrawAxisLine(true);
        axisLeftVolume.setAxisLineColor(mGridLineColor);
//        Transformer leftYTransformer = chart.getRendererLeftYAxis().getTransformer();
//        ColorContentYAxisRenderer leftColorContentYAxisRenderer = new ColorContentYAxisRenderer(chart.getViewPortHandler(), chart.getAxisLeft(), leftYTransformer);
//        leftColorContentYAxisRenderer.setLabelInContent(true);
//        leftColorContentYAxisRenderer.setUseDefaultLabelXOffset(false);
//        chart.setRendererLeftYAxis(leftColorContentYAxisRenderer);

        //右边y
        YAxis axisRightVolume = chart.getAxisRight();
//        axisRightVolume.setDrawLabels(true);
//        axisRightVolume.setDrawGridLines(false);
        axisRightVolume.setLabelCount(3, true);
        axisRightVolume.setDrawAxisLine(false);
        axisRightVolume.setTextColor(mAxisColor);
        axisRightVolume.setTextSize(AXIS_RIGHT_TEXT_SIZE);
        axisRightVolume.setSpaceTop(35);
        axisRightVolume.setSpaceBottom(0);
        axisLeftVolume.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        axisRightVolume.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return DoubleUtil.formatValue(value);
            }
        });
        axisRightVolume.setDrawGridLines(true);
        axisRightVolume.setGridLineWidth(1);
        axisRightVolume.setGridColor(mGridLineColor);
        axisRightVolume.setDrawAxisLine(true);
        axisRightVolume.setAxisLineColor(mGridLineColor);

        int[] colorArray = {mAxisColor, mAxisColor, mAxisColor, mAxisColor, mAxisColor};

        Transformer rightYTransformer = chart.getRendererRightYAxis().getTransformer();
        ColorContentYAxisRenderer rightColorContentYAxisRenderer = new ColorContentYAxisRenderer(chart.getViewPortHandler(), chart.getAxisRight(), rightYTransformer);
        rightColorContentYAxisRenderer.setLabelInContent(true);
        rightColorContentYAxisRenderer.setLabelColor(colorArray);
        rightColorContentYAxisRenderer.setUseDefaultLabelXOffset(false);
        chart.setRendererRightYAxis(rightColorContentYAxisRenderer);
    }


    protected void moveToLast(CustomCombinedChart chart) {
        if (mData.size() > INIT_COUNT) {
            chart.moveViewToX(mData.size() - INIT_COUNT);
        }
    }

    /**
     * set the count of k chart
     */
    public void setCount(int init, int max, int min) {
        INIT_COUNT = init;
        MAX_COUNT = max;
        MIN_COUNT = min;
    }

    protected void setDescription(Chart chart, String text) {
        Description description = chart.getDescription();
        description.setText(text);
    }

    public HisData getLastData() {
        if (mData != null && !mData.isEmpty()) {
            return mData.get(mData.size() - 1);
        }
        return null;
    }

    public void setDateFormat(String mDateFormat) {
        this.mDateFormat = mDateFormat;
    }


}

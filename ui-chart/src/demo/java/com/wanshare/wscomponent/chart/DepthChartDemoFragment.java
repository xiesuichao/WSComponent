package com.wanshare.wscomponent.chart;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.guoziwei.klinelib.chart.CustomCombinedChart;

import java.util.ArrayList;
import java.util.List;

/**
 * $todo类的描述$
 * </br>
 * Date: 2018/8/16 14:16
 *
 * @author hemin
 */
public class DepthChartDemoFragment extends Fragment {
    // 右边Y轴字体大小
    public static final int AXIS_RIGHT_TEXT_SIZE = 8;

    protected int mAxisColor;
    CustomCombinedChart mChartDepth;

    public static DepthChartDemoFragment newInstance() {

        Bundle args = new Bundle();

        DepthChartDemoFragment fragment = new DepthChartDemoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_depth_chart_demo, container, false);
        mChartDepth = view.findViewById(R.id.chart_depth);
        mAxisColor = ContextCompat.getColor(getContext(), R.color.chart_axis_color);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        ArrayList<Entry> valuesLeft = new ArrayList<>();
        int count = 45;
        int range = 100;
        for(int i=0;i<count/2;i++){
            float val = (float)(Math.random()*range)+3;
            valuesLeft.add(new Entry(i,val));
        }

        ArrayList<Entry> valuesRight = new ArrayList<>();
        for(int i=count/2;i<count;i++){
            float val = (float)(Math.random()*range)+3;
            valuesRight.add(new Entry(i,val));
        }

        LineDataSet lineDataSetLeft=new LineDataSet(valuesLeft,"buy data set");
        setLineDataSet(lineDataSetLeft);
        lineDataSetLeft.setColor(0xff00b23e);
        lineDataSetLeft.setFillColor(Color.GREEN);

        LineDataSet lineDataSetRight=new LineDataSet(valuesRight,"sell data set");
        setLineDataSet(lineDataSetRight);
        lineDataSetRight.setColor(0xffFF424A);
        lineDataSetRight.setFillColor(Color.RED);

        List<Entry> entryList = new ArrayList<>();
        entryList.add(new Entry(20,100));
        LineDataSet pointDataSet=new LineDataSet(entryList,"point data set");
        setLineDataSet(pointDataSet);
        pointDataSet.setColor(Color.BLACK);
        pointDataSet.setFillColor(Color.BLACK);
        pointDataSet.setDrawValues(true);
        pointDataSet.setDrawCircles(true);
        pointDataSet.setCircleColor(Color.BLACK);

        LineData lineData = new LineData(lineDataSetLeft,lineDataSetRight,pointDataSet);
        CombinedData combinedData = new CombinedData();
        combinedData.setData(lineData);
        mChartDepth.setData(combinedData);
    }

    private void setLineDataSet(LineDataSet lineDataSet) {
        lineDataSet.setDrawIcons(false);
        lineDataSet.setDrawValues(false);
//        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.TRANSPARENT);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(0f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setValueTextSize(9f);
        lineDataSet.setDrawFilled(true);

        lineDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);
    }

    private void initView() {
        mChartDepth.setScaleEnabled(false);
        mChartDepth.setDrawBorders(false);
        mChartDepth.setDragEnabled(true);
        mChartDepth.setAutoScaleMinMaxEnabled(true);
        mChartDepth.setHighlightPerDragEnabled(false);
        mChartDepth.setScaleYEnabled(false);
        Legend lineChartLegend = mChartDepth.getLegend();
        lineChartLegend.setEnabled(false);
        mChartDepth.getDescription().setEnabled(false);

        XAxis xAxis = mChartDepth.getXAxis();
        xAxis.setDrawLabels(true);
        xAxis.setLabelCount(3);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        YAxis axisLeft= mChartDepth.getAxisLeft();
        axisLeft.setEnabled(false);

        YAxis axisRight= mChartDepth.getAxisRight();
        axisRight.setLabelCount(5);
        axisRight.setDrawLabels(true);
        axisRight.setTextColor(mAxisColor);
        axisRight.setTextSize(AXIS_RIGHT_TEXT_SIZE);
        axisRight.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        axisRight.setDrawAxisLine(false);
        axisRight.setDrawGridLines(false);
//        axisRight.setAxisMinimum(0);

//        mChartDepth.setVisibleXRange(100, 50);

//        mChartDepth.setVisibleXRangeMinimum(0);
//        mChartDepth.setVisibleXRangeMaximum(100);
//        mChartDepth.moveViewToX(50);
    }
}

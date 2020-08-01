package com.wanshare.wscomponent.chart.kline;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.guoziwei.klinelib.chart.BaseView;
import com.guoziwei.klinelib.chart.ChartInfoViewHandler;
import com.guoziwei.klinelib.chart.ColorContentYAxisRenderer;
import com.guoziwei.klinelib.chart.CoupleChartGestureListener;
import com.guoziwei.klinelib.chart.CustomCombinedChart;
import com.guoziwei.klinelib.chart.InfoViewListener;
import com.guoziwei.klinelib.chart.KLineChartInfoView;
import com.guoziwei.klinelib.chart.OnLoadMoreListener;
import com.wanshare.wscomponent.chart.R;
import com.wanshare.wscomponent.chart.kline.model.bean.HisData;
import com.guoziwei.klinelib.util.DisplayUtils;
import com.guoziwei.klinelib.util.DoubleUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Kline封装
 * </br>
 * Date: 2018/7/28 17:07
 *
 * @author hemin
 */
public class WSKlineView extends BaseView implements CoupleChartGestureListener.OnAxisChangeListener {


    public static final int NORMAL_LINE = 0;
    /**
     * average line
     */
    public static final int AVE_LINE = 1;
    /**
     * hide line
     */
    public static final int INVISIABLE_LINE = 6;


    public static final int MA5 = 5;
    public static final int MA10 = 10;
    public static final int MA20 = 20;
    public static final int MA30 = 30;

    public static final int K = 31;
    public static final int D = 32;
    public static final int J = 33;

    public static final int DIF = 34;
    public static final int DEA = 35;

    public static final int BOLL_UP = 36;
    public static final int BOLL_MB = 37;
    public static final int BOLL_DN = 38;

    public static final int VOL_MA5 = 39;
    public static final int VOL_MA10 = 40;

    public static final int KDJ_PADDING = 41;
    public static final float HIGHLIGHT_LINE_WIDTH = 0.5f;

    protected CustomCombinedChart mChartPrice;
    protected CustomCombinedChart mChartVolume;
    protected CustomCombinedChart mChartMacd;
    protected CustomCombinedChart mChartKdj;

    protected KLineChartInfoView mChartInfoView;
    protected FrameLayout mFlMiddlePart;
    protected Context mContext;

    protected TextView mTvPriceInfoMa5;
    protected TextView mTvPriceInfoMa10;
    protected TextView mTvPriceInfoMa30;

    protected TextView mTvVol;
    protected TextView mTvVolMa5;
    protected TextView mTvVolMa10;

    protected TextView mTvBottomValue1;
    protected TextView mTvBottomValue2;
    protected TextView mTvBottomValue3;
    protected TextView mTvBottomValue4;

    /**
     * last price
     */
    private double mLastPrice;

    /**
     * yesterday close price
     */
    private double mLastClose;

    /**
     * the digits of the symbol
     */
    private int mDigits = 2;
    private CoupleChartGestureListener mCoupleChartGestureListener;

    private LineDataSet lineDataSetMA5;
    private LineDataSet lineDataSetMA10;
    //    private LineDataSet lineDataSetMA20;
    private LineDataSet lineDataSetMA30;

    private LineDataSet lineDataSetEMA5;
    private LineDataSet lineDataSetEMA10;
    private LineDataSet lineDataSetEMA30;

    private LineDataSet lineDataSetBollUP;
    private LineDataSet lineDataSetBollMB;
    private LineDataSet lineDataSetBollDN;

    private LineDataSet lineDataSetVolMA5;

    // 当前选择的点
    private HisData mCurrentHisData;
    private LineDataSet lineDataSetVolMA10;
    private BarDataSet barDataSetMacd;
    private BarDataSet barDataSetVol;
    private CandleDataSet candleDataSetKLine;
    private LineDataSet lineDataSetDif;
    private LineDataSet lineDataSetDea;

    private LineDataSet lineDataSetKDJPadding;
    private LineDataSet lineDataSetK;
    private LineDataSet lineDataSetD;
    private LineDataSet lineDataSetJ;


    public WSKlineView(Context context) {
        this(context, null);
    }

    public WSKlineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WSKlineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        initView(context);

        initChartPrice();
        initMiddleChart(mChartVolume);
        initBottomChart(mChartMacd);
        initBottomChart(mChartKdj);
        updateOffset();
        initChartListener();
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.chart_layout_kline, this);
        mChartPrice = findViewById(R.id.price_chart);
        mChartVolume = findViewById(R.id.vol_chart);
        mChartMacd = findViewById(R.id.macd_chart);
        mChartKdj = findViewById(R.id.kdj_chart);
        mChartInfoView = findViewById(R.id.k_info);
        mChartInfoView.setChart(mChartPrice, mChartVolume, mChartMacd, mChartKdj);
        mFlMiddlePart = findViewById(R.id.fl_middle_part);
        mTvPriceInfoMa5 = findViewById(R.id.tv_price_info_ma5);
        mTvPriceInfoMa10 = findViewById(R.id.tv_price_info_ma10);
        mTvPriceInfoMa30 = findViewById(R.id.tv_price_info_ma30);

        mTvVol = findViewById(R.id.tv_vol);
        mTvVolMa5 = findViewById(R.id.tv_vol_ma5);
        mTvVolMa10 = findViewById(R.id.tv_vol_ma10);

        mTvBottomValue1 = findViewById(R.id.tv_bottom_value1);
        mTvBottomValue2 = findViewById(R.id.tv_bottom_value2);
        mTvBottomValue3 = findViewById(R.id.tv_bottom_value3);
        mTvBottomValue4 = findViewById(R.id.tv_bottom_value4);
    }

    public void showKdj() {
        changeMiddlePartBottomMargin(mContext.getResources().getDimensionPixelOffset(R.dimen.chart_bottom_part_height));
        updateOffset();
        mChartVolume.setVisibility(VISIBLE);
        mChartMacd.setVisibility(GONE);
        mChartKdj.setVisibility(VISIBLE);
        setBottomDescription(mCurrentHisData);
    }

    public void showMacd() {
        changeMiddlePartBottomMargin(mContext.getResources().getDimensionPixelOffset(R.dimen.chart_bottom_part_height));
        updateOffset();

        mChartVolume.setVisibility(VISIBLE);
        mChartMacd.setVisibility(VISIBLE);
        mChartKdj.setVisibility(GONE);

        setBottomDescription(mCurrentHisData);
    }

    public void hideChartBottomPart() {
        changeMiddlePartBottomMargin(DisplayUtils.dip2px(mContext, 20));
        mChartVolume.notifyDataSetChanged();
        mChartVolume.invalidate();
        updateOffset();
    }

    private void changeMiddlePartBottomMargin(int bottomMargin) {
        MarginLayoutParams lp = (MarginLayoutParams) mFlMiddlePart.getLayoutParams();
        if (lp == null) {
            lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mContext.getResources().getDimensionPixelOffset(R.dimen.chart_middle_part_height));
        }
        lp.bottomMargin = bottomMargin;
        mFlMiddlePart.setLayoutParams(lp);
    }

    protected void initChartPrice() {
        mChartPrice.setNoDataText(mContext.getString(R.string.chart_loading));

        mChartPrice.setScaleEnabled(true);
        mChartPrice.setDrawBorders(false);
        mChartPrice.setBorderWidth(1);
        mChartPrice.setDragEnabled(true);
        mChartPrice.setScaleYEnabled(false);
        mChartPrice.setAutoScaleMinMaxEnabled(true);
        mChartPrice.setDragDecelerationEnabled(true);
        mChartPrice.setHighlightPerDragEnabled(false);
//        LineChartXMarkerView mvx = new LineChartXMarkerView(mContext, mData);
//        mvx.setChartView(mChartPrice);
//        mChartPrice.setXMarker(mvx);
        Legend lineChartLegend = mChartPrice.getLegend();
        lineChartLegend.setEnabled(false);

//        mChartPrice.getDescription().setTextColor(mContext.getResources().getColor(R.color.description_color));
//        mChartPrice.getDescription().setTextSize(ContextCompat.getColor(mContext,R.color.highlight_color));
        mChartPrice.getDescription().setEnabled(false);

        XAxis xAxisPrice = mChartPrice.getXAxis();
        xAxisPrice.setDrawLabels(false);
//        xAxisPrice.setDrawAxisLine(false);
//        xAxisPrice.setDrawGridLines(false);
        xAxisPrice.setLabelCount(5, true);
//        xAxisPrice.setAvoidFirstLastClipping(true);
        xAxisPrice.setAxisMinimum(-0.5f);
        xAxisPrice.setDrawGridLines(true);
        xAxisPrice.setGridLineWidth(1);
        xAxisPrice.setGridColor(mGridLineColor);
        xAxisPrice.setDrawAxisLine(true);
        xAxisPrice.setAxisLineColor(mGridLineColor);
        int[] colorArray = {mAxisColor, mAxisColor, mAxisColor, mAxisColor, mAxisColor};

        YAxis axisLeftPrice = mChartPrice.getAxisLeft();
//        axisLeftPrice.setLabelCount(5, true);
        axisLeftPrice.setDrawLabels(false);
//        axisLeftPrice.setDrawGridLines(false);
//        axisLeftPrice.setDrawAxisLine(false);
//        axisLeftPrice.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        axisLeftPrice.setTextColor(mAxisColor);
//        axisLeftPrice.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return DoubleUtil.getStringByDigits(value, mDigits);
//            }
//        });
        axisLeftPrice.setEnabled(true);
//        Transformer leftYTransformer = mChartPrice.getRendererLeftYAxis().getTransformer();
//        ColorContentYAxisRenderer leftColorContentYAxisRenderer = new ColorContentYAxisRenderer(mChartPrice.getViewPortHandler(), mChartPrice.getAxisLeft(), leftYTransformer);
//        leftColorContentYAxisRenderer.setLabelColor(colorArray);
//        leftColorContentYAxisRenderer.setLabelInContent(false);
//        leftColorContentYAxisRenderer.setUseDefaultLabelXOffset(false);
//        mChartPrice.setRendererLeftYAxis(leftColorContentYAxisRenderer);
        axisLeftPrice.setDrawGridLines(false);
//        axisLeftPrice.setGridLineWidth(1);
//        axisLeftPrice.setGridColor(mGridLineColor);
        axisLeftPrice.setDrawAxisLine(true);
        axisLeftPrice.setAxisLineColor(mGridLineColor);

        YAxis axisRightPrice = mChartPrice.getAxisRight();
        axisRightPrice.setLabelCount(5, true);
        axisRightPrice.setDrawLabels(true);
//        axisRightPrice.setDrawGridLines(false);
//        axisRightPrice.setDrawAxisLine(false);
        axisRightPrice.setTextColor(mAxisColor);
        axisRightPrice.setTextSize(AXIS_RIGHT_TEXT_SIZE);
        axisRightPrice.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        axisRightPrice.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return DoubleUtil.formatValue(value);
            }
        });

        axisRightPrice.setEnabled(true);
        axisRightPrice.setYOffset(2);
        axisRightPrice.setDrawGridLines(true);
        axisRightPrice.setGridLineWidth(1);
        axisRightPrice.setGridColor(mGridLineColor);
        axisRightPrice.setDrawAxisLine(true);
        axisRightPrice.setAxisLineColor(mGridLineColor);
        axisRightPrice.setSpaceBottom(0);

//        设置标签Y渲染器
        Transformer rightYTransformer = mChartPrice.getRendererRightYAxis().getTransformer();
        ColorContentYAxisRenderer rightColorContentYAxisRenderer = new ColorContentYAxisRenderer(mChartPrice.getViewPortHandler(), mChartPrice.getAxisRight(), rightYTransformer);
        rightColorContentYAxisRenderer.setLabelInContent(true);
        rightColorContentYAxisRenderer.setUseDefaultLabelXOffset(false);
        rightColorContentYAxisRenderer.setLabelColor(colorArray);
        mChartPrice.setRendererRightYAxis(rightColorContentYAxisRenderer);

    }

    private void initChartListener() {
        mCoupleChartGestureListener = new CoupleChartGestureListener(this, mChartPrice, mChartVolume, mChartMacd, mChartKdj);
        mChartPrice.setOnChartGestureListener(mCoupleChartGestureListener);
        mChartPrice.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, this, mChartVolume, mChartMacd, mChartKdj));
        mChartPrice.setOnTouchListener(new ChartInfoViewHandler(mChartPrice));
    }

    public void initData(List<HisData> hisDatas) {
        boolean hasInit = candleDataSetKLine != null;
        addAll(hisDatas);
        updateLabelCount();
        mChartPrice.setRealCount(mData.size());
        if (hasInit) {
            clearTopDataSets();
            clearMiddleDataSets();
            clearBottomDataSets();
        } else {
            initTopDataSets();
            initMiddleDataSets();
            initBottomDataSets();
        }

        addEntrys();
        if (!hasInit) {
            setTopData();
            setMiddleData();
            setBottomData();
        }

        setVisibleXRange();
        notifyAndSetZoomTop();
        notifyAndSetZoomMiddle();
        nofityAndSetZoomBottom();

        if (!hasInit) {
            mChartPrice.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);
            mChartVolume.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);
            mChartMacd.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);
            mChartKdj.zoom(MAX_COUNT * 1f / INIT_COUNT, 0, 0, 0);
        }

        HisData hisData = getLastData();
        setChartDescription(hisData);
    }

    private void updateLabelCount() {
        if (mData != null && mData.size() <= 10) {
            mChartPrice.getXAxis().setLabelCount(1, true);
            mChartVolume.getXAxis().setLabelCount(1, true);
            mChartMacd.getXAxis().setLabelCount(1, true);
            mChartKdj.getXAxis().setLabelCount(1, true);
        } else {
            mChartPrice.getXAxis().setLabelCount(5, true);
            mChartVolume.getXAxis().setLabelCount(5, true);
            mChartMacd.getXAxis().setLabelCount(5, true);
            mChartKdj.getXAxis().setLabelCount(5, true);
        }
    }

    private void nofityAndSetZoomBottom() {
        mChartMacd.notifyDataSetChanged();
        moveToLast(mChartMacd);
        mChartMacd.getXAxis().setAxisMaximum(mChartMacd.getData().getXMax() + 0.5f);

        mChartKdj.notifyDataSetChanged();
        moveToLast(mChartKdj);
        mChartKdj.getXAxis().setAxisMaximum(mChartKdj.getData().getXMax() + 0.5f);
    }

    private void notifyAndSetZoomMiddle() {
        mChartVolume.notifyDataSetChanged();
        moveToLast(mChartVolume);
        mChartVolume.getXAxis().setAxisMaximum(mChartVolume.getData().getXMax() + 0.5f);
    }

    private void notifyAndSetZoomTop() {
        mChartPrice.notifyDataSetChanged();
        moveToLast(mChartPrice);
        mChartPrice.getXAxis().setAxisMaximum(mChartPrice.getData().getXMax() + 0.5f);
    }

    private void setKDJData() {
        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(lineDataSetKDJPadding);
        sets.add(lineDataSetK);
        sets.add(lineDataSetD);
        sets.add(lineDataSetJ);
        LineData lineData = new LineData(sets);

        CombinedData combinedData = new CombinedData();
        combinedData.setData(lineData);
        mChartKdj.setData(combinedData);
    }

    private void setBottomData() {
        setMacdData();
        setKDJData();
    }

    private void setMacdData() {
        BarData barData = new BarData(barDataSetMacd);
        barData.setBarWidth(0.75f);
        CombinedData combinedData = new CombinedData();
        combinedData.setData(barData);

        LineData lineData = new LineData(lineDataSetDif, lineDataSetDea);
        combinedData.setData(lineData);
        mChartMacd.setData(combinedData);
    }

    private void initBottomDataSets() {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<Entry> difEntries = new ArrayList<>();
        ArrayList<Entry> deaEntries = new ArrayList<>();

        barDataSetMacd = setBar(barEntries, NORMAL_LINE);
        lineDataSetDif = setLine(DIF, difEntries);
        lineDataSetDea = setLine(DEA, deaEntries);

        ArrayList<Entry> paddingEntries = new ArrayList<>(INIT_COUNT);
        ArrayList<Entry> kEntries = new ArrayList<>(INIT_COUNT);
        ArrayList<Entry> dEntries = new ArrayList<>(INIT_COUNT);
        ArrayList<Entry> jEntries = new ArrayList<>(INIT_COUNT);

        lineDataSetKDJPadding = setLine(KDJ_PADDING, paddingEntries);
        lineDataSetK = setLine(K, kEntries);
        lineDataSetD = setLine(D, dEntries);
        lineDataSetJ = setLine(J, jEntries);
    }

    private void setMiddleData() {
        BarData barData = new BarData(barDataSetVol);
        barData.setBarWidth(0.75f);
        CombinedData combinedData = new CombinedData();
        combinedData.setData(barData);
        LineData lineData = new LineData(
                lineDataSetVolMA5,
                lineDataSetVolMA10);
        combinedData.setData(lineData);
        mChartVolume.setData(combinedData);
    }

    private void initMiddleDataSets() {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
//        List<Double> volList = new ArrayList<>(mData.size());
        List<Entry> ma5Entries = new ArrayList<>(mData.size());
        List<Entry> ma10Entries = new ArrayList<>(mData.size());

        barDataSetVol = setBar(barEntries, NORMAL_LINE);
        lineDataSetVolMA5 = setLine(VOL_MA5, ma5Entries);
        lineDataSetVolMA10 = setLine(VOL_MA10, ma10Entries);
    }

    @NonNull
    private void setTopData() {
        LineData lineData = new LineData(
                lineDataSetMA5,
                lineDataSetMA10,
//                lineDataSetMA20,
                lineDataSetMA30,
                lineDataSetEMA5,
                lineDataSetEMA10,
                lineDataSetEMA30,
                lineDataSetBollUP,
                lineDataSetBollMB,
                lineDataSetBollDN
        );

        CandleData candleData = new CandleData(candleDataSetKLine);
        CombinedData combinedData = new CombinedData();
        combinedData.setData(lineData);
        combinedData.setData(candleData);

        mChartPrice.setData(combinedData);
    }

    private void initTopDataSets() {
        ArrayList<CandleEntry> lineCJEntries = new ArrayList<>(INIT_COUNT);
        candleDataSetKLine = setKLine(NORMAL_LINE, lineCJEntries);

        ArrayList<Entry> ma5Entries = new ArrayList<>(INIT_COUNT);
        ArrayList<Entry> ma10Entries = new ArrayList<>(INIT_COUNT);
//        ArrayList<Entry> ma20Entries = new ArrayList<>(INIT_COUNT);
        ArrayList<Entry> ma30Entries = new ArrayList<>(INIT_COUNT);

        List<Entry> bollUPEntries = new ArrayList<>(INIT_COUNT);
        List<Entry> bollMBEntries = new ArrayList<>(INIT_COUNT);
        List<Entry> bollDNEntries = new ArrayList<>(INIT_COUNT);

        lineDataSetMA5 = setLine(MA5, ma5Entries);
        lineDataSetMA10 = setLine(MA10, ma10Entries);
//        lineDataSetMA20 = setLine(MA20, ma20Entries);
        lineDataSetMA30 = setLine(MA30, ma30Entries);

        lineDataSetEMA5 = setLine(MA5, new ArrayList<Entry>(INIT_COUNT));
        lineDataSetEMA10 = setLine(MA10, new ArrayList<Entry>(INIT_COUNT));
        lineDataSetEMA30 = setLine(MA30, new ArrayList<Entry>(INIT_COUNT));

        lineDataSetBollUP = setLine(BOLL_UP, bollUPEntries);
        lineDataSetBollMB = setLine(BOLL_MB, bollMBEntries);
        lineDataSetBollDN = setLine(BOLL_DN, bollDNEntries);
    }


    private BarDataSet setBar(ArrayList<BarEntry> barEntries, int type) {
        BarDataSet barDataSet = new BarDataSet(barEntries, "vol");
        barDataSet.setHighLightAlpha(255);
        barDataSet.setHighLightColor(mHighLightColor);
        barDataSet.setDrawValues(false);
        barDataSet.setVisible(type != INVISIABLE_LINE);
        barDataSet.setHighlightEnabled(type != INVISIABLE_LINE);
        barDataSet.setColors(getResources().getColor(R.color.chart_increasing_color), getResources().getColor(R.color.chart_decreasing_color));
        barDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);
        return barDataSet;
    }


    @android.support.annotation.NonNull
    private LineDataSet setLine(int type, List<Entry> lineEntries) {
        LineDataSet lineDataSetMa = new LineDataSet(lineEntries, "ma" + type);
        lineDataSetMa.setDrawValues(false);
        if (type == NORMAL_LINE) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_normal_line_color));
            lineDataSetMa.setCircleColor(ContextCompat.getColor(mContext, R.color.chart_normal_line_color));
        } else if (type == K) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_k));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setDrawHorizontalHighlightIndicator(false);
            lineDataSetMa.setHighLightColor(mHighLightColor);
            lineDataSetMa.setHighlightLineWidth(HIGHLIGHT_LINE_WIDTH);
        } else if (type == D) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_d));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == J) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_j));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == DIF) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_dif));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == DEA) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_dea));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == AVE_LINE) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_ave_color));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == MA5) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_ma5));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == MA10) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_ma10));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == MA20) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_ma20));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == MA30) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_ma30));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == BOLL_UP) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_boll_up));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == BOLL_MB) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_boll_mb));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == BOLL_DN) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_boll_dn));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == VOL_MA5) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_vol_ma5));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == VOL_MA10) {
            lineDataSetMa.setColor(getResources().getColor(R.color.chart_vol_ma10));
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setHighlightEnabled(false);
        } else if (type == KDJ_PADDING) {
            lineDataSetMa.setColor(mTransparentColor);
            lineDataSetMa.setCircleColor(mTransparentColor);
            lineDataSetMa.setDrawHorizontalHighlightIndicator(false);
            lineDataSetMa.setHighLightColor(mHighLightColor);
            lineDataSetMa.setHighlightLineWidth(HIGHLIGHT_LINE_WIDTH);
        } else {
            lineDataSetMa.setVisible(false);
            lineDataSetMa.setHighlightEnabled(false);
        }
        lineDataSetMa.setAxisDependency(YAxis.AxisDependency.RIGHT);
        lineDataSetMa.setLineWidth(1f);
        lineDataSetMa.setCircleRadius(1f);
        lineDataSetMa.setValueTextColor(mMainTextColor);

        lineDataSetMa.setDrawCircles(false);
        lineDataSetMa.setDrawCircleHole(false);

        return lineDataSetMa;
    }

    @android.support.annotation.NonNull
    public CandleDataSet setKLine(int type, ArrayList<CandleEntry> lineEntries) {
        CandleDataSet set = new CandleDataSet(lineEntries, "KLine" + type);
        set.setDrawIcons(false);
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);
        set.setShadowColor(Color.DKGRAY);
        set.setShadowWidth(0.75f);
        set.setDecreasingColor(mDecreasingColor);
        set.setDecreasingPaintStyle(Paint.Style.FILL);
        set.setShadowColorSameAsCandle(true);
        set.setIncreasingColor(mIncreasingColor);
        set.setIncreasingPaintStyle(Paint.Style.FILL);
        set.setNeutralColor(mIncreasingColor);
        set.setDrawValues(true);
        set.setValueTextSize(10);
        set.setValueTextColor(mMainTextColor);
        set.setHighLightColor(mHighLightColor);
        set.setHighlightLineWidth(HIGHLIGHT_LINE_WIDTH);
        set.setHighlightEnabled(true);
        set.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return DoubleUtil.formatValue(value);
            }
        });
        if (type != NORMAL_LINE) {
            set.setVisible(false);
        }
        return set;
    }

    /**
     * according to the price to refresh the last data of the chart
     */
    public void refreshData(float price) {
        if (price <= 0 || price == mLastPrice) {
            return;
        }
        mLastPrice = price;
        CombinedData data = mChartPrice.getData();
        if (data == null) return;
        LineData lineData = data.getLineData();
        if (lineData != null) {
            ILineDataSet set = lineData.getDataSetByIndex(0);
            if (set.removeLast()) {
                set.addEntry(new Entry(set.getEntryCount(), price));
            }
        }
        CandleData candleData = data.getCandleData();
        if (candleData != null) {
            ICandleDataSet set = candleData.getDataSetByIndex(0);
            if (set.removeLast()) {
                HisData hisData = mData.get(mData.size() - 1);
                hisData.setClose(price);
                hisData.setHigh(Math.max(hisData.getHigh(), price));
                hisData.setLow(Math.min(hisData.getLow(), price));
                set.addEntry(new CandleEntry(set.getEntryCount(), (float) hisData.getHigh(), (float) hisData.getLow(), (float) hisData.getOpen(), price));

            }
        }
        mChartPrice.notifyDataSetChanged();
        mChartPrice.invalidate();
    }

    public void addDatas(List<HisData> hisDatas) {
        for (HisData hisData : hisDatas) {
            addData(hisData);
        }
    }

    public void addData(HisData hisData) {
        if (mData.contains(hisData)) {
            int index = mData.indexOf(hisData);
            candleDataSetKLine.removeEntry(index);
//            padding.removeFirst();
            // ma比较特殊，entry数量和k线的不一致，移除最后一个
            lineDataSetMA5.removeLast();
            lineDataSetMA10.removeLast();
//            ma20Set.removeLast();
            lineDataSetMA30.removeLast();

            lineDataSetEMA5.removeLast();
            lineDataSetEMA10.removeLast();
            lineDataSetEMA30.removeLast();

            lineDataSetBollUP.removeLast();
            lineDataSetBollMB.removeLast();
            lineDataSetBollDN.removeLast();

            barDataSetVol.removeEntry(index);
            lineDataSetVolMA5.removeLast();
            lineDataSetVolMA10.removeLast();
            barDataSetMacd.removeEntry(index);
            lineDataSetDif.removeEntry(index);
            lineDataSetDea.removeEntry(index);
            lineDataSetKDJPadding.removeEntry(index);
            lineDataSetK.removeLast();
            lineDataSetD.removeLast();
            lineDataSetJ.removeLast();
            mData.remove(index);
        }
        mData.add(hisData);
        mChartPrice.setRealCount(mData.size());
        int klineCount = candleDataSetKLine.getEntryCount();
        candleDataSetKLine.addEntry(new CandleEntry(klineCount, (float) hisData.getHigh(), (float) hisData.getLow(), (float) hisData.getOpen(), (float) hisData.getClose()));
        // 因为ma的数量会少，所以这里用kline的set数量作为x
        if (!Double.isNaN(hisData.getMa5())) {
            lineDataSetMA5.addEntry(new Entry(klineCount, (float) hisData.getMa5()));
        }
        if (!Double.isNaN(hisData.getMa10())) {
            lineDataSetMA10.addEntry(new Entry(klineCount, (float) hisData.getMa10()));
        }
//        if (!Double.isNaN(hisData.getMa20())) {
//            ma20Set.addEntry(new Entry(klineCount, (float) hisData.getMa20()));
//        }
        if (!Double.isNaN(hisData.getMa30())) {
            lineDataSetMA30.addEntry(new Entry(klineCount, (float) hisData.getMa30()));
        }

        if (!Double.isNaN(hisData.getEma5())) {
            lineDataSetEMA5.addEntry(new Entry(klineCount, (float) hisData.getEma5()));
        }

        if (!Double.isNaN(hisData.getEma10())) {
            lineDataSetEMA10.addEntry(new Entry(klineCount, (float) hisData.getEma10()));
        }

        if (!Double.isNaN(hisData.getEma30())) {
            lineDataSetEMA30.addEntry(new Entry(klineCount, (float) hisData.getEma30()));
        }

        if (!Double.isNaN(hisData.getBollUP())) {
            lineDataSetBollUP.addEntry(new Entry(klineCount, (float) hisData.getBollUP()));
        }

        if (!Double.isNaN(hisData.getBollMB())) {
            lineDataSetBollMB.addEntry(new Entry(klineCount, (float) hisData.getBollMB()));
        }

        if (!Double.isNaN(hisData.getBollDN())) {
            lineDataSetBollDN.addEntry(new Entry(klineCount, (float) hisData.getBollDN()));
        }

        barDataSetVol.addEntry(new BarEntry(barDataSetVol.getEntryCount(), (float) hisData.getVol(), hisData));
        if (!Double.isNaN(hisData.getVolMa5())) {
            lineDataSetVolMA5.addEntry(new Entry(klineCount, (float) hisData.getVolMa5()));
        }

        if (!Double.isNaN(hisData.getVolMa10())) {
            lineDataSetVolMA10.addEntry(new Entry(klineCount, (float) hisData.getVolMa10()));
        }

        barDataSetMacd.addEntry(new BarEntry(barDataSetMacd.getEntryCount(), (float) hisData.getMacd()));
        lineDataSetDif.addEntry(new Entry(lineDataSetDif.getEntryCount(), (float) hisData.getDif()));
        lineDataSetDea.addEntry(new Entry(lineDataSetDea.getEntryCount(), (float) hisData.getDea()));

        if (!Double.isNaN(hisData.getK())) {
            lineDataSetKDJPadding.addEntry(new Entry(lineDataSetKDJPadding.getEntryCount(), (float) hisData.getK()));
        } else {
            lineDataSetKDJPadding.addEntry(new Entry(lineDataSetKDJPadding.getEntryCount(), (float) mData.get(mData.size() - 1).getK()));
        }

        if (!Double.isNaN(hisData.getK())) {
            lineDataSetK.addEntry(new Entry(lineDataSetK.getEntryCount(), (float) hisData.getK()));
        }

        if (!Double.isNaN(hisData.getD())) {
            lineDataSetD.addEntry(new Entry(lineDataSetD.getEntryCount(), (float) hisData.getD()));
        }

        if (!Double.isNaN(hisData.getJ())) {
            lineDataSetJ.addEntry(new Entry(lineDataSetJ.getEntryCount(), (float) hisData.getJ()));
        }

        mChartPrice.getXAxis().setAxisMaximum(mChartPrice.getData().getXMax() + 0.5f);
        mChartVolume.getXAxis().setAxisMaximum(mChartVolume.getData().getXMax() + 0.5f);
        mChartMacd.getXAxis().setAxisMaximum(mChartMacd.getData().getXMax() + 0.5f);
        mChartKdj.getXAxis().setAxisMaximum(mChartKdj.getData().getXMax() + 0.5f);

        updateLabelCount();
        setVisibleXRange();
        notifyDataSetChanged();

        //没有高亮则更新Description
        if (mChartInfoView != null && mChartInfoView.getVisibility() == View.GONE) {
            setChartDescription(hisData);
        }
    }

    public void updateDatas(List<HisData> hisDatas) {

        // 这里需要重新绘制图表，把之前的图表清理掉
        clearTopDataSets();
        clearMiddleDataSets();
        clearBottomDataSets();

        addAll(hisDatas);
        updateLabelCount();
        mChartPrice.setRealCount(mData.size());

        addEntrys();

        setVisibleXRange();

        mChartPrice.moveViewToX(hisDatas.size() - 0.5f);
        mChartVolume.moveViewToX(hisDatas.size() - 0.5f);
        mChartMacd.moveViewToX(hisDatas.size() - 0.5f);
        mChartKdj.moveViewToX(hisDatas.size() - 0.5f);

        notifyDataSetChanged();

        HisData hisData = mData.get(0);
        setChartDescription(hisData);

    }

    private void notifyDataSetChanged() {
        mChartPrice.notifyDataSetChanged();
        mChartPrice.invalidate();
        mChartVolume.notifyDataSetChanged();
        mChartVolume.invalidate();
        mChartMacd.notifyDataSetChanged();
        mChartMacd.invalidate();
        mChartKdj.notifyDataSetChanged();
        mChartKdj.invalidate();
    }

    private void setVisibleXRange() {
        if(mData.size()<5){
            int min = Math.max(2,mData.size()*2);
            mChartPrice.setVisibleXRange(MAX_COUNT, min);
            mChartVolume.setVisibleXRange(MAX_COUNT, min);
            mChartMacd.setVisibleXRange(MAX_COUNT, min);
            mChartKdj.setVisibleXRange(MAX_COUNT, min);
        }else{
            mChartPrice.setVisibleXRange(MAX_COUNT, MIN_COUNT);
            mChartVolume.setVisibleXRange(MAX_COUNT, MIN_COUNT);
            mChartMacd.setVisibleXRange(MAX_COUNT, MIN_COUNT);
            mChartKdj.setVisibleXRange(MAX_COUNT, MIN_COUNT);
        }
    }

    private void addEntrys() {
        for (int i = 0; i < mData.size(); i++) {
            HisData hisData = mData.get(i);
            candleDataSetKLine.addEntry(new CandleEntry(i, (float) hisData.getHigh(), (float) hisData.getLow(), (float) hisData.getOpen(), (float) hisData.getClose()));

            if (!Double.isNaN(hisData.getMa5())) {
                lineDataSetMA5.addEntry(new Entry(i, (float) hisData.getMa5()));
            }

            if (!Double.isNaN(hisData.getMa10())) {
                lineDataSetMA10.addEntry(new Entry(i, (float) hisData.getMa10()));
            }

//            if (!Double.isNaN(hisData.getMa20())) {
//                ma20Set.addEntry(new Entry(i, (float) hisData.getMa20()));
//            }

            if (!Double.isNaN(hisData.getMa30())) {
                lineDataSetMA30.addEntry(new Entry(i, (float) hisData.getMa30()));
            }

            if (!Double.isNaN(hisData.getEma5())) {
                lineDataSetEMA5.addEntry(new Entry(i, (float) hisData.getEma5()));
            }

            if (!Double.isNaN(hisData.getEma10())) {
                lineDataSetEMA10.addEntry(new Entry(i, (float) hisData.getEma10()));
            }

            if (!Double.isNaN(hisData.getEma30())) {
                lineDataSetEMA30.addEntry(new Entry(i, (float) hisData.getEma30()));
            }

            if (!Double.isNaN(hisData.getBollUP())) {
                lineDataSetBollUP.addEntry(new Entry(i, (float) hisData.getBollUP()));
            }

            if (!Double.isNaN(hisData.getBollMB())) {
                lineDataSetBollMB.addEntry(new Entry(i, (float) hisData.getBollMB()));
            }

            if (!Double.isNaN(hisData.getBollDN())) {
                lineDataSetBollDN.addEntry(new Entry(i, (float) hisData.getBollDN()));
            }

            barDataSetVol.addEntry(new BarEntry(i, (float) hisData.getVol(), hisData));
            if (!Double.isNaN(hisData.getVolMa5())) {
                lineDataSetVolMA5.addEntry(new Entry(i, (float) hisData.getVolMa5(), hisData));
            }
            if (!Double.isNaN(hisData.getVolMa10())) {
                lineDataSetVolMA10.addEntry(new Entry(i, (float) hisData.getVolMa10(), hisData));
            }

            barDataSetMacd.addEntry(new BarEntry(i, (float) hisData.getMacd()));
            lineDataSetDif.addEntry(new Entry(i, (float) hisData.getDif()));
            lineDataSetDea.addEntry(new Entry(i, (float) hisData.getDea()));


            if (!Double.isNaN(hisData.getK())) {
                lineDataSetKDJPadding.addEntry(new Entry(i, (float) hisData.getK()));
            } else {
                lineDataSetKDJPadding.addEntry(new Entry(i, (float) mData.get(mData.size() - 1).getK()));
            }

            if (!Double.isNaN(hisData.getK())) {
                lineDataSetK.addEntry(new Entry(i, (float) hisData.getK()));
            }

            if (!Double.isNaN(hisData.getD())) {
                lineDataSetD.addEntry(new Entry(i, (float) hisData.getD()));
            }

            if (!Double.isNaN(hisData.getJ())) {
                lineDataSetJ.addEntry(new Entry(i, (float) hisData.getJ()));
            }

        }
    }

    private void clearBottomDataSets() {
        barDataSetMacd.clear();
        lineDataSetDif.clear();
        lineDataSetDea.clear();

        lineDataSetKDJPadding.clear();
        lineDataSetK.clear();
        lineDataSetD.clear();
        lineDataSetJ.clear();
    }

    private void clearMiddleDataSets() {
        barDataSetVol.clear();
        lineDataSetVolMA5.clear();
        lineDataSetVolMA10.clear();
    }

    private void clearTopDataSets() {
        candleDataSetKLine.clear();

        lineDataSetMA5.clear();
        lineDataSetMA10.clear();
//        ma20Set.clear();
        lineDataSetMA30.clear();

        lineDataSetEMA5.clear();
        lineDataSetEMA10.clear();
        lineDataSetEMA30.clear();

        lineDataSetBollUP.clear();
        lineDataSetBollMB.clear();
        lineDataSetBollDN.clear();
    }

    private void addAll(List<HisData> hisDatas) {
        mData.clear();
        mData.addAll(hisDatas);
    }

    public void setChartDescription(HisData hisData) {
        mCurrentHisData = hisData;
        setTopDescription(hisData);
        setMiddleDescription(hisData);
        setBottomDescription(hisData);
    }

    private void setMiddleDescription(HisData hisData) {
        if (hisData == null) {
            return;
        }
        mTvVol.setText(getDescriptionText("VOL:", hisData.getVol()));
        mTvVolMa5.setText(getDescriptionText("MA5:", hisData.getVolMa5()));
        mTvVolMa10.setText(getDescriptionText("MA10:", hisData.getVolMa10()));
    }

    private void setTopDescription(HisData hisData) {
        if (hisData == null) {
            return;
        }

        if (lineDataSetMA5.isVisible()) {
            mTvPriceInfoMa5.setText(getDescriptionText("MA5:", hisData.getMa5()));
            mTvPriceInfoMa10.setText(getDescriptionText("MA10:", hisData.getMa10()));
            mTvPriceInfoMa30.setText(getDescriptionText("MA30:", hisData.getMa30()));
        } else if (lineDataSetEMA5.isVisible()) {
            mTvPriceInfoMa5.setText(getDescriptionText("EMA5:", hisData.getEma5()));
            mTvPriceInfoMa10.setText(getDescriptionText("EMA10:", hisData.getEma10()));
            mTvPriceInfoMa30.setText(getDescriptionText("EMA30:", hisData.getEma30()));
        } else if (lineDataSetBollUP.isVisible()) {
            mTvPriceInfoMa5.setText(getDescriptionText("BOLL:", hisData.getBollMB()));
            mTvPriceInfoMa10.setText(getDescriptionText("UB:", hisData.getBollUP()));
            mTvPriceInfoMa30.setText(getDescriptionText("LB:", hisData.getBollDN()));
        }
    }

    /**
     * @param hisData
     */
    private void setBottomDescription(HisData hisData) {
        if (hisData == null) {
            return;
        }
        if (mChartMacd.getVisibility() == View.VISIBLE) {
            mTvBottomValue1.setText(new StringBuilder("MACD(12,26,9)"));
            mTvBottomValue2.setText(getDescriptionText("MACD:", hisData.getMacd()));
            mTvBottomValue3.setText(getDescriptionText("DIF:", hisData.getDif()));
            mTvBottomValue4.setText(getDescriptionText("DEA:", hisData.getDea()));
        } else if (mChartKdj.getVisibility() == View.VISIBLE) {
            mTvBottomValue1.setText(new StringBuilder("KDJ(14,3,3)"));
            mTvBottomValue2.setText(getDescriptionText("K:", hisData.getK()));
            mTvBottomValue3.setText(getDescriptionText("D:", hisData.getD()));
            mTvBottomValue4.setText(getDescriptionText("J:", hisData.getJ()));
        }
    }

    private String getDescriptionText(String label, double value) {
        return Double.isNaN(value) ? "" : new StringBuilder(label).append(DoubleUtil.formatValue(value)).toString();
    }

    /**
     * align two chart
     */
    private void updateOffset() {
        int chartMiddleHeight = getResources().getDimensionPixelSize(R.dimen.chart_middle_part_height);
        int chartBottomOffsets = chartMiddleHeight + getChartBottomHeight();
        float leftOffset = 0;
        float rightOffset = DisplayUtils.dip2px(mContext, 50);

        mChartPrice.setViewPortOffsets(leftOffset, 10, rightOffset, chartBottomOffsets);

        mChartPrice.postInvalidate();
        int bottom = DisplayUtils.dip2px(mContext, 20);
//        int middleTopSpace = DisplayUtils.dip2px(mContext, 16);
        int middleTopSpace = 0;
        mChartVolume.setViewPortOffsets(leftOffset, middleTopSpace, rightOffset, 0);
        mChartMacd.setViewPortOffsets(leftOffset, middleTopSpace, rightOffset, bottom);
        mChartKdj.setViewPortOffsets(leftOffset, middleTopSpace, rightOffset, bottom);

        MarginLayoutParams layoutParams = (MarginLayoutParams) mChartInfoView.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.rightMargin = (int) rightOffset;
        }

    }

    private int getChartBottomHeight() {
        MarginLayoutParams lp = (MarginLayoutParams) mFlMiddlePart.getLayoutParams();
        return lp != null && lp.bottomMargin > 0 ? lp.bottomMargin : getResources().getDimensionPixelOffset(R.dimen.chart_bottom_part_height);
    }

    /**
     * add limit line to chart
     */
    public void setLimitLine(double lastClose) {
        LimitLine limitLine = new LimitLine((float) lastClose);
        limitLine.enableDashedLine(5, 10, 0);
        limitLine.setLineColor(getResources().getColor(R.color.chart_limit_color));
        mChartPrice.getAxisLeft().addLimitLine(limitLine);
    }

    public void setLimitLine() {
        setLimitLine(mLastClose);
    }

    public void setLastClose(double lastClose) {
        mLastClose = lastClose;
        mChartPrice.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, mChartVolume, mChartMacd, mChartKdj));
        mChartVolume.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, mChartPrice, mChartMacd, mChartKdj));
        mChartMacd.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, mChartPrice, mChartVolume, mChartKdj));
        mChartKdj.setOnChartValueSelectedListener(new InfoViewListener(mContext, mLastClose, mData, mChartInfoView, mChartPrice, mChartVolume, mChartMacd));

    }

    @Override
    public void onAxisChange(BarLineChartBase chart) {
        float lowestVisibleX = chart.getLowestVisibleX();
        if (lowestVisibleX <= chart.getXAxis().getAxisMinimum()) return;
        int maxX = (int) chart.getHighestVisibleX();
        int x = Math.min(maxX, mData.size() - 1);
        HisData hisData = mData.get(x < 0 ? 0 : x);
        setChartDescription(hisData);
        Log.d("leon", "======= onAxisChange: ");

        if (mChartInfoView != null) {
            mChartInfoView.hide();
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener l) {
        if (mCoupleChartGestureListener != null) {
            mCoupleChartGestureListener.setOnLoadMoreListener(l);
        }
    }

    public void loadMoreComplete() {
        if (mCoupleChartGestureListener != null) {
            mCoupleChartGestureListener.loadMoreComplete();
        }
    }

    public void hideMainPartLine() {
        setMAVisible(false);
        setEMAVisible(false);
        setBollVisible(false);
        clearTopDescription();
        mChartPrice.notifyDataSetChanged();
        mChartPrice.postInvalidate();
    }

    private void clearTopDescription() {
        mTvPriceInfoMa5.setText("");
        mTvPriceInfoMa10.setText("");
        mTvPriceInfoMa30.setText("");
    }

    public void showMA() {
        setMAVisible(true);
        setEMAVisible(false);
        setBollVisible(false);
        mChartPrice.notifyDataSetChanged();
        mChartPrice.postInvalidate();
        setTopDescription(mCurrentHisData);
    }

    public void showEMA() {
        setMAVisible(false);
        setEMAVisible(true);
        setBollVisible(false);
        mChartPrice.notifyDataSetChanged();
        mChartPrice.postInvalidate();
        setTopDescription(mCurrentHisData);
    }

    public void showBoll() {
        setMAVisible(false);
        setEMAVisible(false);
        setBollVisible(true);
        mChartPrice.notifyDataSetChanged();
        mChartPrice.postInvalidate();
        setTopDescription(mCurrentHisData);
    }

    private void setMAVisible(boolean visible) {
        if (lineDataSetMA5 == null) {
            return;
        }
        lineDataSetMA5.setVisible(visible);
        lineDataSetMA10.setVisible(visible);
//        lineDataSetMA20.setVisible(visible);
        lineDataSetMA30.setVisible(visible);
    }

    private void setEMAVisible(boolean visible) {
        if (lineDataSetEMA5 == null) {
            return;
        }
        lineDataSetEMA5.setVisible(visible);
        lineDataSetEMA10.setVisible(visible);
        lineDataSetEMA30.setVisible(visible);
    }

    private void setBollVisible(boolean visible) {
        if (lineDataSetBollUP == null) {
            return;
        }
        lineDataSetBollUP.setVisible(visible);
        lineDataSetBollMB.setVisible(visible);
        lineDataSetBollDN.setVisible(visible);
    }

    @Override
    public void setDateFormat(String mDateFormat) {
        super.setDateFormat(mDateFormat);
        if (mChartInfoView != null) {
            mChartInfoView.setDateFormat(mDateFormat);
        }
    }

    public void setVolUnit(String volUnit) {
        if (mChartInfoView != null) {
            mChartInfoView.setVolUnit(volUnit);
        }
    }

}
package com.guoziwei.klinelib.chart;

/**
 * Created by Administrator on 2016/2/1.
 */

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

import com.wanshare.wscomponent.chart.kline.model.bean.HisData;
import com.wanshare.wscomponent.chart.R;
import com.wanshare.wscomponent.utils.DateUtil;

import java.util.List;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
public class LineChartXMarkerView extends MarkerView {

    private List<HisData> mList;
    private TextView tvContent;

    public LineChartXMarkerView(Context context, List<HisData> list) {
        super(context, R.layout.view_mp_real_price_marker);
        mList = list;
        tvContent = (TextView) findViewById(R.id.tvContent);
    }


    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        int value = (int) e.getX();
        if (mList != null && value < mList.size()) {
            tvContent.setText(DateUtil.formatDate(mList.get(value).getDate(),"HH:mm"));
        }
        super.refreshContent(e, highlight);
    }
}

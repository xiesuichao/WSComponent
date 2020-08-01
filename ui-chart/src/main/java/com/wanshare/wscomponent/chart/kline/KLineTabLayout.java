package com.wanshare.wscomponent.chart.kline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.wanshare.wscomponent.chart.R;
import com.wanshare.wscomponent.logger.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * k线顶部指示器
 * </br>
 * Date: 2018/8/10 17:52
 *
 * @author hemin
 */
public class KLineTabLayout extends LinearLayout implements View.OnClickListener {

    public static final int PERIOD_TOP_ITEM_COUNT = 5;
    private View mPeriodButtonLayout;
    private View mQuotaButtonLayout;
    private LinearLayout mLlQuotaItems;
    private LinearLayout mLlPeriodItems;

    List<DataItem> mPeriodItems;
    List<TextView> periodTabItemViews;
    List<View> periodTabUnderlines;
    private int mSelectedTabPosition = 0;
    private TextView mTvPeriodMore;

    List<DataItem> mainQuotaItems;
    List<TextView> mainQuotaItemViews;
    private int mSelectedMainQuotaPosition = -1;
    private OnClickListener mMainQuotaItemOnclick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mainQuotaItems != null && v.getTag() != null && v.getTag() instanceof Integer) {
                Integer position = (Integer) v.getTag();
                if (position >= 0 && position < mainQuotaItems.size()) {
                    DataItem periodItem = mainQuotaItems.get(position);
                    LogUtil.d("periodItem:" + periodItem);
                    setMainQuotaItemUnselected(mSelectedMainQuotaPosition);
                    mSelectedMainQuotaPosition = position;
                    setMainQuotaItemSelected(mSelectedMainQuotaPosition);
                    if (mListener != null) {
                        mListener.onMainQuotaSelected(periodItem.label);
                    }
                    hideMoreView();
                }
            } else if (v == mTvMainQuotaHide) {
                setMainQuotaItemUnselected(mSelectedMainQuotaPosition);
                mSelectedMainQuotaPosition = -1;
                setMainQuotaItemSelected(mSelectedMainQuotaPosition);
                if (mListener != null) {
                    mListener.onMainQuotaSelected("");
                }
                hideMoreView();
            }
        }
    };
    private View mTvMainQuotaHide;

    List<DataItem> secondQuotaItems;
    List<TextView> secondQuotaItemViews;
    private int mSelectedSecondQuotaPosition = -1;
    private OnClickListener mSecondQuotaItemClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (secondQuotaItems != null && v.getTag() != null && v.getTag() instanceof Integer) {
                Integer position = (Integer) v.getTag();
                if (position >= 0 && position < secondQuotaItems.size()) {
                    DataItem periodItem = secondQuotaItems.get(position);
                    setSecondQuotaItemUnselected(mSelectedSecondQuotaPosition);
                    mSelectedSecondQuotaPosition = position;
                    setSecondQuotaItemSelected(mSelectedSecondQuotaPosition);
                    if (mListener != null) {
                        mListener.onSecondQuotaSelected(periodItem.label);
                    }
                    hideMoreView();
                }
            } else if (v == mTvSecondQuotaHide) {
                setSecondQuotaItemUnselected(mSelectedSecondQuotaPosition);
                mSelectedSecondQuotaPosition = -1;
                setSecondQuotaItemSelected(mSelectedSecondQuotaPosition);
                if (mListener != null) {
                    mListener.onSecondQuotaSelected("");
                }
                hideMoreView();
            }
        }
    };
    private View mTvSecondQuotaHide;
    private LinearLayout mTopLayout;

    private onTabSelectListener mListener;

    public KLineTabLayout(Context context) {
        this(context, null);
    }

    public KLineTabLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KLineTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        initPeriodTab(context);
        initQuota(context);
        initPeriodMore(context);
    }

    private void initQuota(Context context) {
        View viewQuata = LayoutInflater.from(context).inflate(R.layout.layout_kline_quota_items_more, this, true);
        mLlQuotaItems = viewQuata.findViewById(R.id.ll_quota_items);
        mLlQuotaItems.setVisibility(View.GONE);

        initMainQuota(context, viewQuata);
        initSecondQuota(context, viewQuata);
    }

    @NonNull
    private void initPeriodTab(Context context) {
        View viewTop = LayoutInflater.from(context).inflate(R.layout.layout_kline_tab_top, this, true);
        mTopLayout = viewTop.findViewById(R.id.ll_top);
    }

    public void initTabs(List<DataItem> items) {
        if (items == null || items.isEmpty()) {
            return;
        }
        if (mPeriodItems == null) {
            mPeriodItems = new ArrayList<>();
        } else {
            mPeriodItems.clear();
        }

        mPeriodItems.addAll(items);

        mTopLayout.removeAllViews();

        periodTabItemViews = new ArrayList<>();
        periodTabUnderlines = new ArrayList<>();
        for (int i = 0; i < PERIOD_TOP_ITEM_COUNT; i++) {
            View item = LayoutInflater.from(getContext()).inflate(R.layout.layout_kline_tab_period_item, mTopLayout, false);
            RelativeLayout rlTabPeriodItem = item.findViewById(R.id.rl_tab_period_item);
            rlTabPeriodItem.setTag(i);
            rlTabPeriodItem.setOnClickListener(this);
            TextView tvPeriodItem = item.findViewById(R.id.tv_period_item);
            tvPeriodItem.setText(mPeriodItems.get(i).label);
            periodTabItemViews.add(tvPeriodItem);
            View vUnderline = item.findViewById(R.id.view_underline);
            periodTabUnderlines.add(vUnderline);
            mTopLayout.addView(item);
        }

        mPeriodButtonLayout = LayoutInflater.from(getContext()).inflate(R.layout.layout_kline_tab_select_button, mTopLayout, false);
        mTvPeriodMore = mPeriodButtonLayout.findViewById(R.id.tv_selected);
        mTvPeriodMore.setText(R.string.chart_more);

        mQuotaButtonLayout = LayoutInflater.from(getContext()).inflate(R.layout.layout_kline_tab_select_button, mTopLayout, false);
        TextView tvQuota = mQuotaButtonLayout.findViewById(R.id.tv_selected);
        tvQuota.setText(R.string.chart_quota);

        mTopLayout.addView(mPeriodButtonLayout);
        mTopLayout.addView(mQuotaButtonLayout);

        mPeriodButtonLayout.setOnClickListener(this);
        mQuotaButtonLayout.setOnClickListener(this);

        // more period items
        for (int i = PERIOD_TOP_ITEM_COUNT; i < mPeriodItems.size(); i++) {
            View item = LayoutInflater.from(getContext()).inflate(R.layout.layout_kline_period_item, mLlPeriodItems, false);
            TextView tvPeriodItem = item.findViewById(R.id.tv_period_more_item);
            tvPeriodItem.setTag(i);
            periodTabItemViews.add(tvPeriodItem);
            tvPeriodItem.setOnClickListener(this);
            tvPeriodItem.setText(mPeriodItems.get(i).label);
            mLlPeriodItems.addView(item);
        }

        setPeriodTabItemSelected(mSelectedTabPosition);
    }

    private void initSecondQuota(Context context, View viewQuata) {
        secondQuotaItems = new ArrayList<>();
        secondQuotaItems.add(new DataItem(Quota.MACD));
        secondQuotaItems.add(new DataItem(Quota.KDJ));
        LinearLayout llSecondQuota = viewQuata.findViewById(R.id.ll_second_quota);
        secondQuotaItemViews = new ArrayList<>();
        for (int i = 0; i < secondQuotaItems.size(); i++) {
            View item = LayoutInflater.from(context).inflate(R.layout.layout_kline_quota_item, llSecondQuota, false);
            TextView tvQuotaItem = item.findViewById(R.id.tv_quota_item);
            tvQuotaItem.setTag(i);
            secondQuotaItemViews.add(tvQuotaItem);
            tvQuotaItem.setText(secondQuotaItems.get(i).label);
            llSecondQuota.addView(item);

            tvQuotaItem.setOnClickListener(mSecondQuotaItemClick);
        }
        mTvSecondQuotaHide = viewQuata.findViewById(R.id.tv_second_quota_hide);
        mTvSecondQuotaHide.setOnClickListener(mSecondQuotaItemClick);
    }

    private void initPeriodMore(Context context) {
        View viewPeriodMore = LayoutInflater.from(context).inflate(R.layout.layout_kline_period_items_more, this, true);
        mLlPeriodItems = viewPeriodMore.findViewById(R.id.ll_period_items);
        mLlPeriodItems.setVisibility(View.GONE);
    }

    private void initMainQuota(Context context, View viewQuata) {
        mainQuotaItems = new ArrayList<>();
        mainQuotaItems.add(new DataItem(Quota.MA));
        mainQuotaItems.add(new DataItem(Quota.EMA));
        mainQuotaItems.add(new DataItem(Quota.BOLL));

        LinearLayout llMainQuota = viewQuata.findViewById(R.id.ll_main_quota);
        mainQuotaItemViews = new ArrayList<>();
        for (int i = 0; i < mainQuotaItems.size(); i++) {
            View item = LayoutInflater.from(context).inflate(R.layout.layout_kline_quota_item, llMainQuota, false);
            TextView tvQuotaItem = item.findViewById(R.id.tv_quota_item);
            tvQuotaItem.setTag(i);
            mainQuotaItemViews.add(tvQuotaItem);
            tvQuotaItem.setText(mainQuotaItems.get(i).label);
            llMainQuota.addView(item);

            tvQuotaItem.setOnClickListener(mMainQuotaItemOnclick);
        }
        mTvMainQuotaHide = viewQuata.findViewById(R.id.tv_main_quota_hide);
        mTvMainQuotaHide.setOnClickListener(mMainQuotaItemOnclick);
    }

    private void setPeriodTabItemUnselected(int position) {
        periodTabItemViews.get(position).setSelected(false);
        mTvPeriodMore.setText(R.string.chart_more);
        mTvPeriodMore.setSelected(false);
        if (position >= PERIOD_TOP_ITEM_COUNT) {
            return;
        }
        periodTabUnderlines.get(position).setSelected(false);
    }

    private void setPeriodTabItemSelected(int position) {
        periodTabItemViews.get(position).setSelected(true);
        if (position >= PERIOD_TOP_ITEM_COUNT) {
            DataItem periodItem = mPeriodItems.get(position);
            mTvPeriodMore.setText(periodItem.label);
            mTvPeriodMore.setSelected(true);
            return;
        }
        periodTabUnderlines.get(position).setSelected(true);
    }

    @Override
    public void onClick(View v) {
        if (v == mPeriodButtonLayout) {
            if (mLlPeriodItems.getVisibility() == View.GONE
                    ) {
                showPeriodMoreView();
                hideQuotaMoreView();
            } else if (mLlPeriodItems.getVisibility() == View.VISIBLE
                    ) {
                hideMoreView();
            }
        } else if (v == mQuotaButtonLayout) {
            if (mLlQuotaItems.getVisibility() == View.GONE
                    ) {
                hidePeriodMoreView();
                showQuotaMoreView();
            } else if (mLlQuotaItems.getVisibility() == View.VISIBLE
                    ) {
                hideMoreView();
            }
        } else if (v.getTag() != null && v.getTag() instanceof Integer) {
            Integer position = (Integer) v.getTag();
            if (position >= 0 && position < PERIOD_TOP_ITEM_COUNT) {
                // tab period
                DataItem periodItem = mPeriodItems.get(position);
                LogUtil.d("periodItem:" + periodItem);
                setPeriodTabItemUnselected(mSelectedTabPosition);
                hideMoreView();
                mSelectedTabPosition = position;
                setPeriodTabItemSelected(mSelectedTabPosition);
                if (mListener != null) {
                    mListener.onPeriodSelected(periodItem.label, periodItem.getPeriod(), position);
                }
                mTvPeriodMore.setSelected(false);
                mTvPeriodMore.setText(R.string.chart_more);
            } else if (position >= PERIOD_TOP_ITEM_COUNT && position < mPeriodItems.size()) {
                // more period
                DataItem periodItem = mPeriodItems.get(position);
                LogUtil.d("periodItem:" + periodItem);
                mTvPeriodMore.setText(periodItem.label);
                setPeriodTabItemUnselected(mSelectedTabPosition);
                hideMoreView();
                mSelectedTabPosition = position;
                setPeriodTabItemSelected(mSelectedTabPosition);
                if (mListener != null) {
                    mListener.onPeriodSelected(periodItem.label, periodItem.getPeriod(), position);
                }
                mTvPeriodMore.setSelected(true);
            }
        }
    }

    private void showQuotaMoreView() {
        mLlQuotaItems.setVisibility(View.VISIBLE);
        mQuotaButtonLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.chart_gray_bg));
    }

    private void hidePeriodMoreView() {
        mLlPeriodItems.setVisibility(View.GONE);
        mPeriodButtonLayout.setBackgroundColor(ContextCompat.getColor(getContext(),android.R.color.transparent));
    }

    private void hideQuotaMoreView() {
        mLlQuotaItems.setVisibility(View.GONE);
        mQuotaButtonLayout.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
    }

    private void showPeriodMoreView() {
        mLlPeriodItems.setVisibility(View.VISIBLE);
        mPeriodButtonLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.chart_gray_bg));
    }

    private void hideMoreView() {
        hidePeriodMoreView();
        hideQuotaMoreView();
    }

    private void setSecondQuotaItemSelected(int position) {
        if (position < 0) {
            return;
        }
        secondQuotaItemViews.get(position).setSelected(true);
    }

    private void setSecondQuotaItemUnselected(int position) {
        if (position < 0) {
            return;
        }
        secondQuotaItemViews.get(position).setSelected(false);
    }

    private void setMainQuotaItemSelected(int position) {
        if (position < 0) {
            return;
        }
        mainQuotaItemViews.get(position).setSelected(true);
    }

    private void setMainQuotaItemUnselected(int position) {
        if (position < 0) {
            return;
        }

        mainQuotaItemViews.get(position).setSelected(false);
    }

    public void setSelectedQuota(String mainQuota, String secondQuota) {
        if (mainQuotaItems != null) {
            setMainQuotaItemUnselected(mSelectedMainQuotaPosition);
            mSelectedMainQuotaPosition = -1;
            for (int i = 0; i < mainQuotaItems.size(); i++) {
                if (mainQuotaItems.get(i).label.equalsIgnoreCase(mainQuota)) {
                    mSelectedMainQuotaPosition = i;
                }
            }
            setMainQuotaItemSelected(mSelectedMainQuotaPosition);
        }

        if (secondQuotaItems != null) {
            setSecondQuotaItemUnselected(mSelectedSecondQuotaPosition);
            mSelectedSecondQuotaPosition = -1;
            for (int i = 0; i < secondQuotaItems.size(); i++) {
                if (secondQuotaItems.get(i).label.equalsIgnoreCase(secondQuota)) {
                    mSelectedSecondQuotaPosition = i;
                }
            }
            setSecondQuotaItemSelected(mSelectedSecondQuotaPosition);
        }
    }

    public void setSelectedPeriod(int position) {
        if (mPeriodItems == null) {
            return;
        }
        setPeriodTabItemUnselected(mSelectedTabPosition);
        if (position >= 0 && position < mPeriodItems.size()) {
            mSelectedTabPosition = position;
        } else {
            mSelectedTabPosition = 0;
        }
        setPeriodTabItemSelected(mSelectedTabPosition);
    }

    public static class DataItem {
        String label;
        String period;

        public DataItem(String label) {
            this.label = label;
        }

        public DataItem(String label, String period) {
            this.label = label;
            this.period = period;
        }

        @Override
        public String toString() {
            return "DataItem{" +
                    "label='" + label + '\'' +
                    ", period='" + period + '\'' +
                    '}';
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }
    }

    public void setOnTabSelectListener(onTabSelectListener listener) {
        this.mListener = listener;
    }

    public interface onTabSelectListener {
        void onPeriodSelected(String label, String period, int position);

        void onMainQuotaSelected(String quota);

        void onSecondQuotaSelected(String quota);
    }
}

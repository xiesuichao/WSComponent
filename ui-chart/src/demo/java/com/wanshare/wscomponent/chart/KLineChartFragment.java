package com.wanshare.wscomponent.chart;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.guoziwei.klinelib.chart.OnLoadMoreListener;
import com.wanshare.wscomponent.chart.kline.WSKlineView;
import com.wanshare.wscomponent.chart.kline.model.KLineQuotaModel;
import com.wanshare.wscomponent.chart.kline.model.bean.HisData;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class KLineChartFragment extends Fragment {

    KLineQuotaModel model = new KLineQuotaModel();

    private WSKlineView mKLineView;
    private int mDay;

    public KLineChartFragment() {
        // Required empty public constructor
    }

    public static KLineChartFragment newInstance(int day) {
        KLineChartFragment fragment = new KLineChartFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("day", day);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDay = getArguments().getInt("day");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kline_chart, container, false);
        mKLineView = v.findViewById(R.id.kline);
        RadioGroup rgIndex = v.findViewById(R.id.rg_index);
        mKLineView.setDateFormat("MM-dd HH:mm");
        rgIndex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.cb_hide) {
                    hideChartBottomPart();
                } else if (checkedId == R.id.cb_macd) {
                    showMacd();
                } else if (checkedId == R.id.cb_kdj) {
                    showKdj();
                }
            }
        });

        RadioGroup rgMain = v.findViewById(R.id.rg_main_part);
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.cb_main_ma) {
                    showMA();
                }else if(checkedId == R.id.cb_main_ema){
                    showEMA();
                } else if (checkedId == R.id.cb_main_boll) {
                    showBoll();
                } else if (checkedId == R.id.cb_main_hide) {
                    hideMainPartLine();
                }
            }
        });

        initData();
        ((RadioButton) rgIndex.getChildAt(0)).setChecked(true);
        ((RadioButton) rgMain.getChildAt(0)).setChecked(true);
        return v;
    }

    private void showEMA() {
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showEMA();
            }
        });
    }

    private void showBoll() {
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showBoll();
            }
        });
    }

    private void showMA() {
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showMA();
            }
        });
    }

    private void hideMainPartLine() {
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.hideMainPartLine();
            }
        });
    }

    private void hideChartBottomPart() {
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.hideChartBottomPart();
            }
        });
    }


    public void showMacd() {
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showMacd();
            }
        });
    }

    public void showKdj() {
        mKLineView.post(new Runnable() {
            @Override
            public void run() {
                mKLineView.showKdj();
            }
        });
    }

    protected void initData() {
        Observable.create(new ObservableOnSubscribe<List<HisData>>() {
            @Override
            public void subscribe(ObservableEmitter<List<HisData>> emitter) throws Exception {
                final List<HisData> hisData = Util.getK(getContext(), mDay);
                List<HisData> subHisData = hisData.subList(50, hisData.size());
                model.initData(subHisData);
                emitter.onNext(model.getDatas());
            }
        }).subscribeOn(Schedulers.computation())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(new Observer<List<HisData>>() {
               @Override
               public void onSubscribe(Disposable d) {

               }

               @Override
               public void onNext(List<HisData> hisData) {
                   initKLine(hisData);
               }

               @Override
               public void onError(Throwable e) {

               }

               @Override
               public void onComplete() {

               }
           });




        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mKLineView.post(new Runnable() {
                    @Override
                    public void run() {
                        Observable.create(new ObservableOnSubscribe<HisData>() {
                            @Override
                            public void subscribe(ObservableEmitter<HisData> emitter) throws Exception {
                                int index = (int) (Math.random() * model.getDatas().size());
                                HisData data = model.getDatas().get(index);
                                HisData lastData = model.getDatas().get(model.getDatas().size() - 1);
                                HisData newData = new HisData();
                                newData.setVol(data.getVol());
                                newData.setClose(data.getClose());
                                newData.setHigh(Math.max(data.getHigh(), lastData.getClose()));
                                newData.setLow(Math.min(data.getLow(), lastData.getClose()));
                                newData.setOpen(lastData.getClose());
                                newData.setDate(System.currentTimeMillis());
                                model.addNewData(newData);
                                emitter.onNext(newData);
                            }
                        }).subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<HisData>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(HisData newData) {
                                        mKLineView.addData(newData);
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });




                    }
                });
            }
        }, 1000, 1000);

/*
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mKLineView.post(new Runnable() {
                    @Override
                    public void run() {
                        int index = (int) (Math.random() * (hisData.size()));
                        HisData data = hisData.get(index);
                        mKLineView.refreshData((float) data.getClose());
                    }
                });
            }
        }, 500, 1000);*/
    }

    private void initKLine(final List<HisData> hisData) {
        mKLineView.initData(hisData);
        mKLineView.setLimitLine();
        mKLineView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Toast.makeText(getContext(), "触发加载更多", Toast.LENGTH_SHORT).show();
                mKLineView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreData();
                    }
                }, 3000);
            }
        });

        showMA();
        showMacd();
    }

    private void loadMoreData() {
        Toast.makeText(getContext(), "加载更多完成", Toast.LENGTH_SHORT).show();
        Observable.create(new ObservableOnSubscribe<List<HisData>>() {
            @Override
            public void subscribe(ObservableEmitter<List<HisData>> emitter) throws Exception {
                final List<HisData> loadData = Util.getK(getContext(), mDay);
                List<HisData> hisData = loadData.subList(0, 50);
                model.addDatasFirst(hisData);
                emitter.onNext(hisData);
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<HisData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<HisData> hisData) {

                        mKLineView.updateDatas(model.getDatas());
                        mKLineView.loadMoreComplete();
                        mKLineView.setOnLoadMoreListener(null);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}

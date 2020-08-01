package com.wanshare.wscomponent.chart.kline.model;

import com.wanshare.wscomponent.chart.kline.model.bean.HisData;

import java.util.ArrayList;
import java.util.List;

public class RSV {
    private ArrayList<Double> rsv;
    private int n;
    double high = 0.0;
    double low = 0.0;
    double close = 0.0;

    public RSV(List<HisData> OHLCData, int m) {
        n = m;
        rsv = new ArrayList<Double>();
        double rs = 0.0;

        if (OHLCData != null && OHLCData.size() > 0) {

            for (int i = 0; i < OHLCData.size(); i++) {

                HisData oHLCEntity = OHLCData.get(i);
                high = oHLCEntity.getHigh();
                low = oHLCEntity.getLow();
                close = oHLCEntity.getClose();

                if (i < n - 1) {
                    high = 0;
                    low = 0;
                } else {
                    for (int j = 0; j < n; j++) {
                        HisData oHLCEntity1 = OHLCData.get(i - j);
                        high = high > oHLCEntity1.getHigh() ? high : oHLCEntity1.getHigh();
                        low = low < oHLCEntity1.getLow() ? low : oHLCEntity1.getLow();
                    }
                }

                System.out.println("i:" + i + " high:" + high + " low:" + low+" close:"+close);

                if (i < n - 1) {
                    rsv.add(Double.NaN);
                } else if (high != low) {
                    rs = (close - low) / (high - low) * 100;
                    rsv.add(rs);
                } else {
                    rsv.add(0.00);
                }
            }
        }
    }

    public List<Double> getRSV() {
        return rsv;
    }
}


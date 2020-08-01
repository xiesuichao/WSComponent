package com.wanshare.wscomponent.chart.kline.model;

import com.wanshare.wscomponent.chart.kline.model.bean.HisData;

import java.util.ArrayList;
import java.util.List;

/**
 * EMA
 * EXPMA=[2*X+(N-1)*Y’]/(N+1)，其中X表示的是当日收盘价，N为周期天数
 * </br>
 * Date: 2018/8/10 10:27
 *
 * @author hemin
 */
public class EMA {

    public List<Double> calculateEMA(List<HisData> hisDataList, int optInTimePeriod) {
        List<Double> result=new ArrayList<>();
        double expma;
        double preExpma = hisDataList.get(0).getClose();
        double a = 1.0 * 2 / (optInTimePeriod + 1);
        for (int i = 0; i < hisDataList.size(); i++) {
            if (i == 0) {
                expma = hisDataList.get(0).getClose();
            } else {
                expma =hisDataList.get(i).getClose() * a + (1 - a) * preExpma;
            }
            preExpma = expma;
            result.add(expma);
        }

        return result;
    }


    public double calculateLastEMA5(List<HisData> hisDataList) {
        int i = hisDataList.size()-1;
        double preExpma = hisDataList.get(i-1).getEma5();
        int optInTimePeriod=5;
        double a = 1.0 * 2 / (optInTimePeriod + 1);
        double expma =hisDataList.get(i).getClose() * a + (1 - a) * preExpma;
        return expma;
    }

    public double calculateLastEMA10(List<HisData> hisDataList) {
        int i = hisDataList.size()-1;
        double preExpma = hisDataList.get(i-1).getEma10();
        int optInTimePeriod=10;
        double a = 1.0 * 2 / (optInTimePeriod + 1);
        double expma =hisDataList.get(i).getClose() * a + (1 - a) * preExpma;
        return expma;
    }

    public double calculateLastEMA30(List<HisData> hisDataList) {
        int i = hisDataList.size()-1;
        double preExpma = hisDataList.get(i-1).getEma30();
        int optInTimePeriod=30;
        double a = 1.0 * 2 / (optInTimePeriod + 1);
        double expma =hisDataList.get(i).getClose() * a + (1 - a) * preExpma;
        return expma;
    }
}

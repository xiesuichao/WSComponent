package com.wanshare.wscomponent.chart.kline.model;

import android.util.Log;

import com.wanshare.wscomponent.chart.kline.model.bean.HisData;

import java.util.ArrayList;
import java.util.List;

/**
 * K线相关的数据modlel
 * </br>
 * Date: 2018/8/8 11:47
 *
 * @author hemin
 */
public class KLineQuotaModel {

    private final List<HisData> mData = new ArrayList<>(300);

    public synchronized void initData(List<HisData> list) {
        addAll(list);
        calculateHisData();
    }

    private void addAll(List<HisData> list) {
        mData.clear();
        mData.addAll(list);
    }

    private List<HisData> calculateHisData() {
        return calculateHisData( null);
    }

    /**
     * calculate average price and ma data
     */
    private List<HisData> calculateHisData(HisData lastData) {
        List<HisData> list=mData;
        List<Double> ma5List = calculateMA(5, list);
        List<Double> ma10List = calculateMA(10, list);
//        List<Double> ma20List = calculateMA(20, list);
        List<Double> ma30List = calculateMA(30, list);

        EMA ema = new EMA();
        List<Double> ema5List = ema.calculateEMA(list, 5);
        List<Double> ema10List = ema.calculateEMA(list, 10);
        List<Double> ema30List = ema.calculateEMA(list, 30);

        BOLL boll = new BOLL();
        boll.calculateBOLL(list, 13, 2);
        List<Double> ups = boll.getUps();
        List<Double> mbs = boll.getMbs();
        List<Double> dns = boll.getDns();

        List<Double> volMA5s = calculateVolMa(list, 5);
        List<Double> volMA10s = calculateVolMa(list, 10);

        MACD macd = new MACD(list);
        List<Double> bar = macd.getMACD();
        List<Double> dea = macd.getDEA();
        List<Double> dif = macd.getDIF();

        KDJ kdj = new KDJ(list);
        ArrayList<Double> k = kdj.getK();
        ArrayList<Double> d = kdj.getD();
        ArrayList<Double> j = kdj.getJ();

        double amountVol = 0;
        if (lastData != null) {
            amountVol = lastData.getAmountVol();
        }
        for (int i = 0; i < list.size(); i++) {
            HisData hisData = list.get(i);

            hisData.setMa5(ma5List.get(i));
            hisData.setMa10(ma10List.get(i));
//            hisData.setMa20(ma20List.get(i));
            hisData.setMa30(ma30List.get(i));

            hisData.setEma5(ema5List.get(i));
            hisData.setEma10(ema10List.get(i));
            hisData.setEma30(ema30List.get(i));

            if(ups!=null && i<ups.size()){
                hisData.setBollUP(ups.get(i));
            }
            if(mbs!=null && i<mbs.size()){
                hisData.setBollMB(mbs.get(i));
            }
            if(dns!=null && i < dns.size()){
                hisData.setBollDN(dns.get(i));
            }

            hisData.setVolMa5(volMA5s.get(i));
            hisData.setVolMa10(volMA10s.get(i));

            hisData.setMacd(bar.get(i));
            hisData.setDea(dea.get(i));
            hisData.setDif(dif.get(i));

            hisData.setD(d.get(i));
            hisData.setK(k.get(i));
            hisData.setJ(j.get(i));

            amountVol += hisData.getVol();
            hisData.setAmountVol(amountVol);
            if (i > 0) {
                double total = hisData.getVol() * hisData.getClose() + list.get(i - 1).getTotal();
                hisData.setTotal(total);
                double avePrice = total / amountVol;
                hisData.setAvePrice(avePrice);
            } else if (lastData != null) {
                double total = hisData.getVol() * hisData.getClose() + lastData.getTotal();
                hisData.setTotal(total);
                double avePrice = total / amountVol;
                hisData.setAvePrice(avePrice);
            } else {
                hisData.setAmountVol(hisData.getVol());
                hisData.setAvePrice(hisData.getClose());
                hisData.setTotal(hisData.getAmountVol() * hisData.getAvePrice());
            }
        }

        return list;
    }

    /**
     * calculate MA value, return a double list
     *
     * @param dayCount for example: 5, 10, 20, 30
     */
    private static List<Double> calculateMA(int dayCount, List<HisData> data) {
        List<Double> result = new ArrayList<>(data.size());
        for (int i = 0, len = data.size(); i < len; i++) {
            if (i < dayCount - 1) {
                result.add(Double.NaN);
                continue;
            }
            double sum = 0;
            for (int j = 0; j < dayCount; j++) {
                sum += data.get(i - j).getClose();
            }
            result.add(sum / (dayCount));
        }
        return result;
    }

    public List<HisData> getDatas() {
        return mData;
    }


    private void add(HisData hisData) {
        mData.add(hisData);
    }

    public void clear() {
        mData.clear();
    }

    private List<Double> calculateVolMa(List<HisData> input, int period) {
        final MovingAverage ma = new MovingAverage(period);

        final ArrayList<Double> output = new ArrayList<>(input.size());

        for (HisData item : input) {
            ma.push(item.getVol());
            output.add(ma.getCurrent());
        }

        return output;
    }

    /**
     * 添加新的历史数据到最前面，重新计算各指标
     *
     * @param hisDatas 添加的列表
     */
    public synchronized void addDatasFirst(List<HisData> hisDatas) {
        mData.addAll(0, hisDatas);
        calculateHisData();
    }

    public synchronized void addNewData(HisData newData) {
        Log.d("leon", "addNewData: on thread:"+Thread.currentThread().getName());
        if (newData == null) {
            return;
        }
        mData.add(newData);
        calculateLastHisData(newData, mData);
    }

    /**
     * according to the history data list, calculate a new data
     */
    private HisData calculateLastHisData(HisData newData, List<HisData> hisDatas) {

        HisData lastData = hisDatas.get(hisDatas.size() - 1);
        double amountVol = lastData.getAmountVol();

        newData.setMa5(calculateLastMA(5, hisDatas));
        newData.setMa10(calculateLastMA(10, hisDatas));
//        newData.setMa20(calculateLastMA(20, hisDatas));
        newData.setMa30(calculateLastMA(30, hisDatas));

        EMA ema = new EMA();
        newData.setEma5(ema.calculateLastEMA5(hisDatas));
        newData.setEma10(ema.calculateLastEMA10(hisDatas));
        newData.setEma30(ema.calculateLastEMA30(hisDatas));

        BOLL boll = new BOLL();
        boll.calculateBOLL(hisDatas, 13, 2);
        List<Double> ups = boll.getUps();
        newData.setBollUP(ups.get(ups.size()-1));
        List<Double> mbs = boll.getMbs();
        newData.setBollMB(mbs.get(mbs.size()-1));
        List<Double> dns = boll.getDns();
        newData.setBollDN(dns.get(dns.size()-1));

        newData.setVolMa5(calculateLastVolMA(5,hisDatas));
        newData.setVolMa10(calculateLastVolMA(10,hisDatas));

        amountVol += newData.getVol();
        newData.setAmountVol(amountVol);

        double total = newData.getVol() * newData.getClose() + lastData.getTotal();
        newData.setTotal(total);
        double avePrice = total / amountVol;
        newData.setAvePrice(avePrice);

        MACD macd = new MACD(hisDatas);
        List<Double> bar = macd.getMACD();
        newData.setMacd(bar.get(bar.size() - 1));
        List<Double> dea = macd.getDEA();
        newData.setDea(dea.get(dea.size() - 1));
        List<Double> dif = macd.getDIF();
        newData.setDif(dif.get(dif.size() - 1));

        KDJ kdj = new KDJ(hisDatas);
        ArrayList<Double> d = kdj.getD();
        newData.setD(d.get(d.size() - 1));
        ArrayList<Double> k = kdj.getK();
        newData.setK(k.get(k.size() - 1));
        ArrayList<Double> j = kdj.getJ();
        newData.setJ(j.get(j.size() - 1));

        return newData;
    }

    /**
     * calculate last MA value, return a double value
     */
    private  double calculateLastMA(int dayCount, List<HisData> data) {
        double result = Double.NaN;

        if (data.size() < dayCount) {
            return result;
        }

        double sum = 0;
        for (int j = 0; j < dayCount; j++) {
            sum += data.get(data.size()-1 - j).getClose();
        }
        result = (+(sum / dayCount));
        return result;
    }

    private  double calculateLastVolMA(int dayCount, List<HisData> data) {
        double result = Double.NaN;
        if (data.size() < dayCount) {
            return result;
        }

        double sum = 0;
        for (int j = 0; j < dayCount; j++) {
            sum += data.get(data.size()-1 - j).getVol();
        }
        result = (+(sum / dayCount));
        return result;
    }

}

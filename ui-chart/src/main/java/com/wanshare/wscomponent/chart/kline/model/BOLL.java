package com.wanshare.wscomponent.chart.kline.model;

import com.wanshare.wscomponent.chart.kline.model.bean.HisData;

import java.util.ArrayList;
import java.util.List;

/**
 * BOLL指标
 * </br>
 * Date: 2018/8/2 15:11
 *
 * @author hemin
 */
public class BOLL {
    private List<Double> ups;
    private List<Double> mbs;
    private List<Double> dns;

    public  void calculateBOLL(List<HisData> quotesList, int period, int k) {
        if (quotesList == null || quotesList.isEmpty()) return;
        if (period < 0 || period > quotesList.size() - 1) return;

        ups = new ArrayList<>();
        mbs = new ArrayList<>();
        dns = new ArrayList<>();

        double mb;//上轨线
        double up;//中轨线
        double dn;//下轨线

        //n日
        double sum = 0;
        //n-1日
        double sum2 = 0;
        for (int i = 0; i < quotesList.size(); i++) {
            HisData quotes = quotesList.get(i);
            sum += quotes.getClose();
            sum2 += quotes.getClose();
            if (i > period - 1) {
                sum -= quotesList.get(i - period).getClose();
            }
            if (i > period - 2) {
                sum2 -= quotesList.get(i - period + 1).getClose();
            }

            //这个范围不计算，在View上的反应就是不显示这个范围的boll线
            if (i < period - 1) {
//                quotes.setBollUP(Double.NaN);
//                quotes.setBollMB(Double.NaN);
//                quotes.setBollDN(Double.NaN);
                ups.add(Double.NaN);
                mbs.add(Double.NaN);
                dns.add(Double.NaN);
                continue;
            }

            //n日MA
            double ma = sum / period;
            //n-1日MA
            double ma2 = sum2 / (period - 1);
            double md = 0;
            for (int j = i + 1 - period; j <= i; j++) {
                //n-1日
                md += Math.pow(quotesList.get(j).getClose() - ma, 2);
            }
            md = Math.sqrt(md / period);
            //(n－1）日的MA
            mb = ma2;
            up = mb + k * md;
            dn = mb - k * md;

//            quotes.setBollMB(mb);
//            quotes.setBollUP(up);
//            quotes.setBollDN(dn);

            ups.add(up);
            mbs.add(mb);
            dns.add(dn);
        }
    }

    public List<Double> getUps() {
        return ups;
    }

    public List<Double> getMbs() {
        return mbs;
    }

    public List<Double> getDns() {
        return dns;
    }
}

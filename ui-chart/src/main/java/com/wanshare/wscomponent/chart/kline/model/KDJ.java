package com.wanshare.wscomponent.chart.kline.model;

import com.wanshare.wscomponent.chart.kline.model.bean.HisData;

import java.util.ArrayList;
import java.util.List;


public class KDJ {
    private ArrayList<Double> Ks;
    private ArrayList<Double> Ds;
    private ArrayList<Double> Js;
    private ArrayList<Double> rsv;

    private RSV mRSV;

    public KDJ(List<HisData> OHLCData) {
        Ks = new ArrayList<Double>();
        Ds = new ArrayList<Double>();
        Js = new ArrayList<Double>();

        ArrayList<Double> ks = new ArrayList<Double>();
        ArrayList<Double> ds = new ArrayList<Double>();
        ArrayList<Double> js = new ArrayList<Double>();

        int m = 14;
        mRSV = new RSV(OHLCData, m);
        double k = 50.0;
        double d = 50.0;
        double j = 0.0;
        double rSV = 0.0;

        if (OHLCData != null && OHLCData.size() > 0) {
            rsv = (ArrayList<Double>) mRSV.getRSV();
            for(int i=0;i<OHLCData.size();i++){
                if (i < m-1) {
                    Ks.add(Double.NaN);
                    Ds.add(Double.NaN);
                    Js.add(Double.NaN);
                    continue;
                }

                rSV = rsv.get(i);
                k = (k * 2 + rSV) / 3;
                d = (d * 2 + k) / 3;
                j = 3 * k - 2 * d;
                Ks.add(k);
                Ds.add(d);
                Js.add(j);

                System.out.println("i:"+(i)+" k:"+Ks.get(i)+" d:"+Ds.get(i)+" j:"+Js.get(i));
            }
        }
    }

    public ArrayList<Double> getK() {
        return Ks;
    }

    public ArrayList<Double> getD() {
        return Ds;
    }

    public ArrayList<Double> getJ() {
        return Js;
    }
}


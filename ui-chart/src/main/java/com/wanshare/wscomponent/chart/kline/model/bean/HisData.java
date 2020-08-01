package com.wanshare.wscomponent.chart.kline.model.bean;

/**
 * chart data model
 */

public class HisData {

    private double close;
    private double high;
    private double low;
    private double open;
    private double vol;
    private double volMa5;
    private double volMa10;
    private long date;
    private double amountVol;
    private double avePrice;
    private double total;
    private double maSum;
    private double ma5;
    private double ma10;
    private double ma20;
    private double ma30;

    private double ema5;
    private double ema10;
    private double ema30;

    // BOLL
    // 上轨线
    private double bollUP;
    // 中轨线
    private double bollMB;
    // 下轨线
    private double bollDN;

    private double dif;
    private double dea;
    private double macd;

    private double k;
    private double d;
    private double j;

    public double getDif() {
        return dif;
    }

    public HisData() {
    }

    public HisData(double open,double close, double high, double low,  long vol, long date) {
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.vol = vol;
        this.date = date;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }


    public double getAvePrice() {
        return avePrice;
    }

    public void setAvePrice(double avePrice) {
        this.avePrice = avePrice;
    }


    public double getAmountVol() {
        return amountVol;
    }

    public void setAmountVol(double amountVol) {
        this.amountVol = amountVol;
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getMa5() {
        return ma5;
    }

    public void setMa5(double ma5) {
        this.ma5 = ma5;
    }

    public double getMa10() {
        return ma10;
    }

    public void setMa10(double ma10) {
        this.ma10 = ma10;
    }

    public double getMa20() {
        return ma20;
    }

    public void setMa20(double ma20) {
        this.ma20 = ma20;
    }

    public double getMa30() {
        return ma30;
    }

    public void setMa30(double ma30) {
        this.ma30 = ma30;
    }


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HisData data = (HisData) o;

        return date == data.date;
    }

    @Override
    public int hashCode() {
        return (int) (date ^ (date >>> 32));
    }

    public double getMaSum() {
        return maSum;
    }

    public void setMaSum(double maSum) {
        this.maSum = maSum;
    }

    public void setDif(double dif) {
        this.dif = dif;
    }

    public double getDea() {
        return dea;
    }

    public void setDea(double dea) {
        this.dea = dea;
    }

    public double getMacd() {
        return macd;
    }

    public void setMacd(double macd) {
        this.macd = macd;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getJ() {
        return j;
    }

    public void setJ(double j) {
        this.j = j;
    }

    public double getBollUP() {
        return bollUP;
    }

    public void setBollUP(double bollUP) {
        this.bollUP = bollUP;
    }

    public double getBollMB() {
        return bollMB;
    }

    public void setBollMB(double bollMB) {
        this.bollMB = bollMB;
    }

    public double getBollDN() {
        return bollDN;
    }

    public void setBollDN(double bollDN) {
        this.bollDN = bollDN;
    }

    public double getVolMa5() {
        return volMa5;
    }

    public void setVolMa5(double volMa5) {
        this.volMa5 = volMa5;
    }

    public double getVolMa10() {
        return volMa10;
    }

    public void setVolMa10(double volMa10) {
        this.volMa10 = volMa10;
    }

    /**
     * 涨跌额
     */
    public double getChangeAmount(){
        return close-open;
    }

    /**
     * 涨跌幅
     * @return
     */
    public double getChangeRate(){
        if(open==0){
            return Double.NaN;
        }
        return (close-open)/open;
    }

    public double getEma5() {
        return ema5;
    }

    public void setEma5(double ema5) {
        this.ema5 = ema5;
    }

    public double getEma10() {
        return ema10;
    }

    public void setEma10(double ema10) {
        this.ema10 = ema10;
    }

    public double getEma30() {
        return ema30;
    }

    public void setEma30(double ema30) {
        this.ema30 = ema30;
    }

    @Override
    public String toString() {
        return "HisData{" +
                "close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", open=" + open +
                ", vol=" + vol +
                ", volMa5=" + volMa5 +
                ", volMa10=" + volMa10 +
                ", date=" + date +
                ", amountVol=" + amountVol +
                ", avePrice=" + avePrice +
                ", total=" + total +
                ", maSum=" + maSum +
                ", ma5=" + ma5 +
                ", ma10=" + ma10 +
                ", ma20=" + ma20 +
                ", ma30=" + ma30 +
                ", ema5=" + ema5 +
                ", ema10=" + ema10 +
                ", ema30=" + ema30 +
                ", bollUP=" + bollUP +
                ", bollMB=" + bollMB +
                ", bollDN=" + bollDN +
                ", dif=" + dif +
                ", dea=" + dea +
                ", macd=" + macd +
                ", k=" + k +
                ", d=" + d +
                ", j=" + j +
                '}';
    }
}

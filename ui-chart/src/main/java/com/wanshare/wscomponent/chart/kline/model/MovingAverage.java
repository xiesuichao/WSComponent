//******************************************************************************
// SCICHART® Copyright SciChart Ltd. 2011-2017. All rights reserved.
//
// Web: http://www.scichart.com
// Support: support@scichart.com
// Sales:   sales@scichart.com
//
// MovingAverage.java is part of the SCICHART® Examples. Permission is hereby granted
// to modify, create derivative works, distribute and publish any part of this source
// code whether for commercial, private or personal use.
//
// The SCICHART® examples are distributed in the hope that they will be useful, but
// without any warranty. It is provided "AS IS" without warranty of any kind, either
// expressed or implied.
//******************************************************************************

package com.wanshare.wscomponent.chart.kline.model;

import com.github.mikephil.charting.data.Entry;
import com.wanshare.wscomponent.chart.kline.model.bean.HisData;

import java.util.ArrayList;
import java.util.List;

public class MovingAverage {

    private final int length;
    private int circIndex = -1;
    private boolean filled;
    private double current = Double.NaN;
    private final double oneOverLength;
    private final double[] circularBuffer;
    private double total;

    public MovingAverage(int length) {
        this.length = length;
        this.oneOverLength = 1.0 / length;
        this.circularBuffer = new double[length];
    }

    public MovingAverage update(double value) {
        double lostValue = circularBuffer[circIndex];
        circularBuffer[circIndex] = value;

        // Maintain totals for Push function
        total += value;
        total -= lostValue;

        // If not yet filled, just return. Current value should be double.NaN
        if (!filled) {
            current = Double.NaN;
            return this;
        }

        // Compute the average
        double average = 0.0;
        for (double aCircularBuffer : circularBuffer) {
            average += aCircularBuffer;
        }

        current = average * oneOverLength;

        return this;
    }

    public MovingAverage push(double value) {
        // Apply the circular buffer
        if (++circIndex == length) {
            circIndex = 0;
        }

        double lostValue = circularBuffer[circIndex];
        circularBuffer[circIndex] = value;

        // Compute the average
        total += value;
        total -= lostValue;

        // If not yet filled, just return. Current value should be double.NaN
        if (!filled && circIndex != length - 1) {
            current = Double.NaN;
            return this;
        } else {
            // Set a flag to indicate this is the first time the buffer has been filled
            filled = true;
        }

        current = total * oneOverLength;

        return this;
    }

    public int getLength() {
        return length;
    }

    public double getCurrent() {
        return current;
    }

    public static List<Double> movingAverage(List<Double> input, int period) {
        final MovingAverage ma = new MovingAverage(period);

        final ArrayList<Double> output = new ArrayList<>(input.size());

        for (Double item : input) {
            ma.push(item);
            output.add(ma.getCurrent());
        }

        return output;
    }

    public static List<Double> movingAverageVol(List<HisData> input, int period) {
        final MovingAverage ma = new MovingAverage(period);

        final ArrayList<Double> output = new ArrayList<>(input.size());

        for (HisData item : input) {
            ma.push(item.getVol());
            output.add(ma.getCurrent());
        }

        return output;
    }

    public static List<Entry> movingAverageEntry(List<Double> input, int period) {
        final MovingAverage ma = new MovingAverage(period);

        final ArrayList<Entry> output = new ArrayList<>(input.size());

        Double item = null;
        for(int i=0;i<input.size();i++){
            item = input.get(i);
            ma.push(item);
            output.add(new Entry(i, (float) ma.getCurrent()) );
        }

        return output;
    }

    public static List<Double> rsi(List<HisData> input, int period) {
        final MovingAverage averageGain = new MovingAverage(period);
        final MovingAverage averageLoss = new MovingAverage(period);

        final ArrayList<Double> output = new ArrayList<>(input.size());

        // skip first point
        HisData previousBar = input.get(0);
        output.add(Double.NaN);

        for (int i = 1, size = input.size(); i < size; i++) {
            final HisData priceBar = input.get(i);

            final double gain = priceBar.getClose() > previousBar.getClose() ? priceBar.getClose() - previousBar.getClose() : 0.0;
            final double loss = previousBar.getClose() > priceBar.getClose() ? previousBar.getClose() - priceBar.getClose() : 0.0;

            averageGain.push(gain);
            averageLoss.push(loss);

            final double relativeStrength = Double.isNaN(averageGain.getCurrent()) || Double.isNaN(averageLoss.getCurrent()) ? Double.NaN : averageGain.getCurrent() / averageLoss.getCurrent();

            output.add(Double.isNaN(relativeStrength) ? Double.NaN : 100.0 - (100.0 / (1.0 + relativeStrength)));

            previousBar = priceBar;
        }

        return output;
    }

    public static MacdPoints macd(List<Double> input, int slow, int fast, int signal) {
        final MovingAverage maSlow = new MovingAverage(slow);
        final MovingAverage maFast = new MovingAverage(fast);
        final MovingAverage maSignal = new MovingAverage(signal);

        final MacdPoints output = new MacdPoints();

        for (Double item : input) {
            final double macd = maSlow.push(item).getCurrent() - maFast.push(item).getCurrent();
            final double signalLine = Double.isNaN(macd) ? Double.NaN : maSignal.push(macd).getCurrent();
            final double divergence = Double.isNaN(macd) || Double.isNaN(signalLine) ? Double.NaN : macd - signalLine;

            output.addPoint(macd, signalLine, divergence);
        }

        return output;
    }

    public static final class MacdPoints {
        public final List<Double> macdValues = new ArrayList<>();
        public final List<Double> signalValues = new ArrayList<>();
        public final List<Double> divergenceValues = new ArrayList<>();

        public void addPoint(double macd, double signal, double divergence) {
            macdValues.add(macd);
            signalValues.add(signal);
            divergenceValues.add(divergence);
        }
    }

    /**
     * BOLL(n)计算公式：
     * MA=n日内的收盘价之和÷n。
     * MD=（n-1）日的平方根（C－MA）的两次方之和除以n
     * MB=（n－1）日的MA
     * UP=MB+k×MD
     * DN=MB－k×MD
     * K为参数，可根据股票的特性来做相应的调整，一般默认为2
     *
     * @param quotesList 数据集合
     * @param period 周期，一般为26
     * @param k 参数，可根据股票的特性来做相应的调整，一般默认为2
     */
    public static void calculateBOLL(List<HisData> quotesList, int period, int k) {
        if (quotesList == null || quotesList.isEmpty()) return;
        if (period < 0 || period > quotesList.size() - 1) return;

        double mb;//上轨线
        double up;//中轨线
        double dn;//下轨线

        //n日
        double sum = 0;
        //n-1日
        double sum2=0;
        for (int i = 0; i < quotesList.size(); i++) {
            HisData quotes = quotesList.get(i);
            sum += quotes.getClose();
            sum2+=quotes.getClose();
            if (i > period - 1)
                sum -= quotesList.get(i - period).getClose();
            if(i>period-2)
                sum2 -= quotesList.get(i - period+1).getClose();

            //这个范围不计算，在View上的反应就是不显示这个范围的boll线
            if (i < period - 1)
                continue;

            //n日MA
            double ma = sum / period;
            //n-1日MA
            double ma2=sum2/(period-1);
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

            quotes.setBollMB(mb);
            quotes.setBollUP(up);
            quotes.setBollDN(dn);
        }
    }

    public static final class Quotes{

        public double c;
        public double mb;
        public double up;
        public double dn;

        @Override
        public String toString() {
            return "{" +
                    "c=" + c +
                    ", mb=" + mb +
                    ", up=" + up +
                    ", dn=" + dn +
                    '}';
        }
    }



}

package com.guoguang.dksq.entity;

/**
 * Created by tk on 2015/11/11.
 */
public class BaseRate {
    int MinTerm;
    int MaxTerm;
    String RateValue;

    public int getMaxTerm() {
        return MaxTerm;
    }

    public void setMaxTerm(int maxTerm) {
        MaxTerm = maxTerm;
    }

    public int getMinTerm() {
        return MinTerm;
    }

    public void setMinTerm(int minTerm) {
        MinTerm = minTerm;
    }

    public String getRateValue() {
        return RateValue;
    }

    public void setRateValue(String rateValue) {
        RateValue = rateValue;
    }
}

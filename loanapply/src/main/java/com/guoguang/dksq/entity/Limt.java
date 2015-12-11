package com.guoguang.dksq.entity;

/**
 * Created by tk on 2015/11/24.
 */
public class Limt {
    String SerialNo ,
    CustomerName,
    BusinessType,
    BusinessSum,
    Balance;

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public String getBusinessSum() {
        return BusinessSum;
    }

    public void setBusinessSum(String businessSum) {
        BusinessSum = businessSum;
    }

    public String getBusinessType() {
        return BusinessType;
    }

    public void setBusinessType(String businessType) {
        BusinessType = businessType;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(String serialNo) {
        SerialNo = serialNo;
    }
}

package com.guoguang.dkcx.entity;

/**
 * Created by tk on 2015/10/29.
 */
public class LoanInfoEntity {

    String SerialNo;//贷款流水号
    String ArtificialNo;//
    String CustomerID;//客户编号
    String CustomerName;//客户信息
    String CertID;//证件信息
    String BusinessType;//业务类型 code
    String BusinessSum;//3000000  金额
    String Balance;//0  可用余额
    String PutOutDate;//2012/05/28 合同起始日
    String Maturity;//2013/05/28 合同到期日
    String TermMonth;//12  期限
    String BusinessRate;//6.564 执行年利率
    String VouchType;//0205010 主要担保方式
    String REPAYMENTMETHOD;//RPT000040 付款方式
    String DefaultPayDate;//20 默认还款日

    public String getArtificialNo() {
        return ArtificialNo;
    }

    public void setArtificialNo(String artificialNo) {
        ArtificialNo = artificialNo;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public String getBusinessRate() {
        return BusinessRate;
    }

    public void setBusinessRate(String businessRate) {
        BusinessRate = businessRate;
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

    public String getCertID() {
        return CertID;
    }

    public void setCertID(String certID) {
        CertID = certID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getDefaultPayDate() {
        return DefaultPayDate;
    }

    public void setDefaultPayDate(String defaultPayDate) {
        DefaultPayDate = defaultPayDate;
    }

    public String getMaturity() {
        return Maturity;
    }

    public void setMaturity(String maturity) {
        Maturity = maturity;
    }

    public String getPutOutDate() {
        return PutOutDate;
    }

    public void setPutOutDate(String putOutDate) {
        PutOutDate = putOutDate;
    }

    public String getREPAYMENTMETHOD() {
        return REPAYMENTMETHOD;
    }

    public void setREPAYMENTMETHOD(String REPAYMENTMETHOD) {
        this.REPAYMENTMETHOD = REPAYMENTMETHOD;
    }

    public String getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(String serialNo) {
        SerialNo = serialNo;
    }

    public String getTermMonth() {
        return TermMonth;
    }

    public void setTermMonth(String termMonth) {
        TermMonth = termMonth;
    }

    public String getVouchType() {
        return VouchType;
    }

    public void setVouchType(String vouchType) {
        VouchType = vouchType;
    }
}

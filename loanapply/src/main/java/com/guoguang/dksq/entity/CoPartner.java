package com.guoguang.dksq.entity;

public class CoPartner {
	String ProjectName,//项目名称
	CustomerID, //合作商id
	CustomerName,//合作商名称
	
	SerialNo,//合同协议号
	BusinessType,//业务类型
	BusinessCurrency,//币种
	BusinessSum,//总额度
	Balance ;//已用额度
	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
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

	public String getSerialNo() {
		return SerialNo;
	}

	public void setSerialNo(String serialNo) {
		SerialNo = serialNo;
	}

	public String getBusinessType() {
		return BusinessType;
	}

	public void setBusinessType(String businessType) {
		BusinessType = businessType;
	}

	public String getBusinessCurrency() {
		return BusinessCurrency;
	}

	public void setBusinessCurrency(String businessCurrency) {
		BusinessCurrency = businessCurrency;
	}

	public String getBusinessSum() {
		return BusinessSum;
	}

	public void setBusinessSum(String businessSum) {
		BusinessSum = businessSum;
	}

	public String getBalance() {
		return Balance;
	}

	public void setBalance(String balance) {
		Balance = balance;
	}
	
}

package com.guoguang.dksq.database;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table LOAN_BUSINESS.
 */
public class LoanBusiness {

    private Long id;
    private String SeqNo;
    private String ApplyType;
    private String LineNo="";
    private String SerialNo="";
    private String RelativeSerialNo="";
    private String CustomerID="";
    private String CustomerName="";
    private String OccurDate="";
    private String BusinessType="";
    private String OccurType="";
    private String CreditAggreement="";
    private String ContractFlag="";
    private String ThirdParty3="";
    private String CooperatetEntName="";
    private String DESCRIBE1="";
    private String OperationMode="";
    private String BusinessCurrency="";
    private String BusinessSum="";
    private String TermMonth="";
    private String TermDay="";
    private String RateMode="";
    private String BaseRateType="";
    private String BaseRate="";
    private String RateFloatType="";
    private String RateFloat="";
    private String BusinessRate="";
    private String AdjustRateType="";
    private String DESIGNATEDATE="";
    private String CYCLEMONTHS="";
    private String MAINREPAYMENTMETHOD="";
    private String REPAYMENTMETHOD="";
    private String PayCyc="";
    private String GainCyc="";
    private String GainAmount="";
    private String HoldCorpus="";
    private String AMORTIZETERM="";
    private String GRACEPERIODDATE="";
    private String PayAccountName="";
    private String PayAccountNo="";
    private String DefaultPayAcctNoType="";
    private String IncomeOrgID="";
    private String IncomeOrgName="";
    private String PaymentMode="";
    private String HouseNum="";
    private String CurrentHouseCount="";
    private String MfeeSum="";
    private String PutOutType="";
    private String ThirdParty1="";
    private String ThirdPartyID1="";
    private String ThirdParty2="";
    private String ConstructionArea="";
    private String ThirdPartyID2="";
    private String ThirdPartyAdd1="";
    private String ThirdPartyZIP1="";
    private String ThirdPartyAdd2="";
    private String ThirdPartyZIP2="";
    private String ThirdPartyAdd3="";
    private String ThirdPartyZIP3="";
    private String ISSMALLFlow="";
    private String VouchType="";
    private String VouchTypeName="";
    private String Flag1="";
    private String FARMINGTYPE="";
    private String IsLowRisk="";
    private String Purpose="";
    private String Attribute01="";
    private String Attribute02="";
    private String Attribute03="";
    private String Attribute04="";
    private String Attribute05="";
    private String Attribute06="";
    private String Attribute07="";
    private String Attribute08="";
    private String Attribute09="";
    private String BusinessSource="";
    private String BusinessSourceName="";
    private String OtherCondition="";
    private String Remark="";
    private String GuarantyFlag="";
    private String LNGOTimes="";
    private String GOLNTimes="";
    private String DRTimes="";
    private String OperateUserID="";
    private String OperateOrgID="";
    private String OperateOrgName="";
    private String OperateUserName="";
    private String OperateUserID1="";
    private String OperateUserName1="";
    private String InputUserID="";
    private String InputUserName="";
    private String InputOrgID="";
    private String InputOrgName="";
    private String InputDate="";
    private String UpdateDate="";
    private String PigeonholeDate="";
    private String TempSaveFlag="";
    private String PhaseOpinion="";

    public LoanBusiness() {
    }

    public LoanBusiness(Long id) {
        this.id = id;
    }

    public LoanBusiness(Long id, String SeqNo, String ApplyType, String LineNo, String SerialNo, String RelativeSerialNo, String CustomerID, String CustomerName, String OccurDate, String BusinessType, String OccurType, String CreditAggreement, String ContractFlag, String ThirdParty3, String CooperatetEntName, String DESCRIBE1, String OperationMode, String BusinessCurrency, String BusinessSum, String TermMonth, String TermDay, String RateMode, String BaseRateType, String BaseRate, String RateFloatType, String RateFloat, String BusinessRate, String AdjustRateType, String DESIGNATEDATE, String CYCLEMONTHS, String MAINREPAYMENTMETHOD, String REPAYMENTMETHOD, String PayCyc, String GainCyc, String GainAmount, String HoldCorpus, String AMORTIZETERM, String GRACEPERIODDATE, String PayAccountName, String PayAccountNo, String DefaultPayAcctNoType, String IncomeOrgID, String IncomeOrgName, String PaymentMode, String HouseNum, String CurrentHouseCount, String MfeeSum, String PutOutType, String ThirdParty1, String ThirdPartyID1, String ThirdParty2, String ConstructionArea, String ThirdPartyID2, String ThirdPartyAdd1, String ThirdPartyZIP1, String ThirdPartyAdd2, String ThirdPartyZIP2, String ThirdPartyAdd3, String ThirdPartyZIP3, String ISSMALLFlow, String VouchType, String VouchTypeName, String Flag1, String FARMINGTYPE, String IsLowRisk, String Purpose, String Attribute01, String Attribute02, String Attribute03, String Attribute04, String Attribute05, String Attribute06, String Attribute07, String Attribute08, String Attribute09, String BusinessSource, String BusinessSourceName, String OtherCondition, String Remark, String GuarantyFlag, String LNGOTimes, String GOLNTimes, String DRTimes, String OperateUserID, String OperateOrgID, String OperateOrgName, String OperateUserName, String OperateUserID1, String OperateUserName1, String InputUserID, String InputUserName, String InputOrgID, String InputOrgName, String InputDate, String UpdateDate, String PigeonholeDate, String TempSaveFlag, String PhaseOpinion) {
        this.id = id;
        this.SeqNo = SeqNo;
        this.ApplyType = ApplyType;
        this.LineNo = LineNo;
        this.SerialNo = SerialNo;
        this.RelativeSerialNo = RelativeSerialNo;
        this.CustomerID = CustomerID;
        this.CustomerName = CustomerName;
        this.OccurDate = OccurDate;
        this.BusinessType = BusinessType;
        this.OccurType = OccurType;
        this.CreditAggreement = CreditAggreement;
        this.ContractFlag = ContractFlag;
        this.ThirdParty3 = ThirdParty3;
        this.CooperatetEntName = CooperatetEntName;
        this.DESCRIBE1 = DESCRIBE1;
        this.OperationMode = OperationMode;
        this.BusinessCurrency = BusinessCurrency;
        this.BusinessSum = BusinessSum;
        this.TermMonth = TermMonth;
        this.TermDay = TermDay;
        this.RateMode = RateMode;
        this.BaseRateType = BaseRateType;
        this.BaseRate = BaseRate;
        this.RateFloatType = RateFloatType;
        this.RateFloat = RateFloat;
        this.BusinessRate = BusinessRate;
        this.AdjustRateType = AdjustRateType;
        this.DESIGNATEDATE = DESIGNATEDATE;
        this.CYCLEMONTHS = CYCLEMONTHS;
        this.MAINREPAYMENTMETHOD = MAINREPAYMENTMETHOD;
        this.REPAYMENTMETHOD = REPAYMENTMETHOD;
        this.PayCyc = PayCyc;
        this.GainCyc = GainCyc;
        this.GainAmount = GainAmount;
        this.HoldCorpus = HoldCorpus;
        this.AMORTIZETERM = AMORTIZETERM;
        this.GRACEPERIODDATE = GRACEPERIODDATE;
        this.PayAccountName = PayAccountName;
        this.PayAccountNo = PayAccountNo;
        this.DefaultPayAcctNoType = DefaultPayAcctNoType;
        this.IncomeOrgID = IncomeOrgID;
        this.IncomeOrgName = IncomeOrgName;
        this.PaymentMode = PaymentMode;
        this.HouseNum = HouseNum;
        this.CurrentHouseCount = CurrentHouseCount;
        this.MfeeSum = MfeeSum;
        this.PutOutType = PutOutType;
        this.ThirdParty1 = ThirdParty1;
        this.ThirdPartyID1 = ThirdPartyID1;
        this.ThirdParty2 = ThirdParty2;
        this.ConstructionArea = ConstructionArea;
        this.ThirdPartyID2 = ThirdPartyID2;
        this.ThirdPartyAdd1 = ThirdPartyAdd1;
        this.ThirdPartyZIP1 = ThirdPartyZIP1;
        this.ThirdPartyAdd2 = ThirdPartyAdd2;
        this.ThirdPartyZIP2 = ThirdPartyZIP2;
        this.ThirdPartyAdd3 = ThirdPartyAdd3;
        this.ThirdPartyZIP3 = ThirdPartyZIP3;
        this.ISSMALLFlow = ISSMALLFlow;
        this.VouchType = VouchType;
        this.VouchTypeName = VouchTypeName;
        this.Flag1 = Flag1;
        this.FARMINGTYPE = FARMINGTYPE;
        this.IsLowRisk = IsLowRisk;
        this.Purpose = Purpose;
        this.Attribute01 = Attribute01;
        this.Attribute02 = Attribute02;
        this.Attribute03 = Attribute03;
        this.Attribute04 = Attribute04;
        this.Attribute05 = Attribute05;
        this.Attribute06 = Attribute06;
        this.Attribute07 = Attribute07;
        this.Attribute08 = Attribute08;
        this.Attribute09 = Attribute09;
        this.BusinessSource = BusinessSource;
        this.BusinessSourceName = BusinessSourceName;
        this.OtherCondition = OtherCondition;
        this.Remark = Remark;
        this.GuarantyFlag = GuarantyFlag;
        this.LNGOTimes = LNGOTimes;
        this.GOLNTimes = GOLNTimes;
        this.DRTimes = DRTimes;
        this.OperateUserID = OperateUserID;
        this.OperateOrgID = OperateOrgID;
        this.OperateOrgName = OperateOrgName;
        this.OperateUserName = OperateUserName;
        this.OperateUserID1 = OperateUserID1;
        this.OperateUserName1 = OperateUserName1;
        this.InputUserID = InputUserID;
        this.InputUserName = InputUserName;
        this.InputOrgID = InputOrgID;
        this.InputOrgName = InputOrgName;
        this.InputDate = InputDate;
        this.UpdateDate = UpdateDate;
        this.PigeonholeDate = PigeonholeDate;
        this.TempSaveFlag = TempSaveFlag;
        this.PhaseOpinion = PhaseOpinion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeqNo() {
        return SeqNo;
    }

    public void setSeqNo(String SeqNo) {
        this.SeqNo = SeqNo;
    }

    public String getApplyType() {
        return ApplyType;
    }

    public void setApplyType(String ApplyType) {
        this.ApplyType = ApplyType;
    }

    public String getLineNo() {
        return LineNo;
    }

    public void setLineNo(String LineNo) {
        this.LineNo = LineNo;
    }

    public String getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(String SerialNo) {
        this.SerialNo = SerialNo;
    }

    public String getRelativeSerialNo() {
        return RelativeSerialNo;
    }

    public void setRelativeSerialNo(String RelativeSerialNo) {
        this.RelativeSerialNo = RelativeSerialNo;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getOccurDate() {
        return OccurDate;
    }

    public void setOccurDate(String OccurDate) {
        this.OccurDate = OccurDate;
    }

    public String getBusinessType() {
        return BusinessType;
    }

    public void setBusinessType(String BusinessType) {
        this.BusinessType = BusinessType;
    }

    public String getOccurType() {
        return OccurType;
    }

    public void setOccurType(String OccurType) {
        this.OccurType = OccurType;
    }

    public String getCreditAggreement() {
        return CreditAggreement;
    }

    public void setCreditAggreement(String CreditAggreement) {
        this.CreditAggreement = CreditAggreement;
    }

    public String getContractFlag() {
        return ContractFlag;
    }

    public void setContractFlag(String ContractFlag) {
        this.ContractFlag = ContractFlag;
    }

    public String getThirdParty3() {
        return ThirdParty3;
    }

    public void setThirdParty3(String ThirdParty3) {
        this.ThirdParty3 = ThirdParty3;
    }

    public String getCooperatetEntName() {
        return CooperatetEntName;
    }

    public void setCooperatetEntName(String CooperatetEntName) {
        this.CooperatetEntName = CooperatetEntName;
    }

    public String getDESCRIBE1() {
        return DESCRIBE1;
    }

    public void setDESCRIBE1(String DESCRIBE1) {
        this.DESCRIBE1 = DESCRIBE1;
    }

    public String getOperationMode() {
        return OperationMode;
    }

    public void setOperationMode(String OperationMode) {
        this.OperationMode = OperationMode;
    }

    public String getBusinessCurrency() {
        return BusinessCurrency;
    }

    public void setBusinessCurrency(String BusinessCurrency) {
        this.BusinessCurrency = BusinessCurrency;
    }

    public String getBusinessSum() {
        return BusinessSum;
    }

    public void setBusinessSum(String BusinessSum) {
        this.BusinessSum = BusinessSum;
    }

    public String getTermMonth() {
        return TermMonth;
    }

    public void setTermMonth(String TermMonth) {
        this.TermMonth = TermMonth;
    }

    public String getTermDay() {
        return TermDay;
    }

    public void setTermDay(String TermDay) {
        this.TermDay = TermDay;
    }

    public String getRateMode() {
        return RateMode;
    }

    public void setRateMode(String RateMode) {
        this.RateMode = RateMode;
    }

    public String getBaseRateType() {
        return BaseRateType;
    }

    public void setBaseRateType(String BaseRateType) {
        this.BaseRateType = BaseRateType;
    }

    public String getBaseRate() {
        return BaseRate;
    }

    public void setBaseRate(String BaseRate) {
        this.BaseRate = BaseRate;
    }

    public String getRateFloatType() {
        return RateFloatType;
    }

    public void setRateFloatType(String RateFloatType) {
        this.RateFloatType = RateFloatType;
    }

    public String getRateFloat() {
        return RateFloat;
    }

    public void setRateFloat(String RateFloat) {
        this.RateFloat = RateFloat;
    }

    public String getBusinessRate() {
        return BusinessRate;
    }

    public void setBusinessRate(String BusinessRate) {
        this.BusinessRate = BusinessRate;
    }

    public String getAdjustRateType() {
        return AdjustRateType;
    }

    public void setAdjustRateType(String AdjustRateType) {
        this.AdjustRateType = AdjustRateType;
    }

    public String getDESIGNATEDATE() {
        return DESIGNATEDATE;
    }

    public void setDESIGNATEDATE(String DESIGNATEDATE) {
        this.DESIGNATEDATE = DESIGNATEDATE;
    }

    public String getCYCLEMONTHS() {
        return CYCLEMONTHS;
    }

    public void setCYCLEMONTHS(String CYCLEMONTHS) {
        this.CYCLEMONTHS = CYCLEMONTHS;
    }

    public String getMAINREPAYMENTMETHOD() {
        return MAINREPAYMENTMETHOD;
    }

    public void setMAINREPAYMENTMETHOD(String MAINREPAYMENTMETHOD) {
        this.MAINREPAYMENTMETHOD = MAINREPAYMENTMETHOD;
    }

    public String getREPAYMENTMETHOD() {
        return REPAYMENTMETHOD;
    }

    public void setREPAYMENTMETHOD(String REPAYMENTMETHOD) {
        this.REPAYMENTMETHOD = REPAYMENTMETHOD;
    }

    public String getPayCyc() {
        return PayCyc;
    }

    public void setPayCyc(String PayCyc) {
        this.PayCyc = PayCyc;
    }

    public String getGainCyc() {
        return GainCyc;
    }

    public void setGainCyc(String GainCyc) {
        this.GainCyc = GainCyc;
    }

    public String getGainAmount() {
        return GainAmount;
    }

    public void setGainAmount(String GainAmount) {
        this.GainAmount = GainAmount;
    }

    public String getHoldCorpus() {
        return HoldCorpus;
    }

    public void setHoldCorpus(String HoldCorpus) {
        this.HoldCorpus = HoldCorpus;
    }

    public String getAMORTIZETERM() {
        return AMORTIZETERM;
    }

    public void setAMORTIZETERM(String AMORTIZETERM) {
        this.AMORTIZETERM = AMORTIZETERM;
    }

    public String getGRACEPERIODDATE() {
        return GRACEPERIODDATE;
    }

    public void setGRACEPERIODDATE(String GRACEPERIODDATE) {
        this.GRACEPERIODDATE = GRACEPERIODDATE;
    }

    public String getPayAccountName() {
        return PayAccountName;
    }

    public void setPayAccountName(String PayAccountName) {
        this.PayAccountName = PayAccountName;
    }

    public String getPayAccountNo() {
        return PayAccountNo;
    }

    public void setPayAccountNo(String PayAccountNo) {
        this.PayAccountNo = PayAccountNo;
    }

    public String getDefaultPayAcctNoType() {
        return DefaultPayAcctNoType;
    }

    public void setDefaultPayAcctNoType(String DefaultPayAcctNoType) {
        this.DefaultPayAcctNoType = DefaultPayAcctNoType;
    }

    public String getIncomeOrgID() {
        return IncomeOrgID;
    }

    public void setIncomeOrgID(String IncomeOrgID) {
        this.IncomeOrgID = IncomeOrgID;
    }

    public String getIncomeOrgName() {
        return IncomeOrgName;
    }

    public void setIncomeOrgName(String IncomeOrgName) {
        this.IncomeOrgName = IncomeOrgName;
    }

    public String getPaymentMode() {
        return PaymentMode;
    }

    public void setPaymentMode(String PaymentMode) {
        this.PaymentMode = PaymentMode;
    }

    public String getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(String HouseNum) {
        this.HouseNum = HouseNum;
    }

    public String getCurrentHouseCount() {
        return CurrentHouseCount;
    }

    public void setCurrentHouseCount(String CurrentHouseCount) {
        this.CurrentHouseCount = CurrentHouseCount;
    }

    public String getMfeeSum() {
        return MfeeSum;
    }

    public void setMfeeSum(String MfeeSum) {
        this.MfeeSum = MfeeSum;
    }

    public String getPutOutType() {
        return PutOutType;
    }

    public void setPutOutType(String PutOutType) {
        this.PutOutType = PutOutType;
    }

    public String getThirdParty1() {
        return ThirdParty1;
    }

    public void setThirdParty1(String ThirdParty1) {
        this.ThirdParty1 = ThirdParty1;
    }

    public String getThirdPartyID1() {
        return ThirdPartyID1;
    }

    public void setThirdPartyID1(String ThirdPartyID1) {
        this.ThirdPartyID1 = ThirdPartyID1;
    }

    public String getThirdParty2() {
        return ThirdParty2;
    }

    public void setThirdParty2(String ThirdParty2) {
        this.ThirdParty2 = ThirdParty2;
    }

    public String getConstructionArea() {
        return ConstructionArea;
    }

    public void setConstructionArea(String ConstructionArea) {
        this.ConstructionArea = ConstructionArea;
    }

    public String getThirdPartyID2() {
        return ThirdPartyID2;
    }

    public void setThirdPartyID2(String ThirdPartyID2) {
        this.ThirdPartyID2 = ThirdPartyID2;
    }

    public String getThirdPartyAdd1() {
        return ThirdPartyAdd1;
    }

    public void setThirdPartyAdd1(String ThirdPartyAdd1) {
        this.ThirdPartyAdd1 = ThirdPartyAdd1;
    }

    public String getThirdPartyZIP1() {
        return ThirdPartyZIP1;
    }

    public void setThirdPartyZIP1(String ThirdPartyZIP1) {
        this.ThirdPartyZIP1 = ThirdPartyZIP1;
    }

    public String getThirdPartyAdd2() {
        return ThirdPartyAdd2;
    }

    public void setThirdPartyAdd2(String ThirdPartyAdd2) {
        this.ThirdPartyAdd2 = ThirdPartyAdd2;
    }

    public String getThirdPartyZIP2() {
        return ThirdPartyZIP2;
    }

    public void setThirdPartyZIP2(String ThirdPartyZIP2) {
        this.ThirdPartyZIP2 = ThirdPartyZIP2;
    }

    public String getThirdPartyAdd3() {
        return ThirdPartyAdd3;
    }

    public void setThirdPartyAdd3(String ThirdPartyAdd3) {
        this.ThirdPartyAdd3 = ThirdPartyAdd3;
    }

    public String getThirdPartyZIP3() {
        return ThirdPartyZIP3;
    }

    public void setThirdPartyZIP3(String ThirdPartyZIP3) {
        this.ThirdPartyZIP3 = ThirdPartyZIP3;
    }

    public String getISSMALLFlow() {
        return ISSMALLFlow;
    }

    public void setISSMALLFlow(String ISSMALLFlow) {
        this.ISSMALLFlow = ISSMALLFlow;
    }

    public String getVouchType() {
        return VouchType;
    }

    public void setVouchType(String VouchType) {
        this.VouchType = VouchType;
    }

    public String getVouchTypeName() {
        return VouchTypeName;
    }

    public void setVouchTypeName(String VouchTypeName) {
        this.VouchTypeName = VouchTypeName;
    }

    public String getFlag1() {
        return Flag1;
    }

    public void setFlag1(String Flag1) {
        this.Flag1 = Flag1;
    }

    public String getFARMINGTYPE() {
        return FARMINGTYPE;
    }

    public void setFARMINGTYPE(String FARMINGTYPE) {
        this.FARMINGTYPE = FARMINGTYPE;
    }

    public String getIsLowRisk() {
        return IsLowRisk;
    }

    public void setIsLowRisk(String IsLowRisk) {
        this.IsLowRisk = IsLowRisk;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String Purpose) {
        this.Purpose = Purpose;
    }

    public String getAttribute01() {
        return Attribute01;
    }

    public void setAttribute01(String Attribute01) {
        this.Attribute01 = Attribute01;
    }

    public String getAttribute02() {
        return Attribute02;
    }

    public void setAttribute02(String Attribute02) {
        this.Attribute02 = Attribute02;
    }

    public String getAttribute03() {
        return Attribute03;
    }

    public void setAttribute03(String Attribute03) {
        this.Attribute03 = Attribute03;
    }

    public String getAttribute04() {
        return Attribute04;
    }

    public void setAttribute04(String Attribute04) {
        this.Attribute04 = Attribute04;
    }

    public String getAttribute05() {
        return Attribute05;
    }

    public void setAttribute05(String Attribute05) {
        this.Attribute05 = Attribute05;
    }

    public String getAttribute06() {
        return Attribute06;
    }

    public void setAttribute06(String Attribute06) {
        this.Attribute06 = Attribute06;
    }

    public String getAttribute07() {
        return Attribute07;
    }

    public void setAttribute07(String Attribute07) {
        this.Attribute07 = Attribute07;
    }

    public String getAttribute08() {
        return Attribute08;
    }

    public void setAttribute08(String Attribute08) {
        this.Attribute08 = Attribute08;
    }

    public String getAttribute09() {
        return Attribute09;
    }

    public void setAttribute09(String Attribute09) {
        this.Attribute09 = Attribute09;
    }

    public String getBusinessSource() {
        return BusinessSource;
    }

    public void setBusinessSource(String BusinessSource) {
        this.BusinessSource = BusinessSource;
    }

    public String getBusinessSourceName() {
        return BusinessSourceName;
    }

    public void setBusinessSourceName(String BusinessSourceName) {
        this.BusinessSourceName = BusinessSourceName;
    }

    public String getOtherCondition() {
        return OtherCondition;
    }

    public void setOtherCondition(String OtherCondition) {
        this.OtherCondition = OtherCondition;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getGuarantyFlag() {
        return GuarantyFlag;
    }

    public void setGuarantyFlag(String GuarantyFlag) {
        this.GuarantyFlag = GuarantyFlag;
    }

    public String getLNGOTimes() {
        return LNGOTimes;
    }

    public void setLNGOTimes(String LNGOTimes) {
        this.LNGOTimes = LNGOTimes;
    }

    public String getGOLNTimes() {
        return GOLNTimes;
    }

    public void setGOLNTimes(String GOLNTimes) {
        this.GOLNTimes = GOLNTimes;
    }

    public String getDRTimes() {
        return DRTimes;
    }

    public void setDRTimes(String DRTimes) {
        this.DRTimes = DRTimes;
    }

    public String getOperateUserID() {
        return OperateUserID;
    }

    public void setOperateUserID(String OperateUserID) {
        this.OperateUserID = OperateUserID;
    }

    public String getOperateOrgID() {
        return OperateOrgID;
    }

    public void setOperateOrgID(String OperateOrgID) {
        this.OperateOrgID = OperateOrgID;
    }

    public String getOperateOrgName() {
        return OperateOrgName;
    }

    public void setOperateOrgName(String OperateOrgName) {
        this.OperateOrgName = OperateOrgName;
    }

    public String getOperateUserName() {
        return OperateUserName;
    }

    public void setOperateUserName(String OperateUserName) {
        this.OperateUserName = OperateUserName;
    }

    public String getOperateUserID1() {
        return OperateUserID1;
    }

    public void setOperateUserID1(String OperateUserID1) {
        this.OperateUserID1 = OperateUserID1;
    }

    public String getOperateUserName1() {
        return OperateUserName1;
    }

    public void setOperateUserName1(String OperateUserName1) {
        this.OperateUserName1 = OperateUserName1;
    }

    public String getInputUserID() {
        return InputUserID;
    }

    public void setInputUserID(String InputUserID) {
        this.InputUserID = InputUserID;
    }

    public String getInputUserName() {
        return InputUserName;
    }

    public void setInputUserName(String InputUserName) {
        this.InputUserName = InputUserName;
    }

    public String getInputOrgID() {
        return InputOrgID;
    }

    public void setInputOrgID(String InputOrgID) {
        this.InputOrgID = InputOrgID;
    }

    public String getInputOrgName() {
        return InputOrgName;
    }

    public void setInputOrgName(String InputOrgName) {
        this.InputOrgName = InputOrgName;
    }

    public String getInputDate() {
        return InputDate;
    }

    public void setInputDate(String InputDate) {
        this.InputDate = InputDate;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String UpdateDate) {
        this.UpdateDate = UpdateDate;
    }

    public String getPigeonholeDate() {
        return PigeonholeDate;
    }

    public void setPigeonholeDate(String PigeonholeDate) {
        this.PigeonholeDate = PigeonholeDate;
    }

    public String getTempSaveFlag() {
        return TempSaveFlag;
    }

    public void setTempSaveFlag(String TempSaveFlag) {
        this.TempSaveFlag = TempSaveFlag;
    }

    public String getPhaseOpinion() {
        return PhaseOpinion;
    }

    public void setPhaseOpinion(String PhaseOpinion) {
        this.PhaseOpinion = PhaseOpinion;
    }

}

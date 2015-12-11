package com.guoguang.khgl.entity;

import java.util.List;

public class CustomInfo {
	
	String CustomerID=""	,//	客户编号
	FullName=""	,//	姓名
	CertType=""	,//	证件类型
	CertID=""	,//	证件号码
	Sex=""	,//	性别
	Birthday=""	,//	出生日期
	Age=""	,//	年龄
	EduExperience=""	,//	最高学历
	EduRecord=""	,//	毕业学校(取得最高学历)
	GraduateYear=""	,//	毕业年份(取得最高学历)
	EduDegree=""	,//	最高学位
	SINo=""	,//	社会保险号
	IsRela=""	,//	是否我行关联方
	Staff=""	,//	是否本行员工
	CustomerLevel=""	,//	行内级别
	CreditRecordType=""	,//	信用记录类型
	Nationality=""	,//	民族
	NativePlace=""	,//	户籍地址
	PoliticalFace=""	,//	政治面貌
	Marriage=""	,//	婚姻状况
	BabyNum=""	,//	子女数
	DetailAddName=""	,//	家庭地址
	DetailAdd=""	,//	家庭地址
	CustomerBelongArea=""	,//	客户所属行政区划代码
	CustomerBelongAreaName=""	,//	客户所属行政区划代码
	FamilyAdd=""	,//	家庭详细地址
	FamilyZIP=""	,//	家庭地址邮编
	FamilyTel=""	,//	家庭电话
	FamilyStatus=""	,//	居住状况
	MobileTelephone=""	,//	手机号码
	EmailAdd=""	,//	电子邮箱
	LocalLinkMan=""	,//	联系人
	LocalAdd=""	,//	联系人地址
	LocalTel=""	,//	联系人电话
	LocalCellPhone=""	,//	联系人手机
	UrgencyLinkMan=""	,//	紧急联系人
	RelaToLender=""	,//	同借款人关系
	UrgencyAdd=""	,//	紧急联系人地址
	UrgencyTel=""	,//	紧急联系人电话
	UrgencyCellPhone=""	,//	紧急联系人手机
	CustomerResource=""	,//	客户来源
	IsResideLocalLongTime=""	,//	是否本地常住
	IsGivenTaxesList=""	,//	是否提供税单
	Occupation=""	,//	职业
	HeadShip=""	,//	职务
	Position=""	,//	职称
	MonthIncome=""	,//	个人月收入
	YearIncome=""	,//	个人年收入
	MonthRent=""	,//	个人月租金收入
	OtherMonthIncome=""	,//	其他月收入
	FamilyMonthIncome=""	,//	家庭月收入
	FamilyAssets=""	,//	家庭总资产
	Familyindebted=""	,//	家庭总负债
	AlreadyFurnish=""	,//	已供款额
	PrepareFurnish=""	,//	拟供款额
	PropertyFee=""	,//	物业管理费(月)
	IncomeProve=""	,//	收入证明
	INVESTMENTFIRM=""	,//	个人投资企业名称
	INVESTMENTVALUE=""	,//	实际投资金额
	INVESTMENTRATE=""	,//	占股比例(%)
	HOUSEAMOUNT=""	,//	家庭房产套数
	HOUSEVALUE=""	,//	家庭房产购买总价值
	CARAMOUNT=""	,//	家庭购买汽车辆数
	CARVALUE=""	,//	家庭购买汽车总价值
	PERSONALASSETS=""	,//	个人资产
	INDEBTEDDESCRIBE=""	,//	负债情况描述
	Character=""	,//	兴趣爱好
	FinanceBelong=""	,//	财务报表类型
	UnitKind=""	,//	单位所属行业
	WorkCorp=""	,//	单位名称
	DetailWorkAdd=""	,//	单位地址
	DetailWorkAddName=""	,//	单位地址
	WorkAdd=""	,//	单位详细地址
	WorkZip=""	,//	单位地址邮编
	WorkTel=""	,//	单位电话
	WorkYearCount=""	,//	现工作单位年限
	IndustryYearCount=""	,//	目前行业从业年限
	PositionYear=""	,//	任职年限
	WorkBeginDate=""	,//	本单位工作起始日
	CommAddType=""	,//	通讯地址类型
	CommAdd=""	,//	通讯地址
	CommZip=""	,//	通讯地址邮编
	PayAccount=""	,//	工资帐号
	TempSaveFlag=""	,//	暂存标志
	PayAccountBank=""	,//	工资帐号开户银行
	BillForm=""	,//	账单形式
	HasRecommendor=""	,//	是否有推荐人
	recommendorCertType=""	,//	推荐人证件类型
	recommendorCertID=""	,//	推荐人证件号码
	recommendor=""	,//	推荐人名称
	recommendorMainCustomerID=""	,//	推荐人核心客户号
	recommendScale=""	,//	推荐人提成比例
	Remark=""	,//	备注
	ManageUserName=""	,//	管户人
	ManageOrgName=""	,//	管户机构
	InputUserID=""	,//	登记人
	UserName=""	,//	登记人
	InputOrgID=""	,//	登记单位
	OrgName=""	,//	登记单位
	InputDate=""	,//	登记日期
	UpdateDate="",//更新日器
	hasUpdatePermission = "",
	SeqNo;//流水

	List<CustomRelaInfo> CustomerObjectRelatives;
	
	

	public List<CustomRelaInfo> getCustomerObjectRelatives() {
		return CustomerObjectRelatives;
	}

	public void setCustomerObjectRelatives(
			List<CustomRelaInfo> customerObjectRelatives) {
		CustomerObjectRelatives = customerObjectRelatives;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getCertType() {
		return CertType;
	}

	public void setCertType(String certType) {
		CertType = certType;
	}

	public String getCertID() {
		return CertID;
	}

	public void setCertID(String certID) {
		CertID = certID;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String birthday) {
		Birthday = birthday;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getEduExperience() {
		return EduExperience;
	}

	public void setEduExperience(String eduExperience) {
		EduExperience = eduExperience;
	}

	public String getEduRecord() {
		return EduRecord;
	}

	public void setEduRecord(String eduRecord) {
		EduRecord = eduRecord;
	}

	public String getGraduateYear() {
		return GraduateYear;
	}

	public void setGraduateYear(String graduateYear) {
		GraduateYear = graduateYear;
	}

	public String getEduDegree() {
		return EduDegree;
	}

	public void setEduDegree(String eduDegree) {
		EduDegree = eduDegree;
	}

	public String getSINo() {
		return SINo;
	}

	public void setSINo(String sINo) {
		SINo = sINo;
	}

	public String getIsRela() {
		return IsRela;
	}

	public void setIsRela(String isRela) {
		IsRela = isRela;
	}

	public String getStaff() {
		return Staff;
	}

	public void setStaff(String staff) {
		Staff = staff;
	}

	public String getCustomerLevel() {
		return CustomerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		CustomerLevel = customerLevel;
	}

	public String getCreditRecordType() {
		return CreditRecordType;
	}

	public void setCreditRecordType(String creditRecordType) {
		CreditRecordType = creditRecordType;
	}

	public String getNationality() {
		return Nationality;
	}

	public void setNationality(String nationality) {
		Nationality = nationality;
	}

	public String getNativePlace() {
		return NativePlace;
	}

	public void setNativePlace(String nativePlace) {
		NativePlace = nativePlace;
	}

	public String getPoliticalFace() {
		return PoliticalFace;
	}

	public void setPoliticalFace(String politicalFace) {
		PoliticalFace = politicalFace;
	}

	public String getMarriage() {
		return Marriage;
	}

	public void setMarriage(String marriage) {
		Marriage = marriage;
	}

	public String getBabyNum() {
		return BabyNum;
	}

	public void setBabyNum(String babyNum) {
		BabyNum = babyNum;
	}

	public String getDetailAddName() {
		return DetailAddName;
	}

	public void setDetailAddName(String detailAddName) {
		DetailAddName = detailAddName;
	}

	public String getDetailAdd() {
		return DetailAdd;
	}

	public void setDetailAdd(String detailAdd) {
		DetailAdd = detailAdd;
	}

	public String getCustomerBelongArea() {
		return CustomerBelongArea;
	}

	public void setCustomerBelongArea(String customerBelongArea) {
		CustomerBelongArea = customerBelongArea;
	}

	public String getCustomerBelongAreaName() {
		return CustomerBelongAreaName;
	}

	public void setCustomerBelongAreaName(String customerBelongAreaName) {
		CustomerBelongAreaName = customerBelongAreaName;
	}

	public String getFamilyAdd() {
		return FamilyAdd;
	}

	public void setFamilyAdd(String familyAdd) {
		FamilyAdd = familyAdd;
	}

	public String getFamilyZIP() {
		return FamilyZIP;
	}

	public void setFamilyZIP(String familyZIP) {
		FamilyZIP = familyZIP;
	}

	public String getFamilyTel() {
		return FamilyTel;
	}

	public void setFamilyTel(String familyTel) {
		FamilyTel = familyTel;
	}

	public String getFamilyStatus() {
		return FamilyStatus;
	}

	public void setFamilyStatus(String familyStatus) {
		FamilyStatus = familyStatus;
	}

	public String getMobileTelephone() {
		return MobileTelephone;
	}

	public void setMobileTelephone(String mobileTelephone) {
		MobileTelephone = mobileTelephone;
	}

	public String getEmailAdd() {
		return EmailAdd;
	}

	public void setEmailAdd(String emailAdd) {
		EmailAdd = emailAdd;
	}

	public String getLocalLinkMan() {
		return LocalLinkMan;
	}

	public void setLocalLinkMan(String localLinkMan) {
		LocalLinkMan = localLinkMan;
	}

	public String getLocalAdd() {
		return LocalAdd;
	}

	public void setLocalAdd(String localAdd) {
		LocalAdd = localAdd;
	}

	public String getLocalTel() {
		return LocalTel;
	}

	public void setLocalTel(String localTel) {
		LocalTel = localTel;
	}

	public String getLocalCellPhone() {
		return LocalCellPhone;
	}

	public void setLocalCellPhone(String localCellPhone) {
		LocalCellPhone = localCellPhone;
	}

	public String getUrgencyLinkMan() {
		return UrgencyLinkMan;
	}

	public void setUrgencyLinkMan(String urgencyLinkMan) {
		UrgencyLinkMan = urgencyLinkMan;
	}

	public String getRelaToLender() {
		return RelaToLender;
	}

	public void setRelaToLender(String relaToLender) {
		RelaToLender = relaToLender;
	}

	public String getUrgencyAdd() {
		return UrgencyAdd;
	}

	public void setUrgencyAdd(String urgencyAdd) {
		UrgencyAdd = urgencyAdd;
	}

	public String getUrgencyTel() {
		return UrgencyTel;
	}

	public void setUrgencyTel(String urgencyTel) {
		UrgencyTel = urgencyTel;
	}

	public String getUrgencyCellPhone() {
		return UrgencyCellPhone;
	}

	public void setUrgencyCellPhone(String urgencyCellPhone) {
		UrgencyCellPhone = urgencyCellPhone;
	}

	public String getCustomerResource() {
		return CustomerResource;
	}

	public void setCustomerResource(String customerResource) {
		CustomerResource = customerResource;
	}

	public String getIsResideLocalLongTime() {
		return IsResideLocalLongTime;
	}

	public void setIsResideLocalLongTime(String isResideLocalLongTime) {
		IsResideLocalLongTime = isResideLocalLongTime;
	}

	public String getIsGivenTaxesList() {
		return IsGivenTaxesList;
	}

	public void setIsGivenTaxesList(String isGivenTaxesList) {
		IsGivenTaxesList = isGivenTaxesList;
	}

	public String getOccupation() {
		return Occupation;
	}

	public void setOccupation(String occupation) {
		Occupation = occupation;
	}

	public String getHeadShip() {
		return HeadShip;
	}

	public void setHeadShip(String headShip) {
		HeadShip = headShip;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public String getMonthIncome() {
		return MonthIncome;
	}

	public void setMonthIncome(String monthIncome) {
		MonthIncome = monthIncome;
	}

	public String getYearIncome() {
		return YearIncome;
	}

	public void setYearIncome(String yearIncome) {
		YearIncome = yearIncome;
	}

	public String getMonthRent() {
		return MonthRent;
	}

	public void setMonthRent(String monthRent) {
		MonthRent = monthRent;
	}

	public String getOtherMonthIncome() {
		return OtherMonthIncome;
	}

	public void setOtherMonthIncome(String otherMonthIncome) {
		OtherMonthIncome = otherMonthIncome;
	}

	public String getFamilyMonthIncome() {
		return FamilyMonthIncome;
	}

	public void setFamilyMonthIncome(String familyMonthIncome) {
		FamilyMonthIncome = familyMonthIncome;
	}

	public String getFamilyAssets() {
		return FamilyAssets;
	}

	public void setFamilyAssets(String familyAssets) {
		FamilyAssets = familyAssets;
	}

	public String getFamilyindebted() {
		return Familyindebted;
	}

	public void setFamilyindebted(String familyindebted) {
		Familyindebted = familyindebted;
	}

	public String getAlreadyFurnish() {
		return AlreadyFurnish;
	}

	public void setAlreadyFurnish(String alreadyFurnish) {
		AlreadyFurnish = alreadyFurnish;
	}

	public String getPrepareFurnish() {
		return PrepareFurnish;
	}

	public void setPrepareFurnish(String prepareFurnish) {
		PrepareFurnish = prepareFurnish;
	}

	public String getPropertyFee() {
		return PropertyFee;
	}

	public void setPropertyFee(String propertyFee) {
		PropertyFee = propertyFee;
	}

	public String getIncomeProve() {
		return IncomeProve;
	}

	public void setIncomeProve(String incomeProve) {
		IncomeProve = incomeProve;
	}

	public String getINVESTMENTFIRM() {
		return INVESTMENTFIRM;
	}

	public void setINVESTMENTFIRM(String iNVESTMENTFIRM) {
		INVESTMENTFIRM = iNVESTMENTFIRM;
	}

	public String getINVESTMENTVALUE() {
		return INVESTMENTVALUE;
	}

	public void setINVESTMENTVALUE(String iNVESTMENTVALUE) {
		INVESTMENTVALUE = iNVESTMENTVALUE;
	}

	public String getINVESTMENTRATE() {
		return INVESTMENTRATE;
	}

	public void setINVESTMENTRATE(String iNVESTMENTRATE) {
		INVESTMENTRATE = iNVESTMENTRATE;
	}

	public String getHOUSEAMOUNT() {
		return HOUSEAMOUNT;
	}

	public void setHOUSEAMOUNT(String hOUSEAMOUNT) {
		HOUSEAMOUNT = hOUSEAMOUNT;
	}

	public String getHOUSEVALUE() {
		return HOUSEVALUE;
	}

	public void setHOUSEVALUE(String hOUSEVALUE) {
		HOUSEVALUE = hOUSEVALUE;
	}

	public String getCARAMOUNT() {
		return CARAMOUNT;
	}

	public void setCARAMOUNT(String cARAMOUNT) {
		CARAMOUNT = cARAMOUNT;
	}

	public String getCARVALUE() {
		return CARVALUE;
	}

	public void setCARVALUE(String cARVALUE) {
		CARVALUE = cARVALUE;
	}

	public String getPERSONALASSETS() {
		return PERSONALASSETS;
	}

	public void setPERSONALASSETS(String pERSONALASSETS) {
		PERSONALASSETS = pERSONALASSETS;
	}

	public String getINDEBTEDDESCRIBE() {
		return INDEBTEDDESCRIBE;
	}

	public void setINDEBTEDDESCRIBE(String iNDEBTEDDESCRIBE) {
		INDEBTEDDESCRIBE = iNDEBTEDDESCRIBE;
	}

	public String getCharacter() {
		return Character;
	}

	public void setCharacter(String character) {
		Character = character;
	}

	public String getFinanceBelong() {
		return FinanceBelong;
	}

	public void setFinanceBelong(String financeBelong) {
		FinanceBelong = financeBelong;
	}

	public String getUnitKind() {
		return UnitKind;
	}

	public void setUnitKind(String unitKind) {
		UnitKind = unitKind;
	}

	public String getWorkCorp() {
		return WorkCorp;
	}

	public void setWorkCorp(String workCorp) {
		WorkCorp = workCorp;
	}

	public String getDetailWorkAdd() {
		return DetailWorkAdd;
	}

	public void setDetailWorkAdd(String detailWorkAdd) {
		DetailWorkAdd = detailWorkAdd;
	}

	public String getDetailWorkAddName() {
		return DetailWorkAddName;
	}

	public void setDetailWorkAddName(String detailWorkAddName) {
		DetailWorkAddName = detailWorkAddName;
	}

	public String getWorkAdd() {
		return WorkAdd;
	}

	public void setWorkAdd(String workAdd) {
		WorkAdd = workAdd;
	}

	public String getWorkZip() {
		return WorkZip;
	}

	public void setWorkZip(String workZip) {
		WorkZip = workZip;
	}

	public String getWorkTel() {
		return WorkTel;
	}

	public void setWorkTel(String workTel) {
		WorkTel = workTel;
	}

	public String getWorkYearCount() {
		return WorkYearCount;
	}

	public void setWorkYearCount(String workYearCount) {
		WorkYearCount = workYearCount;
	}

	public String getIndustryYearCount() {
		return IndustryYearCount;
	}

	public void setIndustryYearCount(String industryYearCount) {
		IndustryYearCount = industryYearCount;
	}

	public String getPositionYear() {
		return PositionYear;
	}

	public void setPositionYear(String positionYear) {
		PositionYear = positionYear;
	}

	public String getWorkBeginDate() {
		return WorkBeginDate;
	}

	public void setWorkBeginDate(String workBeginDate) {
		WorkBeginDate = workBeginDate;
	}

	public String getCommAddType() {
		return CommAddType;
	}

	public void setCommAddType(String commAddType) {
		CommAddType = commAddType;
	}

	public String getCommAdd() {
		return CommAdd;
	}

	public void setCommAdd(String commAdd) {
		CommAdd = commAdd;
	}

	public String getCommZip() {
		return CommZip;
	}

	public void setCommZip(String commZip) {
		CommZip = commZip;
	}

	public String getPayAccount() {
		return PayAccount;
	}

	public void setPayAccount(String payAccount) {
		PayAccount = payAccount;
	}

	public String getTempSaveFlag() {
		return TempSaveFlag;
	}

	public void setTempSaveFlag(String tempSaveFlag) {
		TempSaveFlag = tempSaveFlag;
	}

	public String getPayAccountBank() {
		return PayAccountBank;
	}

	public void setPayAccountBank(String payAccountBank) {
		PayAccountBank = payAccountBank;
	}

	public String getBillForm() {
		return BillForm;
	}

	public void setBillForm(String billForm) {
		BillForm = billForm;
	}

	public String getHasRecommendor() {
		return HasRecommendor;
	}

	public void setHasRecommendor(String hasRecommendor) {
		HasRecommendor = hasRecommendor;
	}

	public String getRecommendorCertType() {
		return recommendorCertType;
	}

	public void setRecommendorCertType(String recommendorCertType) {
		this.recommendorCertType = recommendorCertType;
	}

	public String getRecommendorCertID() {
		return recommendorCertID;
	}

	public void setRecommendorCertID(String recommendorCertID) {
		this.recommendorCertID = recommendorCertID;
	}

	public String getRecommendor() {
		return recommendor;
	}

	public void setRecommendor(String recommendor) {
		this.recommendor = recommendor;
	}

	public String getRecommendorMainCustomerID() {
		return recommendorMainCustomerID;
	}

	public void setRecommendorMainCustomerID(String recommendorMainCustomerID) {
		this.recommendorMainCustomerID = recommendorMainCustomerID;
	}

	public String getRecommendScale() {
		return recommendScale;
	}

	public void setRecommendScale(String recommendScale) {
		this.recommendScale = recommendScale;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getManageUserName() {
		return ManageUserName;
	}

	public void setManageUserName(String manageUserName) {
		ManageUserName = manageUserName;
	}

	public String getManageOrgName() {
		return ManageOrgName;
	}

	public void setManageOrgName(String manageOrgName) {
		ManageOrgName = manageOrgName;
	}

	public String getInputUserID() {
		return InputUserID;
	}

	public void setInputUserID(String inputUserID) {
		InputUserID = inputUserID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getInputOrgID() {
		return InputOrgID;
	}

	public void setInputOrgID(String inputOrgID) {
		InputOrgID = inputOrgID;
	}

	public String getOrgName() {
		return OrgName;
	}

	public void setOrgName(String orgName) {
		OrgName = orgName;
	}

	public String getInputDate() {
		return InputDate;
	}

	public void setInputDate(String inputDate) {
		InputDate = inputDate;
	}

	public String getUpdateDate() {
		return UpdateDate;
	}

	public void setUpdateDate(String updateDate) {
		UpdateDate = updateDate;
	}

	public String getHasUpdatePermision() {
		return hasUpdatePermission;
	}

	public void setHasUpdatePermision(String hasUpdatePermision) {
		this.hasUpdatePermission = hasUpdatePermision;
	}

	public String getSeqNo() {
		return SeqNo;
	}

	public void setSeqNo(String seqNo) {
		SeqNo = seqNo;
	}
}

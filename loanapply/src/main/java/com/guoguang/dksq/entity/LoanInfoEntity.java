package com.guoguang.dksq.entity;

import com.guoguang.dksq.database.Assure;
import com.guoguang.dksq.database.Guarantee;
import com.guoguang.dksq.database.House;
import com.guoguang.dksq.database.LoanBusiness;
import com.guoguang.dksq.database.PhotoEntity;

import java.util.List;

public class LoanInfoEntity {
	LoanBusiness loanBusiness;
	Assure assure;
	House house;
	List<Guarantee> guarantees;
	List<PhotoEntity> photoMap;

	public LoanBusiness getLoanBusiness() {
		return loanBusiness;
	}
	public void setLoanBusiness(LoanBusiness loanBusiness) {
		this.loanBusiness = loanBusiness;
	}
	public Assure getAssure() {
		return assure;
	}
	public void setAssure(Assure assure) {
		this.assure = assure;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public List<PhotoEntity> getPhotoMap() {
		return photoMap;
	}
	public void setPhotoMap(List<PhotoEntity> photoMap) {
		this.photoMap = photoMap;
	}

	public List<Guarantee> getGuarantees() {
		return guarantees;
	}

	public void setGuarantees(List<Guarantee> guarantees) {
		this.guarantees = guarantees;
	}
}

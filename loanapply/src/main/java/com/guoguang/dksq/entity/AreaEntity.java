package com.guoguang.dksq.entity;


import com.guoguang.dksq.database.AreaLib;

import java.util.List;

public class AreaEntity {
	List<AreaLib> ProvinceList;
	List<AreaLib> CityList;
	List<AreaLib> AreaList;

	public List<AreaLib> getProvinceList() {
		return ProvinceList;
	}
	public void setProvinceList(List<AreaLib> provinceList) {
		ProvinceList = provinceList;
	}
	public List<AreaLib> getCityList() {
		return CityList;
	}
	public void setCityList(List<AreaLib> cityList) {
		CityList = cityList;
	}
	public List<AreaLib> getAreaList() {
		return AreaList;
	}
	public void setAreaList(List<AreaLib> areaList) {
		AreaList = areaList;
	}

	
	
	
	
}

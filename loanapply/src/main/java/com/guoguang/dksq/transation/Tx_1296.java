package com.guoguang.dksq.transation;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Tx_1296<T extends Object> extends BaseTx{
	String Code;
	Gson gson ;
	
	public Tx_1296(String code) {
		super();
		Code = code;
		gson = new GsonBuilder().create();
	}


	@Override
	public String getBody() {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<>();
		map.put("TransCode", "1296");
		map.put("code",Code);
		return gson.toJson(map);
	}
	
}

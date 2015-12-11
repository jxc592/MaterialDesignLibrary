package com.guoguang.dksq.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String formatDkDate(){
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}
}

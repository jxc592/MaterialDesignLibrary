package com.aidl.cilent.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class DateUtil {

    private static final String DEFUALT_FORMAT = "yyyy-MM-dd";
    private static final String DETAILED_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String LSH_FORMAT = "yyyyMMdd";
    private static final String P_FORMAT = "yyMMddhhmm";
    private static final String DETAILED_LSH_FORMAT = "yyyyMMddHHmmss";
    private static final String SIMPLE_FORMAT = "yyMMdd";

    private static final String FIRST_TIME = "HH : mm";
    private static final String NIANYUERI_FORMAT = "yyyy年MM月dd日 E";
    
    private static final String FIRST_WEEK = "E";
    private static final String FIRST_FORMAT = "dd/MM/yyyy";
    private static final String TOP_TIME_FORMAT = "HH:mm";
    private static final String CurrentHour = "HH";
    
    private static final SimpleDateFormat topTime = new SimpleDateFormat(
			TOP_TIME_FORMAT);
    private static final SimpleDateFormat defualtFormat = new SimpleDateFormat(DEFUALT_FORMAT);
    private static final SimpleDateFormat detaledFormat = new SimpleDateFormat(DETAILED_FORMAT);
    private static final SimpleDateFormat lshFormat = new SimpleDateFormat(LSH_FORMAT);
    private static final SimpleDateFormat detaledLshFormat = new SimpleDateFormat(DETAILED_LSH_FORMAT);
    private static final SimpleDateFormat simpleFormat = new SimpleDateFormat(SIMPLE_FORMAT);
    private static final SimpleDateFormat pFormat = new SimpleDateFormat(P_FORMAT);

    private static final SimpleDateFormat nianYueRiFormat = new SimpleDateFormat(NIANYUERI_FORMAT);
    
    private static final SimpleDateFormat firstFormat = new SimpleDateFormat(FIRST_FORMAT);
    private static final SimpleDateFormat firstWeek = new SimpleDateFormat(FIRST_WEEK);
    private static final SimpleDateFormat firstTime = new SimpleDateFormat(FIRST_TIME);
    private static final SimpleDateFormat currentHourTime = new SimpleDateFormat(CurrentHour);
    
 // 当前时间是星期几
 	public static String getWeekOfDate() {
 		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
 		Calendar cal = Calendar.getInstance();
 		Date curDate = new Date(System.currentTimeMillis());
 		cal.setTime(curDate);
 		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
 		if (w < 0)
 			w = 0;
 		return weekDays[w];
 	}
    
    /**
     * 返回默认的日期格式： yyyy年MM月dd日 周几
     * @param date
     * @return
     */
    public static String NianYueRiFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return nianYueRiFormat.format(date);
        }
    }
   
    
    
    /**
     * 返回默认的日期格式： yyyy-MM-dd
     * @param date
     * @return
     */
    public static String defualtFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return defualtFormat.format(date);
        }
    }

    /**
     * 返回默认的日期格式： yyMMddhhmm
     * @param date
     * @return
     */
    public static String messageIdFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return pFormat.format(date);
        }
    }
    /**
     * 返回日期详细格式：yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String detaledFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return detaledFormat.format(date);
        }
    }

    /**
     * 返回默认的流水号中使用的日期格式：yyyyMMdd
     * @param date
     * @return
     */
    public static String lshFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return lshFormat.format(date);
        }
    }

    /**
     * 返回简单的日期格式：yyMMdd
     * @param date
     * @return
     */
    public static String simpleFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return simpleFormat.format(date);
        }
    }

    /**
     * 返回详细的流水号中使用的日期格式： yyyyMMddHHmmss
     * @param date
     * @return
     */
    public static String detaledLshFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return detaledLshFormat.format(date);
        }
    }

    /**
     * 为日期增加天数
     * @param date 日期
     * @param day 天数，可以为负
     * @return
     */
    public static Date addDay(Date date, int day) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 为日期增加月数
     * @param date 日期
     * @param month 月，可以为负
     * @return
     */
    public static Date addMonth(Date date, int month) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 设置小时
     * @param date 时间
     * @param hour 小时
     * @return
     */
    public static Date setHour(Date date, int hour) {
        if (hour < 0) {
            hour = 0;
        } else if (hour > 23) {
            hour = 23;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 设置分钟
     * @param date 时间
     * @param minute 分钟
     * @return
     */
    public static Date setMinute(Date date, int minute) {
        if (minute < 0) {
            minute = 0;
        } else if (minute > 59) {
            minute = 59;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 设置秒
     * @param date 时间
     * @param second 秒
     * @return
     */
    public static Date setSecond(Date date, int second) {
        if (second < 0) {
            second = 0;
        } else if (second > 59) {
            second = 59;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, second); 
        return calendar.getTime();
    }
    
    /**
     * 把 yyyy-MM-dd HH:mm:ss 的字符串转为日期
     * @param str  
     * @return
     * @throws InstanceDataException
     */
    public static Date parseDate(String str) throws Exception {
         if (str == null || "".equals(str)) {  
			return null;
		}
            return detaledFormat.parse(str);
    }
    
	/**
	 *2012-02-02格式转化成Date
	 * @param time
	 * @return
	 */
	public static final Date  getDateFormString(String time){
		time = time + " 00:01:01";   
		Date mDate = null ;
		  try {
			mDate = DateUtil.parseDate(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mDate;
		
	}
	
	/**
	 * 检测字符串是否符合时间格式 
	 * @param sDate 格式为"yyyy-mm-dd"
	 * @return
	 */
	public static boolean isValidDate(String sDate) {
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}

	

	/**
	 * 输入的日期格式yyyymmdd转为yyyy-mm-yy

	 * @param  str  输入的日期字符串  "20121221"
	 * @return String string 转化后的字符串“2012-12-21”
	 */
	
	public static String formateDateDesplay(String str) {
		if ("".equals(str) || str == null) {
			return "";    
		}
		str = str.replaceAll("-", ""); 
		if ("长期".equals(str)) {
			return str;
		}
		if ((str.length() == 8) && checkDate(str)) // 长度部位8位，或不符日期格式
		{
			String string = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);
			return string;
		}
		return null;
	}    
	
	/**
	 * 输入的日期格式yyyymmdd转为yyyy-mm-yy

	 * @param  str  输入的日期字符串  "20121221"
	 * @return String string 转化后的字符串“2012-12-21”
	 */
	
	public static String formateDateDesplay2(String str) {
		if ("".equals(str) || str == null) {
			return "";    
		}
		str = str.replaceAll("-", ""); 
		if ("长期".equals(str)) {
			return str;
		}
		// 长度部位8位，或不符日期格式
		String string = str.substring(0, 4) + "-" + str.substring(4, 6) + "-"
				+ str.substring(6, 8);
		return string;
	}   
	
	/**
	 * 判断日期是否正确
	 * 
	 * @param number  日期,形式为"yyyyMMdd"，如"20120624"         
	 * @return boolean true--日期格式正确，false--日期格式错误
	 */
	public static final boolean checkDate(String number) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		simpledateformat.setLenient(false);
		try {
			simpledateformat.parse(number);
		}   catch (ParseException e) {
			return false;
		}
		return true;
	}
	
	
	 /**
     * 得到当前的时间Date
     */
    public static final String getCurrentTime(){ 
    	int  n_year, n_month, n_day;  
    	String mDate = null; 
    	Calendar c = null;  
    	c = Calendar.getInstance();
		n_year = c.get(Calendar.YEAR); 
		n_month = c.get(Calendar.MONTH) + 1;
		n_day = c.get(Calendar.DAY_OF_MONTH);
		String year = String.valueOf(n_year);
		String month = String.valueOf(n_month);
		String day = String.valueOf(n_day);
		if (month.length() == 1) {
			month = "0" + month; 
		} 
		if (day.length() == 1) {
			day = "0" + day ; 
		}
		mDate = year + "-" + month + "-" + day;
		
		return mDate;
    	
    }
    
    /**
     * 获取当前时间的毫秒数
     * @return
     */
    public static final String getCurrentMillTime(){
    	Calendar c = Calendar.getInstance();
    	long time = c.getTimeInMillis();
		return String.valueOf(time);
    }
    
    /**201105211255111111
     * "yyyy.MM.dd HH:mm:ss"
     * 18位字符串转换成时间字符串 
     * @param time
     * @return
     */
    public static final String LongToDateString(String time ){
    	 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
         Date d = null;
		try {
			d = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
         sdf=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
         String a=sdf.format(d); 
         return a;
    }
    
    /**
     * 返回默认的日期格式： dd/mm/yyyy格式
     * @param date
     * @return
     */
    public static String FirstFormat(Date date) {
        if (date == null) {
            return "";
        } else {
            return firstFormat.format(date);
        }
    }
    
    /**
     * 返回默认的日期格式： 星期几
     * @param date
     * @return
     */
    public static String FirstWeek(Date date) {
        if (date == null) {
            return "";
        } else {
            return firstWeek.format(date);
        }
    }
    
    /**
     * 返回默认的 时间格式： xx:xx:xx
     * @param date
     * @return
     */
    public static String FirstTime(Date date) {
        if (date == null) {
            return "";
        } else {
            return firstTime.format(date);
        }
    }
	/**
	 * 格式化时间201307021205
	 * @param datetime
	 * @return
	 */
	public static String formateLongTime(String datetime){
		if (datetime != null && datetime.length() > 12) {
			String year = datetime.substring(0, 4);
			String yue = datetime.substring(4, 6);
			String day = datetime.substring(6, 8);
			String hour = datetime.substring(8, 10);
			String min = datetime.substring(10, 12);
			return year + "年 " + yue + "月 "  + day + "日"+ hour + "时" + min+"分";
		}
		return "";
	}
	
	/**
	 * 这个方法是用来格式化固定的显示时间的
	 * 
	 * @param date
	 * @return
	 */
	public static String TopTimeFormat(Date date) {
		if (date == null) {
			return "";
		} else {
			return topTime.format(date);
		}
	}
	
	public static int returnZaoZhongWan()
	{
		int timeType=1;
		Calendar mCalendar = Calendar.getInstance();//可以对每个时间域单独修改
		int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
		if(6<hour && hour<12)
		{
			//早上
			return 1;
		}
		else if(12<hour && hour<18)
		{
			//中午
			return 2;
		}else {
			//晚上
			return 3;
		}
			
	}
	
	/**
	 * 将传入时间与当前时间进行对比，是否今天昨天  
	 * @param date
	 * @return
	 */
	public static String getTimeStr(Date date) {  
	    String todySDF = "今天 HH:mm";  
	    String yesterDaySDF = "昨天 HH:mm";  
	    String otherSDF = "yyyy年M月d日 HH:mm";  
	    SimpleDateFormat sfd = null;  
	    String time = "";  
	    Calendar dateCalendar = Calendar.getInstance();  
	    dateCalendar.setTime(date);  
	    Date now = new Date();  
	    Calendar targetCalendar = Calendar.getInstance();  
	    targetCalendar.setTime(now);  
	    targetCalendar.set(Calendar.HOUR_OF_DAY, 0);  
	    targetCalendar.set(Calendar.MINUTE, 0);  
	    if (dateCalendar.after(targetCalendar)) {  
	        sfd = new SimpleDateFormat(todySDF, Locale.getDefault());  
	        time = sfd.format(date);  
	        return time;  
	    } else {  
	        targetCalendar.add(Calendar.DATE, -1);  
	        if (dateCalendar.after(targetCalendar)) {  
	            sfd = new SimpleDateFormat(yesterDaySDF, Locale.getDefault());  
	            time = sfd.format(date);  
	            return time;  
	        }  
	    }  
	    sfd = new SimpleDateFormat(otherSDF, Locale.getDefault());  
	    time = sfd.format(date);  
	    return time;  
	}
	
	/**
	 * 获取明天的日期xxxx-xx-xx
	 * @return
	 */
	public static String getTomorrowTime(){
		Date date=new Date();//取时间
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String dateString = formatter.format(date);
		 return dateString;
	}
}

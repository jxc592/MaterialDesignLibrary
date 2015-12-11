package com.aidl.cilent.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import android.os.Environment;
import android.util.Log;

public class MyLog {
	private static String myFilePath= Environment.getExternalStorageDirectory()
			+ "/crash";
//	private static String myFilePath="sdcard/MyLogFile";
	private static String myLogPath="";
	static String exe_bDate="";
	static String textName="";
	public MyLog()
	{
		textName();
		myLogPath=myFilePath+"/"+textName+".txt";
	}
	public static void createFile()
	{
		File myFile=new File(myFilePath);//判断目录是否存在，不存在创建
		if(!myFile.exists())
		{
			myFile.mkdir();
		}
	}
	
	public static void createMyLog()
	{
		File myLog=new File(myLogPath);
		if(!myLog.exists())
		{
			try {
				myLog.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 添加一条log
	 * @param str
	 */
	public static void addMyLog(String str)
	{
		if (textName == null || "".equals(textName)) {
			textName();
			myLogPath=myFilePath+"/"+textName+".txt";
//			myLogPath="sdcard/MyLogFile/"+textName+".txt";
		}
		createFile();
		createMyLog();
		beginDate();
		StringBuffer buffer=new StringBuffer();
		buffer.append("\n");
		buffer.append(exe_bDate+":");
		buffer.append(str+"\n");
		File file=new File(myLogPath);
		try {
			// 写数据   注意这里，两个参数，第一个是写入的文件，第二个是指是覆盖还是追加，
            //默认是覆盖的，就是不写第二个参数，这里设置为true就是说不覆盖，是在后面追加。
			FileOutputStream fos=new FileOutputStream(file,true);
			fos.write(buffer.toString().getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询log
	 * @return
	 */
	public String selectMyLog()
	{
		File file=new File(myLogPath);
		String myText="";
		StringBuilder text = new StringBuilder();
		try {
			
			FileInputStream fis=new FileInputStream(file);
			BufferedReader br=new BufferedReader(new InputStreamReader(fis));
			while((myText=br.readLine())!=null)
			{
				text.append(myText);
				text.append("\n");
			}
			br.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text.toString();
	}
	
	//记录工作开始时间
	public static void beginDate()
	{
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date javaDate=new java.util.Date();
		exe_bDate =  dateFormat.format(javaDate);
	}
	
	public static void textName()
	{
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date javaDate=new java.util.Date();
		textName =  dateFormat.format(javaDate);
		textName = "crash-" + textName ;
	}
}

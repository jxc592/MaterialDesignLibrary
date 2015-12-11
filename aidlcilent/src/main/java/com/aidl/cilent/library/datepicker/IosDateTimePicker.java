package com.aidl.cilent.library.datepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.aidl.cilent.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class IosDateTimePicker extends LinearLayout {

	Context mContext;
	LayoutInflater inflater;
	
	
	private WheelView year;
	private WheelView month;
	private WheelView day;
	private WheelView time;
	private WheelView min;
	private WheelView sec;
	
	private int mYear=1996;
	private int mMonth=0;
	private int mDay=1;
	
	public enum Mode{
		ShowAll,
		ShowDate,
		ShowTime
	};
	
	Mode mode;
	
	
	@SuppressLint("NewApi")
	public IosDateTimePicker(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		prepare(context);
	}

	public IosDateTimePicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		prepare(context);
	}

	public IosDateTimePicker(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		prepare(context);
	}
	

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
		if(mode == Mode.ShowDate){
			min.setVisibility(View.GONE);
			sec.setVisibility(View.GONE);
		}else if (mode == Mode.ShowTime) {
			year.setVisibility(View.GONE);
			month.setVisibility(View.GONE);
			day.setVisibility(View.GONE);
		}
		view.invalidate();
	}





	
	

	public MDate getmDate() {
		MDate  date = new MDate();
		date.setYear((year.getCurrentItem()+1950)+"");
		date.setMonth(String.format("%02d",month.getCurrentItem()+1 ));
		date.setDay(String.format("%02d",day.getCurrentItem()+1 ));
		date.setHour(String.format("%02d",min.getCurrentItem()+1 ));
		date.setMiniute(String.format("%02d",sec.getCurrentItem()+1 ));
		return date;
	}

	View view;
	void prepare(Context context){
		mContext =context;
		inflater = LayoutInflater.from(context);
		addView(getDataPick());
	}
	OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
		@Override
		public void onScrollingStarted(WheelView wheel) {

		}

		@Override
		public void onScrollingFinished(WheelView wheel) {
			int n_year = year.getCurrentItem() + 1950;//��
			int n_month = month.getCurrentItem() + 1;//��
			
			initDay(n_year,n_month);
			
			//String birthday=new StringBuilder().append((year.getCurrentItem()+1950)).append("-").append((month.getCurrentItem() + 1) < 10 ? "0" + (month.getCurrentItem() + 1) : (month.getCurrentItem() + 1)).append("-").append(((day.getCurrentItem()+1) < 10) ? "0" + (day.getCurrentItem()+1) : (day.getCurrentItem()+1)).toString();
			//tv1.setText("����             "+calculateDatePoor(birthday)+"��");
			//tv2.setText("����             "+getAstro(month.getCurrentItem() + 1,day.getCurrentItem()+1));
		}
	};
	
	
	
	private View getDataPick() {
		Calendar c = Calendar.getInstance();
		int norYear = c.get(Calendar.YEAR);
		int curMonth = c.get(Calendar.MONTH) + 1;//ͨ��Calendar���������Ҫ+1
		int curDate = c.get(Calendar.DATE);
		
		int curYear = mYear;
//		int curMonth =mMonth+1;
//		int curDate = mDay;
		
		view = inflater.inflate(R.layout.wheel_date_picker, null);
		
		year = (WheelView) view.findViewById(R.id.year);
		NumericWheelAdapter numericWheelAdapter1=new NumericWheelAdapter(mContext,1950, norYear); 
		numericWheelAdapter1.setLabel("年");
		year.setViewAdapter(numericWheelAdapter1);
		year.setCyclic(false);//�Ƿ��ѭ������
		year.addScrollingListener(scrollListener);

		
		month = (WheelView) view.findViewById(R.id.month);
		NumericWheelAdapter numericWheelAdapter2=new NumericWheelAdapter(mContext,1, 12, "%02d"); 
		numericWheelAdapter2.setLabel("月");
		month.setViewAdapter(numericWheelAdapter2);
		month.setCyclic(true);
		month.addScrollingListener(scrollListener);
		
		day = (WheelView) view.findViewById(R.id.day);
		initDay(curYear,curMonth);
		day.setCyclic(true);
		
		time= (WheelView) view.findViewById(R.id.time);
		String[] times = {"����","����"} ;
		ArrayWheelAdapter<String> arrayWheelAdapter=new ArrayWheelAdapter<String>(mContext,times );
		time.setViewAdapter(arrayWheelAdapter);
		time.setCyclic(false);
		time.addScrollingListener(scrollListener);
		
		min = (WheelView) view.findViewById(R.id.min);
		NumericWheelAdapter numericWheelAdapter3=new NumericWheelAdapter(mContext,1, 23, "%02d"); 
		numericWheelAdapter3.setLabel("ʱ");
		min.setViewAdapter(numericWheelAdapter3);
		min.setCyclic(true);
		min.addScrollingListener(scrollListener);
		
		sec = (WheelView) view.findViewById(R.id.sec);
		NumericWheelAdapter numericWheelAdapter4=new NumericWheelAdapter(mContext,1, 59, "%02d"); 
		numericWheelAdapter4.setLabel("��");
		sec.setViewAdapter(numericWheelAdapter4);
		sec.setCyclic(true);
		sec.addScrollingListener(scrollListener);
		
		
		
		year.setVisibleItems(7);//������ʾ����
		month.setVisibleItems(7);
		day.setVisibleItems(7);
//		time.setVisibleItems(7);
		min.setVisibleItems(7);
		sec.setVisibleItems(7);
		
		
		year.setCurrentItem(norYear - 1950);
		month.setCurrentItem(curMonth - 1);
		day.setCurrentItem(curDate - 1);
		
		return view;
	}
	
	/**
	 */
	private void initDay(int arg1, int arg2) {
		NumericWheelAdapter numericWheelAdapter=new NumericWheelAdapter(mContext,1, getDay(arg1, arg2), "%02d");
		numericWheelAdapter.setLabel("日");
		day.setViewAdapter(numericWheelAdapter);
	}
	
	/**
	 * ������ڼ�������
	 * @param birthday
	 * @return
	 */
	public  String calculateDatePoor(String birthday) {
		try {
			if (TextUtils.isEmpty(birthday))
				return "0";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date birthdayDate = sdf.parse(birthday);
			String currTimeStr = sdf.format(new Date());
			Date currDate = sdf.parse(currTimeStr);
			if (birthdayDate.getTime() > currDate.getTime()) {
				return "0";
			}
			long age = (currDate.getTime() - birthdayDate.getTime())
					/ (24 * 60 * 60 * 1000) + 1;
			String year = new DecimalFormat("0.00").format(age / 365f);
			if (TextUtils.isEmpty(year))
				return "0";
			return String.valueOf(new Double(year).intValue());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "0";
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	private int getDay(int year, int month) {
		int day = 30;
		boolean flag = false;
		switch (year % 4) {
		case 0:
			flag = true;
			break;
		default:
			flag = false;
			break;
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 2:
			day = flag ? 29 : 28;
			break;
		default:
			day = 30;
			break;
		}
		return day;
	}
	
	/**
	 * ������ռ�������
	 * @param month
	 * @param day
	 * @return
	 */
	public String getAstro(int month, int day) {
         String[] astro = new String[] { "Ħ����", "ˮƿ��", "˫����", "������", "��ţ��",
                         "˫����", "��з��", "ʨ����", "��Ů��", "�����", "��Ы��", "������", "Ħ����" };
         int[] arr = new int[] { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };// ��������ָ���
         int index = month;
         // ���ѯ�����ڷָ���֮ǰ������-1�����򲻱�
         if (day < arr[month - 1]) {
                 index = index - 1;
         }
         // ��������ָ�������string
         return astro[index];
 }
	
	public class MDate{
		String year,month,day,hour,miniute;

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public String getMonth() {
			return month;
		}

		public void setMonth(String month) {
			this.month = month;
		}

		public String getDay() {
			return day;
		}

		public void setDay(String day) {
			this.day = day;
		}

		public String getHour() {
			return hour;
		}

		public void setHour(String hour) {
			this.hour = hour;
		}

		public String getMiniute() {
			return miniute;
		}

		public void setMiniute(String miniute) {
			this.miniute = miniute;
		}
		
	}
}

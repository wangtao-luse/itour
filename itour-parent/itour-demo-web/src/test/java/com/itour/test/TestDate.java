package com.itour.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class TestDate {
	public static void main(String[] args) throws ParseException {
	 //背景：mysql数据库日期设计为INT类型(占用资源少，查询速度快,使用较灵活)
	//DATETIME：固定格式YYYY-MM-DD HH:MM:SS使用DATETIME;
   //目的:测试long类型日期的长度来设计数据库的长度
  //结果:13位;
 // 结论：可将长度设计为13;
		
	 long currentTimeMillis = System.currentTimeMillis();
	 System.out.println("currentTimeMillis:"+currentTimeMillis);
	 long t= 1586754344991L;
	 long t1=1586755121102L;
	 Long t2=1586755426771L;
	 System.out.println(t);
	 Date d = new Date(t2);
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 String format = sdf.format(d);
	 System.out.println(format);
	 
	 
	 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	   Date d1 = sdf1.parse("2020-4-13");
	  long start= d1.getTime();
	  System.out.println("Start:"+start);
	  System.out.println("Start:"+(start<=t2));
	  Date parse = sdf.parse("2020-4-13 23:59:59");
	  long end = parse.getTime();
	  System.out.println("end:"+end);
	  System.out.println("end:"+(end>=t2));
	  
	 String strDate = getStrDate(t1, "yyyy-mm-dd");
	 System.out.println("strDate:"+strDate);
	 long time20= getLongDate("2020-4-13 23:59:59", "");
	 System.out.println(time20);
	}
	/**
	 * 根据long类型的时间获取对应的String类型的日期
	 * @param time long 类型的日期
	 * @param format 格式 默认yyyy-MM-dd HH:mm:ss
	 * @return 返回字符串日期
	 */
	public static String getStrDate(long time,String format) {
		if(StringUtils.isEmpty(format)) {
			format="yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat(format);
		Date date = new Date(time);
		String format2 = sdf1.format(date);
		return format2;
		
	}
	/**
	 * 根据String类型的日期获取对应的Long日期类型
	 * @param date 字符串日期
	 * @param format 格式 默认yyyy-MM-dd HH:mm:ss
	 * @return 返回Long类型的日期
	 */
	public static Long getLongDate(String date,String format) {
		if(StringUtils.isEmpty(format)) {
			format="yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat(format);
		  try {
			Date d1 = sdf1.parse(date);
		    return d1.getTime();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
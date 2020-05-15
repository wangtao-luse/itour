package com.itour.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
/**
 * 将日期存储转换为long类型用于存储到mysql数据库中Int的字段中
 * @param date
 * @return
 */
public static long getlongDate(Date date) {
	long time = date.getTime()/1000;
	return time;
}
/**
 * 展示时需要*1000在进行转换
 * @param t
 * @return
 */
public static long longDate(long t) {
	Date date= new Date(t*1000);
	long time = date.getTime();
	return time;
}
/**
 * 返回当前日期的的long形式日期
 * @return
 */
public static long currentLongDate() {
	Date date= new Date();
	long time = date.getTime();
	return time;
}
/**
 * 将日期转换为以毫米数
 * @param date 开始日期
 * @return 以毫秒为来单位的时间
 */
public static long getMillis(Date date) {
	Calendar c = Calendar.getInstance();
	c.setTime(date);
	return c.getTimeInMillis();
}
/**
 * 获取指定日期后N天的日期
 * @param date 开始日期
 * @param day  天数
 * @return 指定日期起N天前或后的日期
 */
public static Date addDate(Date date, int day) {
	Calendar c = Calendar.getInstance();

	c.setTimeInMillis(getMillis(date) + (day) * 86400000L);
	return c.getTime();
}
/**
 * 获取指定日期后的N分钟后的日期
 * @param date 开始日期
 * @param minute 分钟数
 * @return  开始日期后的N分钟前或后的日期
 */
public static Date addMinute(Date date, int minute) {
	Calendar c = Calendar.getInstance();
	c.setTime(date);
	c.add(Calendar.MINUTE, minute);
	return c.getTime();
}
/**
 * 返回指定日期N年后的日期
 * @param date 开始日期
 * @param year 年数
 * @return  返回从开始日期起N年以前或后的日期
 */
public static Date addYear(Date date, int year) {
	Calendar calender = Calendar.getInstance();
	calender.setTime(date);
	calender.add(1, year);
	return calender.getTime();
}
/**
 * 返回指定日期N个月后的日期
 * @param date  开始日期
 * @param month 月份数
 * @return  开始日期起N个月前或后的日期
 */
public static Date addMonth(Date date, int month) {
	Calendar calender = Calendar.getInstance();
	calender.setTime(date);
	calender.add(2, month);
	return calender.getTime();
}
/**
 * 返回指定日期N小数后的日期
 * @param date 开始日期
 * @param hour 小数数
 * @return 开始日期起N小时前或后的日期
 */
public static Date addHour(Date date, int hour) {
	Calendar calender = Calendar.getInstance();
	calender.setTime(date);
	calender.add(Calendar.HOUR, hour);
	return calender.getTime();
}
/**
 * 
 * @param date 开始日期
 * @param second 秒数
 * @return 开始日期起N秒前或后的日期
 */
public static Date addSecond(Date date, int second) {
	Calendar calender = Calendar.getInstance();
	calender.setTime(date);
	calender.add(Calendar.SECOND, second);
	return calender.getTime();
}
/**
 * 计算两个日期之间相差多少秒
 * @param date1
 * @param date2
 * @return
 */
public static long getDifferOfSecond(Date date1,Date date2){
	Calendar cal1 = Calendar.getInstance();
	Calendar cal2 = Calendar.getInstance();
	cal2.setTime(date2);
	cal1.setTime(date1);
	return ((cal1.getTimeInMillis()-cal2.getTimeInMillis())/1000);
}
/**  
 * 计算两个日期之间相差的天数  
 * @param smdate 较小的时间 
 * @param bdate  较大的时间 
 * @return 相差天数 
 * @throws ParseException  
 */    
public static int getDifferOfDay(Date smdate,Date bdate) throws ParseException  {    
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
    smdate=sdf.parse(sdf.format(smdate));  
    bdate=sdf.parse(sdf.format(bdate));  
    Calendar cal = Calendar.getInstance();    
    cal.setTime(smdate);    
    long time1 = cal.getTimeInMillis();                 
    cal.setTime(bdate);    
    long time2 = cal.getTimeInMillis();         
    long between_days=(time2-time1)/(1000*3600*24);  
   return Integer.parseInt(String.valueOf(between_days));           
} 
/**
 * 计算两个日期之间相差多少分钟
 * @param date1
 * @param date2
 * @return
 */
public static long getDifferOfMinute(Date date1,Date date2){
	Calendar cal1 = Calendar.getInstance();
	Calendar cal2 = Calendar.getInstance();
	cal2.setTime(date2);
	cal1.setTime(date1);
	return ((cal1.getTimeInMillis()-cal2.getTimeInMillis())/(1000*60));
}
/**
 * 通过时间秒毫秒数判断两个时间的间隔
 * 直接通过计算两个日期的毫秒数，他们的差除以一天的毫秒数，即可得到我们想要的两个日期相差的天数。
 * @param date1
 * @param date2
 * @return
 */
public static int differentDaysByMillisecond(Date date1,Date date2){
	int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
	return days;
}
/**
 * <p>@describe: 时间对比  </P>
 * <p>@param date1
 * <p>@param date2
 * <p>@return -1：date1小于 date2  0：相等  1： date1大于 date2 </P>
 */
public static int compareDate(Date date1,Date date2){
	 return date1.compareTo(date2);

}
/**
 * <p>@describe: 比较时间大小  </P>
 * <p>@param data1  时间1
 * <p>@param data2   时间2
 * <p>@param patternString   日期格式化
 * <p>@return 大于等于返回true  小于-false </P>
 */
public static boolean compareDate(String data1,String data2,String patternString){
	DateFormat df = new SimpleDateFormat(patternString);//创建日期转换对象
	boolean b = true;
	try {
		Date dt1 = df.parse(data1);//将字符串转换为date类型
		Date dt2 = df.parse(data2);
		if(dt1.getTime()<dt2.getTime())//比较时间大小,如果dt1大于dt2
		{
			b=false;
		}
		
	} catch (ParseException e) {
	}
	return b;
	 
}
/**
 * 方法 getBetweenDays 得到两个日期的间隔天数
 * 
 * @param endDate
 *            时间字符串1，如：2001-07-17 11:00:00
 * @param startDate
 *            时间字符串2，如：2001-12-31 11:00:00
 * @return long 间隔天数(long)
 */
public static long getBetweenDays(String endDate, String startDate) {
	if (endDate == null || startDate == null)
		return -1;
	if (endDate.length() < 10 || startDate.length() < 10)
		return -1;
	int startYear = 2001, startMonth = 12, startDay = 31;
	int endYear = 2001, endMonth = 12, endDay = 31;

	try {
		startYear = Integer.parseInt(endDate.substring(0, 4));
		startMonth = Integer.parseInt(endDate.substring(5, 7));
		startDay = Integer.parseInt(endDate.substring(8, 10));
		endYear = Integer.parseInt(startDate.substring(0, 4));
		endMonth = Integer.parseInt(startDate.substring(5, 7));
		endDay = Integer.parseInt(startDate.substring(8, 10));
	} catch (NumberFormatException e) {
	} catch (Exception e) {
	}

	Calendar startCalendar = Calendar.getInstance();
	Calendar endCalendar = Calendar.getInstance();
	startCalendar.set(startYear, startMonth - 1, startDay, 1, 0, 0);
	endCalendar.set(endYear, endMonth - 1, endDay, 0, 0, 0);
	Date endDateTemp = startCalendar.getTime();
	Date startDateTemp = endCalendar.getTime();
	long startTime = endDateTemp.getTime();
	long endTime = startDateTemp.getTime();
	long times = startTime - endTime;
	// long days = Math.abs(times / ( 1000 *
	// 3600 * 24
	// ));
	long days = times / (1000 * 3600 * 24);
	return days;
}
/**
 * <p>@describe: 获取两个日期的相差天数  </P>
 * <p>@param early
 * <p>@param late
 * <p>@return  </P>
 */
public static final int daysBetween(Date early, Date late) { 
     
    java.util.Calendar calst = java.util.Calendar.getInstance();   
    java.util.Calendar caled = java.util.Calendar.getInstance();   
    calst.setTime(early);   
     caled.setTime(late);   
     //设置时间为0时   
     calst.set(java.util.Calendar.HOUR_OF_DAY, 0);   
     calst.set(java.util.Calendar.MINUTE, 0);   
     calst.set(java.util.Calendar.SECOND, 0);   
     caled.set(java.util.Calendar.HOUR_OF_DAY, 0);   
     caled.set(java.util.Calendar.MINUTE, 0);   
     caled.set(java.util.Calendar.SECOND, 0);   
    //得到两个日期相差的天数   
     int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
            .getTime().getTime() / 1000)) / 3600 / 24;   
     
    return days;   
}   

public static void main(String[] args) {
	long getlongDate = DateUtil.getlongDate(new Date());
	System.out.println(getlongDate);//1589436700
									//1589438064
	long longDate = DateUtil.longDate(getlongDate);
	System.out.println(longDate);
}
}

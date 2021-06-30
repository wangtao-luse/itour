package com.itour.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
private static final String FMT_DATE ="yyyy-MM-dd";
private static final String FMT_TIME ="HH:mm:ss";
private static final String FMT_DATETIME ="yyyy-MM-dd HH:mm:ss";
private static final String FMT_YEAR ="yyyy";
private static final String FMT_MONTH ="MM";
private static final String FMT_DAY ="dd";
private static final String FMT_MONTH_DAY ="MM-dd";
/**
 * 返回当前日期的long类型日期
 * @return long
 */
public static long currentLongDate() {
	Date date= new Date();
	long time = date.getTime();
	return time;
}
/**
 * 返回指定日期long类型日期
 * @param date
 * @return long
 */
public static long getlongDate(Date date) {
	long time = date.getTime();
	return time;
}
/**
 * 计算给定时间和当前时间相差多少时间
 * 
 * @param sDate
 * @return
 * @throws ParseException 
 */
public static String getDateStr(Date sDate) throws ParseException  {
	Date eDate = new Date();
	long differOfSecond = getDifferOfSecond(sDate,eDate);
	long differOfMinute = getDifferOfMinute(sDate,eDate);
	long differOfHour = getDifferOfHour(sDate,eDate);
	int differOfDay = getDifferOfDay(sDate,eDate);
	int differOfMonth = getDifferOfMonth(sDate,eDate);
	int differOfYear = getDifferOfYear(sDate,eDate);
	if(differOfMinute<1) {
		return differOfSecond+"秒前";
	}else if(differOfMinute<60) {
		return differOfMinute+"分钟前";
	}else if(differOfHour<24) {
		return differOfHour+"小时前";
	}else if(differOfDay<8) {
		return differOfDay+"天前";
	}else if(differOfYear<1) {
		return DateUtil.getStrDate(sDate, DateUtil.FMT_MONTH_DAY);
	}else {
		return differOfYear+"年前";
	}
}
/**
 * 计算两个日期之间相差多少秒
 * @param sDate 较小日期
 * @param eDate 较大日期
 * @return
 */
public static long getDifferOfSecond(Date sDate,Date eDate){
	Calendar sCalendar = Calendar.getInstance();
	Calendar eCalendar = Calendar.getInstance();
	eCalendar.setTime(eDate);
	sCalendar.setTime(sDate);
	return ((eCalendar.getTimeInMillis()-sCalendar.getTimeInMillis())/1000);
}
/**
 * 计算两个日期之间相差多少分钟
 * @param sDate 较小日期
 * @param eDate 较大日期
 * @return  
 */
public static long getDifferOfMinute(Date sDate,Date eDate){
	Calendar sCalendar = Calendar.getInstance();
	Calendar eCalendar = Calendar.getInstance();
	eCalendar.setTime(eDate);
	sCalendar.setTime(sDate);
	return ((eCalendar.getTimeInMillis()-sCalendar.getTimeInMillis())/(1000*60));
}
/**
 * 计算两个日期之间相差多少小时
 * @param sDate 较小日期
 * @param eDate 较大日期
 * @return  
 */
public static long getDifferOfHour(Date sDate,Date eDate){
	Calendar sCalendar = Calendar.getInstance();
	Calendar eCalendar = Calendar.getInstance();
	eCalendar.setTime(eDate);
	sCalendar.setTime(sDate);
	return ((eCalendar.getTimeInMillis()-sCalendar.getTimeInMillis())/(1000*60*60));
}
/**  
 * 计算两个日期之间相差的天数  
 * @param sDate 较小的时间 
 * @param eDate  较大的时间 
 * @return 相差天数 
 * @throws ParseException  
 */    
public static int getDifferOfDay(Date sDate,Date eDate) throws ParseException  {    
    Calendar cal = Calendar.getInstance();    
    cal.setTime(sDate);    
    long stime = cal.getTimeInMillis();                 
    cal.setTime(eDate);    
    long etime = cal.getTimeInMillis();         
    long between_days=(etime-stime)/(1000*3600*24);  
   return Integer.parseInt(String.valueOf(between_days));           
} 
/**
 * 计算两个日期相差的月数
 * @param sDate 较小的日期
 * @param eDate 较大的日期
 * @return
 * @throws ParseException
 */
public static int getDifferOfMonth(Date sDate,Date eDate) throws ParseException  {    
	Calendar sCalendar = Calendar.getInstance();
	Calendar eCalendar = Calendar.getInstance();
	sCalendar.setTime(sDate);
	eCalendar.setTime(eDate);
	int year =eCalendar.get(Calendar.YEAR)-sCalendar.get(Calendar.YEAR);
	//开始日期若小月结束日期
	if(year<0){
	year=-year;
	return year*12+sCalendar.get(Calendar.MONTH)-eCalendar.get(Calendar.MONTH);
	}
	return year*12+eCalendar.get(Calendar.MONTH)-sCalendar.get(Calendar.MONTH);
} 
/**
 * 计算两个日期相差的年数
 * @param sDate 较小日期
 * @param eDate 较大日期
 * @return 
 * @throws ParseException
 */
public static int getDifferOfYear(Date sDate,Date eDate) throws ParseException  {    
	Calendar cal1 = Calendar.getInstance();
	Calendar cal2 = Calendar.getInstance();
	cal2.setTime(eDate);
	cal1.setTime(sDate);
	int sYear = cal1.get(Calendar.YEAR);
	int eYear = cal2.get(Calendar.YEAR);
	int year = eYear - sYear;
	return   year; 
} 
/**
 * 将Date型日期转为对应的pattern格式的日期
 * @param date
 * @param pattern
 * @return
 */
public static String getStrDate(Date date,String pattern) {
	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	  String format = sdf.format(date);
	return format;
}












/****--------------------------------------------------------------------------------为使用过的方法---------------------*/








/**
 * 将日期转换为对应的毫秒数
 * @param date 日期
 * @return 以毫秒为来单位的时间 long
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

/**
 * 获取当天的开始时间 如2020-9-22 00:00
 * @return
 * @throws ParseException
 */
public static long getMidnight() throws ParseException {
    //1.得到long类型的当前时间
	long current = DateUtil.getlongDate(new Date());
	//2.将当前日期格式化为yyyy-MM-dd
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String format = sdf.format(new Date(current));
	System.out.println(format);
	//3.将日格式化的日期字符串转为long类型日期
	Date parse = sdf.parse(format);
	long time = parse.getTime();
	return time;
	
	
}
/**
 * 获取本周的第一天
 * @return String
 * @throws ParseException 
 * **/
public static Date getWeekStart() throws ParseException{
    Calendar cal=Calendar.getInstance();
    cal.add(Calendar.WEEK_OF_MONTH, 0);
    cal.set(Calendar.DAY_OF_WEEK, 2);
    Date time=cal.getTime();
    return time;
}
/**
 * 获取本周的最后一天
 * @return String
 * @throws ParseException 
 * **/
public static Date getWeekEnd() throws ParseException{
    Calendar cal=Calendar.getInstance();
    cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
    cal.add(Calendar.DAY_OF_WEEK, 1);
    Date time=cal.getTime();
   
    return time;
}

public static void main(String[] args) throws ParseException {
	//得到long类型的当前时间
	long longDate = DateUtil.getlongDate(new Date());
	//System.out.println(getlongDate);//1589436700
									//1589438064
									//1691766000
	Date d = new Date(longDate);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	String format = sdf.format(d);
	//System.out.println(format);
	Date parse = sdf.parse("2023-08-11 23:00");
	long time = DateUtil.getlongDate(parse);
	//System.out.println("指定日期的long格式"+time);
	testDate();
	
}
public static void test() {
	//1.获取当前时间
	Date now = new Date();
	long time = now.getTime();	
	System.out.println(time);
	
	
}
/**
 * 测试连个日期之间的间隔
 * @throws ParseException 
 */
public static void testDate() throws ParseException {
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	//1.当前时间
	Date current = new Date();
    //2.开始时间
	Date stime = sdf1.parse("2016-07-25 08:30:00");
	//3.结束时间 
	Date etime = sdf1.parse("2022-06-30 23:00:00");
	long differOfSecond = DateUtil.getDifferOfSecond(stime,etime);
	System.out.println("相差 "+differOfSecond+"秒");
	long differOfMinute = DateUtil.getDifferOfMinute(stime,etime);
	System.out.println("相差 "+differOfMinute+"分钟");
	long differOfHour = DateUtil.getDifferOfHour(stime, etime);
	System.out.println("相差 "+differOfHour+"小时");
	int differOfDay = DateUtil.getDifferOfDay(stime, etime);
	System.out.println("相差 "+differOfDay+"天");
	int differOfMonth = DateUtil.getDifferOfMonth(stime,etime);
	System.out.println("相差 "+differOfMonth+"个月");
	int differOfYear = DateUtil.getDifferOfYear(stime, etime);
	System.out.println("相差 "+differOfYear+"年");
	
	String dateStr = DateUtil.getDateStr(stime);
	System.out.println(dateStr);
}
}

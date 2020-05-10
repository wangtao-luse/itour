package com.itour.util;

import java.util.Date;

public class DateUtil {
/**
 * 将日期存储转换为long类型用于存储到mysql数据库中,	
 * @param date
 * @return
 */

public static long getlongDate(Date date) {
	long time = date.getTime()/1000;
	return time;
}

}

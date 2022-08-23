package com.itour.common.redis;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.itour.help.DateHelper;
import com.itour.util.IpUtil;

public class RedisKeyManager {
/** 
 * 独立IP数也称IP数，指1天内使用不同IP地址的用户访问网站的数量，同一IP无论访问了几个页面，独立IP数均为1;
 * @return 根据文章Id返回文章独立IP的值 Key如 key=192.168.1.8::2022-6-22::1
 */
public static String key_ip(HttpServletRequest request ,String id) {
	 //1.组装key,ip::yyyy-MM-dd::文章Id
	 String strDate = DateHelper.getStrDate(new Date(), "yyyy-MM-dd");
	 String ipAddr = IpUtil.getIpAddr(request);
	 String key=ipAddr+"::"+strDate+"::"+id;
return key;	
}
}

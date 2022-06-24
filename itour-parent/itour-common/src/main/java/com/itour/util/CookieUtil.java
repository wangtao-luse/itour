package com.itour.util;

import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CookieUtil {
public static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);
public static final String VISIT_COOKIE_NAME = "visit.wangtao.club";
/**
 * 获取指定name的Cookie
 * @param request
 * @param cookieName  
 * @return
 */
public static String getCookieValue(HttpServletRequest  request, String cookieName) {
	Cookie[] cookies = request.getCookies();
	for (Cookie cookie : cookies) {
		if(cookieName.equals(cookie.getName())) {
			return  cookie.getValue();
		}
	}
	return null;
}
/**
 * 设置cookie的value
 * @param value
 * @return
 */
public static String setCookieValue(HttpServletRequest  request,HttpServletResponse response,String cookName,String value) {
	Cookie cookie = new Cookie(cookName, value);
	int cookieDeath = getCookieDeath();
	if (cookieDeath > 0) {
		cookie.setMaxAge(cookieDeath);
	}
	if (null != request) {
		String domainName = getDomainName(request);
		logger.info("domainName:"+domainName);
		 if (!"localhost".equals(domainName)) {
             cookie.setDomain(domainName);
         }
	}
	cookie.setPath("/");
	response.addCookie(cookie);
	return cookie.getValue();
}
/**
 * 获取cookie失效时间
 * @return
 */
public static int getCookieDeath(){
    // 获取当前时间戳
    long now = Calendar.getInstance().getTimeInMillis();

    // 通过 Calendar 手动设置时间
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY,23);
    calendar.set(Calendar.MINUTE,59);
    calendar.set(Calendar.SECOND,59);

    // 获取当日 23:59:59 的时间戳
    long death = calendar.getTimeInMillis();

    // 计算过期时间
    // 注意：cookie 过期时间的单位为秒
    int cookieMaxAge = (int) ((death-now)/1000);

    return cookieMaxAge;
}
private static final String getDomainName(HttpServletRequest request) {
    String domainName = null;

    String serverName = request.getRequestURL().toString();
    if (serverName == null || serverName.equals("")) {
        domainName = "";
    } else {
        serverName = serverName.toLowerCase();
        serverName = serverName.substring(7);
        final int end = serverName.indexOf("/");
        serverName = serverName.substring(0, end);
        if (serverName.indexOf(":") > 0) {
            String[] ary = serverName.split("\\:");
            serverName = ary[0];
        }

        final String[] domains = serverName.split("\\.");
        int len = domains.length;
        if (len > 3 && !isIp(serverName)) {
            // www.xxx.com.cn
            domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
        } else if (len <= 3 && len > 1) {
            // xxx.com or xxx.cn
            domainName = "." + domains[len - 2] + "." + domains[len - 1];
        } else {
            domainName = serverName;
        }
    }
    return domainName;
}
public static boolean isIp(String IP){//判断是否是一个IP
    boolean b = false;
    IP = trimSpaces(IP);
    if(IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")){
        String s[] = IP.split("\\.");
        if(Integer.parseInt(s[0])<255)
            if(Integer.parseInt(s[1])<255)
                if(Integer.parseInt(s[2])<255)
                    if(Integer.parseInt(s[3])<255)
                        b = true;
    }
    return b;
}
public static String trimSpaces(String IP){//去掉IP字符串前后所有的空格
    while(IP.startsWith(" ")){
        IP= IP.substring(1,IP.length()).trim();
    }
    while(IP.endsWith(" ")){
        IP= IP.substring(0,IP.length()-1).trim();
    }
    return IP;
}

}

package com.itour.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.itour.common.redis.RedisManager;
import com.itour.constant.RedisKey;
import com.itour.util.CookieUtil;
import com.itour.util.DateUtil;
import com.itour.util.IpUtil;
import com.itour.util.SessionUtil;
import com.itour.util.StringHelper;
@Component
public class WebsiteInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(WebsiteInterceptor.class);
//https://blog.csdn.net/upordown6263/article/details/125280173	
	@Autowired
private RedisManager redisManager;
 @Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
	// TODO Auto-generated method stub
	 /**
		 * 1.pv(浏览量):页面浏览量,同一页面多次打开浏览量累计;
		 * 2.ip(独立ip):1天内使用不同IP地址的用户访问网站的数量,同一IP无论访问了网站里的多少个页面，独立IP数均为1。
		 * 3.vv(访问次数):从访客来到您网站到最终关闭网站的所有页面离开，计为1次访问。若访客连续30分钟没有新开和刷新页面，或者访客关闭了。
		 * 4.(uv)独立访客:1天内相同的访客多次访问您的网站只计算1个UV，以cookie为依据。
		 */
	 //pv
	websitePvCount();
	//uv
	websiteUvCount(request,response);
	//ip
	websiteIpCount(request);
	//vv
	websiteVvCount();
	return true;
}
 /**
  * 统计站点pv
  */
private long websitePvCount() {
	//统计站点pv
	 long incr = this.redisManager.incr(RedisKey.KEY_ITOUR_PV_COUNT, 1);
	 return incr;
}
/**
 * 统计站点uv
 * @param request
 * @param response
 */
private void websiteUvCount(HttpServletRequest request,HttpServletResponse response) {
	String cookieValue = CookieUtil.getCookieValue(request, CookieUtil.VISIT_COOKIE_NAME);
	if(StringUtils.isEmpty(cookieValue)) {//说明该用户机器(浏览器)当天没有访问过
		String uuid = StringHelper.getUuid();
		cookieValue = CookieUtil.setCookieValue(request,response,CookieUtil.VISIT_COOKIE_NAME, uuid);
		//统计站点uv
		this.redisManager.incr(RedisKey.KEY_ITOUR_UV_COUNT, 1);
	}
	
	
}
/**
 * 统计站点ip
 * 1.将iP存入Redis
 * 2.定时任务插入数据库
 * @param request
 * @param response
 */
private void websiteIpCount(HttpServletRequest request) {
	
	String ipAddress = IpUtil.getIpAddr(request);
	String key = ipAddress;
	String strDate = DateUtil.getStrDate(new Date(), DateUtil.FMT_DATETIME);
	if (!StringUtils.isEmpty(ipAddress)) {
		//判断Redis中是否存在
		 boolean isMember = this.redisManager.sisMember(RedisKey.KEY_ITOUR_IP_LIST, key);
		 if(!isMember) {
			 this.redisManager.sAdd(RedisKey.KEY_ITOUR_IP_LIST, key);
			 this.redisManager.sAdd(RedisKey.KEY_ITOUR_IP_LIST_DATE, ipAddress+"::"+strDate);
		 }
	}
	
}
/**
 * 访问次数（vv）
 * @param request
 */
private void websiteVvCount() {
	Session session = SessionUtil.getSession();
	Object attribute = session.getAttribute(SessionUtil.SESSION_VISIT_NAME);
	if(null ==attribute) {
		session.setAttribute(SessionUtil.SESSION_VISIT_NAME, StringHelper.getUuid());
		this.redisManager.incr(RedisKey.KEY_ITOUR_VV_COUNT, 1);
	}
}

}

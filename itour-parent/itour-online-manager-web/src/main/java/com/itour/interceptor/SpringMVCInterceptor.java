package com.itour.interceptor;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.itour.common.vo.AccountVo;
import com.itour.util.SessionUtil;


/**
 * 
 * @author ddd
 * @since 2017/11/16
 * 拦截所有的controller 统计时间
 *
 */
public class SpringMVCInterceptor implements HandlerInterceptor{
	

	private Logger logger=LoggerFactory.getLogger(SpringMVCInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			 AccountVo sessionUser = SessionUtil.getSessionUser();
			String host=getRemoteHost(request, response);
			request.setAttribute("startTime", System.currentTimeMillis());
			String redirectURL=WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
			request.setAttribute("host", host);
			request.setAttribute("redirectURL", redirectURL);
			request.setAttribute("kssj", "08:00 - 23:00");
			request.setAttribute("user", sessionUser);
			
			//判断当前的请求地址是否需要记录日志
			String servletPath=request.getServletPath();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		try {
			long startTime=(long) request.getAttribute("startTime");
			long endTime = System.currentTimeMillis();
			long executeTime = endTime - startTime;
			
			String host=this.getRemoteHost(request, response);//IP
			if(handler instanceof HandlerMethod){
				HandlerMethod handlerMethod=(HandlerMethod)handler;
				logger.info("controller : " + handlerMethod.getBeanType().getName() +";methodName : "+handlerMethod.getMethod().getName()+";SessionID : "+SessionUtil.getSession().getId()+";IP Addr : "+host+";Execute Time  : "+executeTime+"ms");
			}
			
			Object obj= request.getAttribute("controllerLog");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	/**
	 * 获取IP
	 * @param request
	 * @param response
	 * @return
	 */
	private String getRemoteHost(HttpServletRequest request, HttpServletResponse response){

	    String ip = request.getHeader("x-forwarded-for");

	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){

	        ip = request.getHeader("Proxy-Client-IP");

	    }

	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){

	        ip = request.getHeader("WL-Proxy-Client-IP");

	    }

	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){

	        ip = request.getRemoteAddr();

	    }

	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
}

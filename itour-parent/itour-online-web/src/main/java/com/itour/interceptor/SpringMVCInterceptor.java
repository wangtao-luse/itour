package com.itour.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.itour.common.vo.AccountVo;
import com.itour.util.SessionUtil;
import com.itour.util.ShiroFilterUtils;


/**
 * 
 * @author ddd
 * @since 2017/11/16
 * 拦截所有的controller 
 *
 */
@Component
public class SpringMVCInterceptor implements HandlerInterceptor{
	
	private Logger logger=LoggerFactory.getLogger(SpringMVCInterceptor.class);
  /**
   * 进入controller层之前拦截请求
   */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			 AccountVo sessionUser = SessionUtil.getSessionUser();
			String host=getRemoteHost(request, response);
			String redirectURL=WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
			request.setAttribute("startTime", System.currentTimeMillis());
			request.setAttribute("host", host);
			request.setAttribute("redirectURL", redirectURL);
			request.setAttribute("user", sessionUser);
			//判断当前的请求地址是否需要记录日志
			String servletPath=request.getServletPath();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
  /**
   * 访问controller之后 访问视图之前被调用
   */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		try {
			long startTime=(long) request.getAttribute("startTime");
			long endTime = System.currentTimeMillis();
			long executeTime = endTime - startTime;
			String host=this.getRemoteHost(request, response);//IP
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /**
     * 访问视图之后被调用
     */
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

	    return "0:0:0:0:0:0:0:1".equals(ip)?"127.0.0.1":ip;
	}
	
}

package com.itour.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
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
 * 拦截所有的controller 统计时间
 *
 */
@Component
public class SpringMVCInterceptor implements HandlerInterceptor{
	

	private Logger logger=LoggerFactory.getLogger(SpringMVCInterceptor.class);

	@Override
	//在业务处理器处理请求之前被调用,预处理，可以进行编码、安全控制、权限校验等处理
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			AccountVo sessionUser = SessionUtil.getSessionUser();
			String host=getRemoteHost(request, response);
			request.setAttribute("startTime", System.currentTimeMillis());
			String redirectURL=WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
			request.setAttribute("host", host);
			request.setAttribute("redirectURL", redirectURL);
			request.setAttribute("user", sessionUser);
			/*
			 * Subject subject = SecurityUtils.getSubject(); Session session =
			 * subject.getSession(false); String attribute =
			 * (String)session.getAttribute("userName"); boolean authenticated =
			 * subject.isAuthenticated(); boolean ajax = ShiroFilterUtils.isAjax(request);
			 * if(ajax&&attribute==null) {//session失效 ShiroFilterUtils.out(response); return
			 * false; }
			 */
			 
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
		/*
		 * 在业务处理器处理请求执行完成后，生成视图之前执行。
		 * 后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView
		 */
	}

	@Override
	//在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；
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

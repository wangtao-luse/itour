package com.itour.filter;


import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 自定义登录拦截器
 * @author ddd
 *
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

	private static final Logger log = LoggerFactory.getLogger(CustomFormAuthenticationFilter.class);


	/**
	 * 重新跳转逻辑
	 */
	/*
	 * protected void saveRequestAndRedirectToLogin(ServletRequest request,
	 * ServletResponse response) throws IOException { String requestUrl =
	 * getPathWithinApplication(request); requestUrl = URLEncoder.encode(requestUrl,
	 * "UTF-8"); setLoginUrl("/member/login" + "?redirectURL=" + requestUrl);
	 * super.saveRequestAndRedirectToLogin(request, response);
	 * 
	 * }
	 */

	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if (isLoginRequest(httpServletRequest, httpServletResponse)) {
			if (isLoginSubmission(httpServletRequest, httpServletResponse)) {
				if (log.isTraceEnabled()) {
					log.trace("Login submission detected.  Attempting to execute login.");
				}
				return executeLogin(httpServletRequest, httpServletResponse);
			} else {
				if (log.isTraceEnabled()) {
					log.trace("Login page view.");
				}
				return true;
			}
		} else {
			if (log.isTraceEnabled()) {
				log.trace("Attempting to access a path which requires authentication.  Forwarding to the "
						+ "Authentication url [" + getLoginUrl() + "]");
			}

			/**
			 * 如果是ajax提交，获取上次请求路径
			 */
			String header = httpServletRequest.getHeader("X-Requested-With");//
			String header2 = httpServletRequest.getHeader("accept");//application/json
			String requestURI = getPathWithinApplication(request);
			  if (isAjax(httpServletRequest)) { 
				  //Session是否失效			
				  Subject subject = getSubject(httpServletRequest, httpServletResponse);
				  if(null==subject.getPrincipal()&&isAjax(httpServletRequest)) {
					  System.out.println(subject.getPrincipal());
					  httpServletResponse.setCharacterEncoding("UTF-8"); //在响应头设置session状态
					  httpServletResponse.setHeader("session-status", "timeout"); 
				  }
			  } else {
	                    response.setContentType("text/html;charset=UTF-8");
	                    PrintWriter out = response.getWriter();
	                    out.println("<script language='javascript' type='text/javascript'>");
	                    out.println("alert('由于你长时间没有操作,导致Session失效!请你重新登录!');top.location.href='"+httpServletRequest.getContextPath()+"/member/login'");
	                    out.println("</script>");
	                    out.close();
	                    out.flush();
			  }
			 
			
		}
		return false;
	}
	private boolean isAjax(HttpServletRequest request) {
		return request.getHeader("X-Requested-With") != null&&request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1;
		
	}

}

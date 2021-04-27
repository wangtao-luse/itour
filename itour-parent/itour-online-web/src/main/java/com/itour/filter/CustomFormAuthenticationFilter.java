package com.itour.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;


/**
 * 自定义登录拦截器
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

	private static final Logger log = LoggerFactory.getLogger(CustomFormAuthenticationFilter.class);


	/**
	 * 重新跳转逻辑
	 */
	protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		String requestUrl = getPathWithinApplication(request);
		requestUrl = URLEncoder.encode(requestUrl, "UTF-8");
		setLoginUrl("/member/login" + "?redirectURL=" + requestUrl);
		super.saveRequestAndRedirectToLogin(request, response);

	}

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
				// allow them to see the login page ;)
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
			if (!(httpServletRequest.getHeader("accept").indexOf("application/json") > -1
					|| (httpServletRequest.getHeader("X-Requested-With") != null
							&& (httpServletRequest).getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
				saveRequestAndRedirectToLogin(request, response);

			} else {
				PrintWriter out = null;
				try {
					String lastRequestUrl = httpServletRequest.getHeader("Referer");
					
					String contextPath = httpServletRequest.getContextPath();
					
					lastRequestUrl = lastRequestUrl.substring(lastRequestUrl.indexOf(contextPath) + contextPath.length());
					
					lastRequestUrl = URLEncoder.encode(lastRequestUrl, "UTF-8");
					ResponseMessage responseMessage = new ResponseMessage();
					responseMessage.setResultCode(Constant.LOGIN);
					responseMessage.setResultMessage("请先登录！");
					responseMessage.setReturnResult("/account/login" + "?redirectURL=" + lastRequestUrl);

					String jsonStr = JSON.toJSONString(responseMessage);
					response.setContentType("text/json;charset=UTF-8");
					out = response.getWriter();
					out.print(jsonStr);
					out.flush();
				} catch (Exception e) {
					throw e;
				}finally {
					if(out!=null)out.close();
				}
			}

			return false;
		}
	}

}

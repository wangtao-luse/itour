package com.itour.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;




/***
 * 全局异常捕获
 * @author wwang
 *
 */

@ControllerAdvice
public class DefaultExceptionHandler {
	protected Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	/**
	 * <p/>
	 * 后续根据不同的需求定制即可
	 */
	@ResponseBody
	@ExceptionHandler({ Throwable.class })	
	public ModelAndView processUnauthenticatedException(HttpServletRequest request, HttpServletResponse response,
			Throwable ex) {
		String header = request.getHeader("X-Requested-With");
		String accep = request.getHeader("accept");
		if(this.isAjax(request)) {//ajax请求
			return null;
		}else {//普通请求
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("/exception");
			
			return mv;
		}
		
	}
	/**
	 * 判断请求是否是Ajax异步请求方式
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		//request.getHeader("X-Requested-With")为 null，则为传统同步请求，
	    //为 XMLHttpRequest，则为 Ajax 异步请求。		
		return header!=null&&"X-Requested-With".equals(header);
	}
	public static boolean isAcceptJson(HttpServletRequest request) {
		String header = request.getHeader("accept");
		return header.equals("application/json");
	}
}

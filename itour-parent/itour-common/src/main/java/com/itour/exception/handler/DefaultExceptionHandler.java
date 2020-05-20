package com.itour.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.itour.common.resp.ResponseMessage;
import com.itour.exception.BaseException;

@ControllerAdvice
public class DefaultExceptionHandler {
	public Object defaultExceptionHander(HttpServletRequest request,HttpServletResponse response,Throwable ex) {
		if(isAjax(request)) {
		  if(ex instanceof BaseException) {
			  return ResponseMessage.getFailed(ex.getMessage());
		  }else {
			  
		  }
		}else {
			ModelAndView mv = new ModelAndView();
		}
		
		return null;
		
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
}

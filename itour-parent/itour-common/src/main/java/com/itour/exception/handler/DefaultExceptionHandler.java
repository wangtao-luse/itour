package com.itour.exception.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itour.entity.ResponseEntity;
import com.itour.util.JsonUtil;




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
	public static ModelAndView processUnauthenticatedException(HttpServletRequest request, HttpServletResponse response,
			Throwable ex) {
		String header = request.getHeader("X-Requested-With");
		String accep = request.getHeader("accept");
		if(!(isAjax(request)||isAcceptJson(request))) {
			ResponseEntity responseEntity = ResponseEntity.from(ex);
			ModelAndView mv = new ModelAndView();
			mv.addObject("error", responseEntity);
			mv.setViewName(responseEntity.getCallbackUrl());
			return mv;
		}else {			
			PrintWriter out = null;
			try {
				ResponseEntity responseEntity = ResponseEntity.from(ex);
				
				String jsonStr = JsonUtil.pojo2JsonStr(responseEntity);
				response.setContentType("text/json;charset=UTF-8");
				out = response.getWriter();
				out.print(jsonStr);
				out.flush();
			} catch (IOException e) {
				ModelAndView mv = new ModelAndView();
				mv.addObject("error", e);
				mv.setViewName("cmdty/error/exception");
			}
			return null;
			
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
		return header!=null&&header.indexOf("X-Requested-With")>-1;
	}
	public static boolean isAcceptJson(HttpServletRequest request) {
		String header = request.getHeader("accept");//浏览器可接受的MIME类型；
		return header.indexOf("application/json")>-1;
	}
	 
}

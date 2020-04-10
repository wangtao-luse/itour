package com.itour.exception.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.itour.entity.ResponseEntity;
import com.itour.util.JsonUtil;


@ControllerAdvice
public class DefaultExceptionHandler {
	protected Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	/**
	 * <p/>
	 * 后续根据不同的需求定制即可
	 */
	@ExceptionHandler({ Throwable.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ModelAndView processUnauthenticatedException(HttpServletRequest request, HttpServletResponse response,
			Throwable ex) {
		logger.error("DefaultExceptionHandler:", ex);

		if (!(request.getHeader("accept").indexOf("application/json") > -1
				|| (request.getHeader("X-Requested-With") != null
						&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			// 根据不同错误转向不同页面
			ResponseEntity responseEntity = ResponseEntity.from(ex);
			ModelAndView mv = new ModelAndView();
			mv.addObject("error", responseEntity);
			mv.setViewName(responseEntity.getCallbackUrl());
			return mv;
		} else {
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
}

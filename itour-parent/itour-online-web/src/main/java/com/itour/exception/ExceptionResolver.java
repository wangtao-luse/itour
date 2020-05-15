package com.itour.exception;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
/**
 * springmvc全局异常处理
 * @author wwang
 *
 */
@Configuration
public class ExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		String servletPath = request.getServletPath();
		String requestType = request.getHeader("X-Requested-With");
		//如果requestType能拿到值，并且值为 XMLHttpRequest ,表示客户端的请求为异步请求，那自然是ajax请求了，
		//   反之如果为null,则是普通的请求 
		if(null!=requestType&&requestType.indexOf("XMLHttpRequest")!=-1) {
			try {
				response.setContentType("applciation/json;charset=utf-8");
				ResponseMessage responseMessage=new ResponseMessage();
				if(ex instanceof BaseException) {
					responseMessage=ResponseMessage.getFailed(ex.getMessage());
				}
				PrintWriter pw = response.getWriter();
				pw.write(JSONObject.toJSONString(responseMessage));
				pw.flush();
				pw.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}else {//普通请求
			
		}
		return null;
	}

}

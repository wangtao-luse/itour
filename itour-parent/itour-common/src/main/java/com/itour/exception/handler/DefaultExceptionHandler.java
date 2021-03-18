package com.itour.exception.handler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;




/***
 * 捕获服务提供者
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
	public static Object processUnauthenticatedException(HttpServletRequest request, HttpServletResponse response,
			Throwable ex) {
			if (ex instanceof BaseException) {
				ex.printStackTrace();
				return ResponseMessage.getFailed(ex.getMessage());
			} else if (ex instanceof AuthorizationException){
				ex.printStackTrace();
				return ResponseMessage.getFailed(Constant.FAILED_NOAUTHOR);
			}else{
				ex.printStackTrace();
				return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
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
		return header!=null&&header.indexOf("XMLHttpRequest")>-1;
	}
	public static boolean isAcceptJson(HttpServletRequest request) {
		String header = request.getHeader("accept");//浏览器可接受的MIME类型；
		return header.indexOf("application/json")>-1;
	}
	/**
	 * <p/>
	 * 后续根据不同的需求定制即可
	 */
	/*
	 * @ExceptionHandler({ Throwable.class })
	 * 
	 * @ResponseStatus(HttpStatus.UNAUTHORIZED)
	 * 
	 * @ResponseBody public ModelAndView
	 * processUnauthenticatedException(HttpServletRequest request,
	 * HttpServletResponse response, Throwable ex) {
	 * logger.error("DefaultExceptionHandler:", ex);
	 * 
	 * if (!(request.getHeader("accept").indexOf("application/json") > -1 ||
	 * (request.getHeader("X-Requested-With") != null &&
	 * request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) { //
	 * 根据不同错误转向不同页面 ResponseEntity responseEntity = ResponseEntity.from(ex);
	 * ModelAndView mv = new ModelAndView(); mv.addObject("error", responseEntity);
	 * mv.setViewName(responseEntity.getCallbackUrl()); return mv; } else {
	 * PrintWriter out = null; try { ResponseEntity responseEntity =
	 * ResponseEntity.from(ex); String jsonStr =
	 * JsonUtil.pojo2JsonStr(responseEntity);
	 * response.setContentType("text/json;charset=UTF-8"); out =
	 * response.getWriter(); out.print(jsonStr); out.flush(); } catch (IOException
	 * e) { ModelAndView mv = new ModelAndView(); mv.addObject("error", e);
	 * mv.setViewName("cmdty/error/exception"); } return null; } }
	 */
}

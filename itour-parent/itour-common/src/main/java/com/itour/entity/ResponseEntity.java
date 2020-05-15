package com.itour.entity;

import java.util.List;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.FieldError;

import com.csesteel.common.base.exception.BaseException;

/**
 * 用于AJAX提交的返回结果
 *
 */
public  class ResponseEntity {
	
	/**
	 * 异常页面
	 */
	public static final String DEFAULT_EXCEPTION_PAGE = "/error/exception.html";
	
	/**
	 * 没有权限提示页面
	 */
	public static final String DEFAULT_UNAUTH_PAGE = "/error/noAuth.html";

	/**
	 * 是否成功
	 */
	private boolean isSuccess;
	
	
	/**
	 * 提示信息
	 */
	private String message;
	
	/**
	 * 回跳页面
	 */
	private String callbackUrl;
	
	/**
	 * 错误的信息集
	 */
	private List<FieldError> errors;
	
	
	public static ResponseEntity from(Throwable e) {
		ResponseEntity responseEntity = new ResponseEntity();
		String errorMessage = convertMessage(e);
		String callbackUrl = convertUrl(e);
		responseEntity.setIsSuccess(false);
		responseEntity.setMessage(errorMessage);
		responseEntity.setCallbackUrl(callbackUrl);
		return responseEntity;
	}

	private static String convertMessage(Throwable e) {
		String errorMessage = e.getMessage();
		// 验证失败
		if (e instanceof BaseException) {
			errorMessage = errorMessage;
		} else if (e instanceof AuthorizationException){
			errorMessage = "用户权限不够，请先授权";
		}else{
			errorMessage = "系统错误，请联系管理员";
		}

		return errorMessage;
	}
	
	private static String convertUrl(Throwable e){
		String callbackUrl = "";
		if (e instanceof BaseException) {
			callbackUrl = ResponseEntity.DEFAULT_EXCEPTION_PAGE;
		}else if(e instanceof AuthorizationException){
			callbackUrl = ResponseEntity.DEFAULT_UNAUTH_PAGE;
		}else{
			callbackUrl = ResponseEntity.DEFAULT_EXCEPTION_PAGE;
		}
		
		
		return callbackUrl;
	}

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public List<FieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldError> errors) {
		this.errors = errors;
		this.isSuccess = false;
	}
	
}

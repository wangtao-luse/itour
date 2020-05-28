package com.itour.common.vo;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ExUsernamePasswordToken extends UsernamePasswordToken{
private HttpServletRequest request;

public ExUsernamePasswordToken(String username,String password,HttpServletRequest request) {
	super(username,password);
	this.request = request;
}

public HttpServletRequest getRequest() {
	return request;
}

public void setRequest(HttpServletRequest request) {
	this.request = request;
}

}

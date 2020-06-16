package com.itour.common.vo;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ExUsernamePasswordToken extends UsernamePasswordToken{
private HttpServletRequest request;
private String cname;
private String ip;


public ExUsernamePasswordToken(String username,String password,HttpServletRequest request,String cname,String ip) {
	super(username,password);
	this.request = request;
}
public HttpServletRequest getRequest() {
	return request;
}

public void setRequest(HttpServletRequest request) {
	this.request = request;
}

public String getCname() {
	return cname;
}

public void setCname(String cname) {
	this.cname = cname;
}

public String getIp() {
	return ip;
}

public void setIp(String ip) {
	this.ip = ip;
}

}

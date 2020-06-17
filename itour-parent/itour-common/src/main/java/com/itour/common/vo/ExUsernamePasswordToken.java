package com.itour.common.vo;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.UsernamePasswordToken;

import com.alibaba.fastjson.JSONObject;

public class ExUsernamePasswordToken extends UsernamePasswordToken{
private HttpServletRequest request;
private String cname;
private String ip;
private JSONObject jsonObject;


public ExUsernamePasswordToken(String username,String password,HttpServletRequest request,String ip,String cname,JSONObject jsonObject) {
	super(username,password);
	this.request = request;
}
public HttpServletRequest getRequest() {
	return request;
}

public void setRequest(HttpServletRequest request) {
	this.request = request;
}


public JSONObject getJsonObject() {
	return jsonObject;
}
public void setJsonObject(JSONObject jsonObject) {
	this.jsonObject = jsonObject;
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

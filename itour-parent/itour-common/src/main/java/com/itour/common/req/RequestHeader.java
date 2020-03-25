package com.itour.common.req;

import java.io.Serializable;
public class RequestHeader implements Serializable {
private static final long serialVersionUID = 1L;
private String functionMethod;//方法的url
private String requestURI;
private StringBuffer requestURL;
private String contextPath;
private String servletPath;
private String remoteAddr;
private String LocalAddr;
private String serverName;
private int serverPort;
private String scheme;
private String remoteHost;
private long time;
public String getFunctionMethod() {
	return functionMethod;
}
public void setFunctionMethod(String functionMethod) {
	this.functionMethod = functionMethod;
}
public String getRequestURI() {
	return requestURI;
}
public void setRequestURI(String requestURI) {
	this.requestURI = requestURI;
}
public StringBuffer getRequestURL() {
	return requestURL;
}
public void setRequestURL(StringBuffer requestURL) {
	this.requestURL = requestURL;
}
public String getContextPath() {
	return contextPath;
}
public void setContextPath(String contextPath) {
	this.contextPath = contextPath;
}
public String getServletPath() {
	return servletPath;
}
public void setServletPath(String servletPath) {
	this.servletPath = servletPath;
}
public String getRemoteAddr() {
	return remoteAddr;
}
public void setRemoteAddr(String remoteAddr) {
	this.remoteAddr = remoteAddr;
}
public String getLocalAddr() {
	return LocalAddr;
}
public void setLocalAddr(String localAddr) {
	LocalAddr = localAddr;
}
public String getServerName() {
	return serverName;
}
public void setServerName(String serverName) {
	this.serverName = serverName;
}
public int getServerPort() {
	return serverPort;
}
public void setServerPort(int serverPort) {
	this.serverPort = serverPort;
}
public String getScheme() {
	return scheme;
}
public void setScheme(String scheme) {
	this.scheme = scheme;
}
public String getRemoteHost() {
	return remoteHost;
}
public void setRemoteHost(String remoteHost) {
	this.remoteHost = remoteHost;
}
public long getTime() {
	return time;
}
public void setTime(long time) {
	this.time = time;
}

}

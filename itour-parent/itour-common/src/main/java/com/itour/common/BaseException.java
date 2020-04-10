package com.itour.common;

public class BaseException extends RuntimeException {
private static final long serialVersionUID = 1L;
private String message;
public BaseException() {}
public BaseException(String message) {
	super();
	this.message = message;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
}

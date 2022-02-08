package com.itour.common;

import java.util.ArrayList;
import java.util.List;

public class ReturnMessage<T> {
private String code;
private String msg;
private  T data;
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public T getData() {
	return data;
}
public void setData(T data) {
	this.data = data;
}

 public <T> List<T> check(List<T> t){
	return t;
}
 public <T> T check1(T t){
	 return t;
 }
public static void main(String[] args) {
	ReturnMessage m = new ReturnMessage();	
	List<ReturnMessage>  list = new ArrayList<ReturnMessage>();
    List<ReturnMessage> check = m.check(list);
    Object check1 = m.check1(1);
}

}

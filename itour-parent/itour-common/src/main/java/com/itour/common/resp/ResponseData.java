package com.itour.common.resp;

import com.itour.constant.Constant;

public class ResponseData<T extends Object> {
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

public ResponseData() {
}
public ResponseData(String code, String msg) {
	super();
	this.code = code;
	this.msg = msg;
}
public static ResponseData<Object> getSuccess(){
	return new ResponseData<Object>(Constant.SUCCESS_CODE, Constant.SUCESS_MESSAGE);
}
public static ResponseData<Object> getSuccess(Object data){
	return new ResponseData<Object>(Constant.SUCCESS_CODE, Constant.SUCESS_MESSAGE);
}
}

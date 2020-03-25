package com.itour.common.req;

import java.io.Serializable;


public class RequestMessage implements Serializable {
private static final long serialVersionUID = 1L;
private RequestHeader requestHeader;
private RequestBody body;
public RequestHeader getRequestHeader() {
	return requestHeader;
}
public void setRequestHeader(RequestHeader requestHeader) {
	this.requestHeader = requestHeader;
}
public RequestBody getBody() {
	return body;
}
public void setBody(RequestBody body) {
	this.body = body;
}

}

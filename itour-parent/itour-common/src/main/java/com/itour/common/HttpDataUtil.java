package com.itour.common;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.req.RequestBody;
import com.itour.common.req.RequestHeader;
import com.itour.common.req.RequestMessage;
import com.itour.common.vo.AccountVo;
import com.itour.util.SessionUtil;



public class HttpDataUtil {

public static RequestMessage postData(JSONObject content,HttpServletRequest request) {
	RequestMessage requestMessage = new RequestMessage();
	RequestHeader header = new RequestHeader();
	if(null!=request) {
		header.setRemoteAddr(HttpDataUtil.getRemoteHost(request));
	}
	
	requestMessage.setRequestHeader(header);	
	RequestBody body = new RequestBody();
	AccountVo sessionUser = SessionUtil.getSessionUser();
	if(null != sessionUser) {
		body.setuId(sessionUser.getuId());
		body.setNickname(sessionUser.getNickname());
		body.setAvatar(sessionUser.getAvatar());
		body.setOauthType(sessionUser.getOauthType());	
	}
	body.setContent(content);
	requestMessage.setBody(body);
	return requestMessage;
}
/**
 * 获取IP
 * @param request
 * @param response
 * @return
 */
public static String getRemoteHost(HttpServletRequest request){
    String ip = request.getHeader("x-forwarded-for");
    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
        ip = request.getHeader("Proxy-Client-IP");
    }

    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
        ip = request.getHeader("WL-Proxy-Client-IP");
    }

    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
        ip = request.getRemoteAddr();
    }
    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
}
}

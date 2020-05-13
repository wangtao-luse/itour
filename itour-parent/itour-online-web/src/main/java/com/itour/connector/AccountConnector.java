package com.itour.connector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.account.api.AccountApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;

@Service
public class AccountConnector {
	@Autowired
	AccountApi accountApi;
/**
 * 注册提交
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage regSub(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.regSub(postData);
	return responseMessage;
}
/**
   * 检查email或用户名是否已经存在
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage checkRegName(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.checkRegName(postData);
	return responseMessage;
}

/**
 * 登录提交
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage loginSub(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.loginSub(postData);
	return responseMessage;
}
}

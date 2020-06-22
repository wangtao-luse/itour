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
public ResponseMessage checkOauthId(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.checkOauthId(postData);
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
/**
 * 权限查询
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage queryAccountRight(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.queryAccountRight(postData);
	return responseMessage;
}
/**
 * 忘记密码
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage findpwd(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.findPwd(postData);
	return responseMessage;
}
/**
 * 组列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage groupList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.getGroupList(postData);
	return responseMessage;
}
/**
 * 角色列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage roleList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.queryRoleList(postData);
	return responseMessage;
}

}

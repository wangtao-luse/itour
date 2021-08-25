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
/**
 * 获取当前用户下的组
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage getAccountGroupName(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.getAccountGroupName(postData);
	return responseMessage;
}
/**
 * 获取当前用户下的角色
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage getAccountRoleName(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.getAccountRoleName(postData);
	return responseMessage;
}
/**
 * 获取当前用户下的权限
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage getAccountRightDetial(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.getAccountRightDetial(postData);
	return responseMessage;
}
/**
 * 获取不用认证的资源
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage getAccountRightAnon(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.getAccountRightAnon(postData);
	return responseMessage;
}
/**
 * 修改图像
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage updateOAuthById(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.updateOAuthById(postData);
	return responseMessage;
}
/**
 * 查看认证表单条
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage selectOauthtOne(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.selectOauthtOne(postData);
	return responseMessage;
}
/**
 * 认证信息修改
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage updateOAuth(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = accountApi.updateOAuth(postData);
	return responseMessage;
}

}

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
 * 用户组列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage getGroupList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage groupList = accountApi.getGroupList(postData);
	return groupList;
	
}
/**
 *  用户组新增
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage insertGroup(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage groupList = accountApi.insertGroup(postData);
	return groupList;
	
}
/**
 * 组授权列表
 * @param jsonObject
 * @param request
 * @return
 */

public ResponseMessage authorizeRoleList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage groupList = accountApi.authorizeRoleList(postData);
	return groupList;
	
}
/**
 * 组授权
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage powerRole(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage groupList = accountApi.powerRole(postData);
	return groupList;
	
}
/**
 * 修改用户组
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage updateGroup(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage updateGroup = accountApi.updateGroup(postData);
	return updateGroup;
	
}
/**
 * 用户组查询单条
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage getGroup(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage updateGroup = accountApi.getGroup(postData);
	return updateGroup;
	
}
/**
 * 角色列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage getRoleList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage groupList = accountApi.queryRoleList(postData);
	return groupList;
	
}
/**
 * 角色修改
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage updateRole(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage updateRole = accountApi.updateRole(postData);
	return updateRole;
}
/**
 * 角色单条查询
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage getRole(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage getRole = accountApi.getRole(postData);
	return getRole;
}
/**
 *  角色插入
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage insertRole(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage getRole = accountApi.insertRole(postData);
	return getRole;
}
/**
 * 角色授权权限列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage authorizeRightList(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage authorizeRightList = accountApi.authorizeRightList(postData);
	return authorizeRightList;
}
/**
 * 角色授权权限
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage powerRight(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage powerRight = accountApi.powerRight(postData);
	return powerRight;
}
/**
 * 前台 权限列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage selectRightList(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage powerRight = accountApi.selectRightList(postData);
	return powerRight;
}
/**
 *  前台会员列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage selectAccountList(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage selectViewAccountList = accountApi.selectAccountList(postData);
	return selectViewAccountList;
}
/**
 *  前台会员列表（视图）
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage selectViewAccountList(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage selectViewAccountList = accountApi.selectViewAccountList(postData);
	return selectViewAccountList;
}
/**
 * 前台权限列表查询（视图）
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage selectViewRightList(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage selectViewRightList = accountApi.selectViewRightList(postData);
	return selectViewRightList;
}
/**
 * 获取指定组下的用户列表
 * @param requestMessage
 * @return
 */
public ResponseMessage getViewAAccountGroupList(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage selectViewRightList = accountApi.getViewAAccountGroupList(postData);
	return selectViewRightList;
}
/**
 * 分配会员
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage grantAccount(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage selectViewRightList = accountApi.grantAccount(postData);
	return selectViewRightList;
}
/**
 *  删除已分配会员的所属组
 * @param requestMessage
 * @return
 */
public ResponseMessage deleteAccountGroup(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage selectViewRightList = accountApi.deleteAccountGroup(postData);
	return selectViewRightList;
}
/**
 *  注册前台会员
 * @param requestMessage
 * @return
 */
public ResponseMessage insertMember(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage insertMember = accountApi.loginSub(requestMessage);
	return insertMember;
}
/**
 *  注册前台会员
 * @param requestMessage
 * @return
 */
public ResponseMessage getViewOauthList(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage insertMember = accountApi.getViewOauthList(requestMessage);
	return insertMember;
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
}

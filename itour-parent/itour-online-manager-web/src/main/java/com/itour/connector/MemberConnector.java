package com.itour.connector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.member.api.MemberApi;

@Service
public class MemberConnector {
	@Autowired
	MemberApi memberApi;
	/**
	 * 角色列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryAccountRight(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.queryRoleList(requestMessage);
	}
	/**
	 * 登录提交
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage loginSub(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.loginSub(requestMessage);
	}
	/**
	 * 后台管理员列表查询
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage selectAccountList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.selectAccountList(requestMessage);
	}
	/**
	 * 后台管理员列表查询（视图）
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage selectViewAccountList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.selectViewAccountList(requestMessage);
	}
	/**
	 * 用户组列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage groupList(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.getGroupList(requestMessage);
	}
	/**
	 * 角色列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage roleList(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.queryRoleList(requestMessage);
	}
	/**
	 * 权限列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage rightList(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.getRightList(requestMessage);
	}
	/**
	 * 组授权角色
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage authorizeRoleList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.authorizeRoleList(requestMessage);
	}
	/**
	 * 组插入
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage insertGroup(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.insertGroup(requestMessage);
	}
	/**
	 *  修改组
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage updateGroup(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.updateGroup(requestMessage);
	}
	/**
	 * 组查询单条
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage getGroup(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.getGroup(requestMessage);
	}
	/**
	 * 组授权角色
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage powerRole(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.powerRole(requestMessage);
	}
	/**
	 * 角色授权列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage authorizeRightList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.authorizeRightList(requestMessage);
	}
	/**
	 * 角色授权
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage powerRight(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.powerRight(requestMessage);
	}
	/**
	 *  权限列表（视图）
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage getViewRightList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.getViewRightList(requestMessage);
	}

	/**
	 * 后台管理员分配管理员
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage grantAccount(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.grantAccount(requestMessage);
	}
	/**
	 * 后台管理员授权组
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage deleteAccountGroup(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.deleteAccountGroup(requestMessage);
	}
	/**
	 * 后台管理员查询指定组下的所有用户列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage getViewMAccountGroupList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.getViewMAccountGroupList(requestMessage);
	}
	/**
	 * 后台管理员账号详情（视图）
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage getViewOauthList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.getViewOauthList(requestMessage);
	}
	/**
	 * 获取当前用户下的组名称
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage getAccountGroupName(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.getAccountGroupName(requestMessage);
	}	
	/**
	 * 获取当前用户下的角色
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage getAccountRoleName(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.getAccountRoleName(requestMessage);
	}	
	/**
	 * 获取当前用户下的权限
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage getAccountRightDetial(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.getAccountRightDetial(requestMessage);
	}
	
}

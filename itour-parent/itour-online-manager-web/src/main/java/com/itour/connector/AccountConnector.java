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
public ResponseMessage getGroupList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage groupList = accountApi.getGroupList(postData);
	return groupList;
	
}
public ResponseMessage insertGroup(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage groupList = accountApi.insertGroup(postData);
	return groupList;
	
}

public ResponseMessage authorizeRoleList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage groupList = accountApi.authorizeRoleList(postData);
	return groupList;
	
}
public ResponseMessage powerRole(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage groupList = accountApi.powerRole(postData);
	return groupList;
	
}
public ResponseMessage updateGroup(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage updateGroup = accountApi.updateGroup(postData);
	return updateGroup;
	
}
public ResponseMessage getGroup(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage updateGroup = accountApi.getGroup(postData);
	return updateGroup;
	
}
public ResponseMessage getRoleList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage groupList = accountApi.queryRoleList(postData);
	return groupList;
	
}
public ResponseMessage updateRole(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage updateRole = accountApi.updateRole(postData);
	return updateRole;
}
public ResponseMessage getRole(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage getRole = accountApi.getRole(postData);
	return getRole;
}
public ResponseMessage insertRole(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage getRole = accountApi.insertRole(postData);
	return getRole;
}
public ResponseMessage authorizeRightList(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage authorizeRightList = accountApi.authorizeRightList(postData);
	return authorizeRightList;
}
public ResponseMessage powerRight(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage powerRight = accountApi.powerRight(postData);
	return powerRight;
}
public ResponseMessage selectRightList(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage powerRight = accountApi.selectRightList(postData);
	return powerRight;
}
public ResponseMessage selectViewAccountList(JSONObject jsonObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage selectViewAccountList = accountApi.selectViewAccountList(postData);
	return selectViewAccountList;
}

}
